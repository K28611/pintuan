package me.k28611.pintuan.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.functions.FuncLang;
import me.k28611.pintuan.annotation.JwtIgnore;
import me.k28611.pintuan.dao.GroupFareMapper;
import me.k28611.pintuan.entity.Audience;
import me.k28611.pintuan.enums.ResultCode;
import me.k28611.pintuan.model.po.GroupFare;
import me.k28611.pintuan.model.po.GroupFareDetail;
import me.k28611.pintuan.model.po.GroupMember;
import me.k28611.pintuan.model.po.HrMember;
import me.k28611.pintuan.model.vo.UnpayBean;
import me.k28611.pintuan.service.ActivityService;
import me.k28611.pintuan.service.FileService;
import me.k28611.pintuan.service.GroupService;
import me.k28611.pintuan.service.UserService;
import me.k28611.pintuan.utils.JsonResult;
import me.k28611.pintuan.utils.JwtTokenUtils;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.Null;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/userApi")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Audience audience;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    FileService fileService;
    @Autowired
    ActivityService activityService;
    @Autowired
    GroupService groupService;
    @Autowired
    GroupFareMapper groupFareMapper;
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * @Description:登录接口

     * @param param
     * @param request
     * @return me.k28611.manage.utils.JsonResult
     **/

    @GetMapping("/login")
    @ResponseBody
    @JwtIgnore
    public JsonResult login(@RequestBody Map<String, String> param) {


        String userName = param.get("userName");
        String passWord = param.get("password");
        try {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
                return new JsonResult(ResultCode.PARAM_IS_BLANK, null);
            } else {
                HrMember user = userService.selectByUserName(userName);
                if (user == null) {
                    return new JsonResult(ResultCode.USER_LOGIN_ERROR, null);
                } else {
                    System.out.println(bCryptPasswordEncoder.matches(passWord,user.getPassword()));
                    if (bCryptPasswordEncoder.matches(passWord, user.getPassword())) {
                        System.out.println(passWord);
                        final String token = JwtTokenUtils.generateToken(user, audience);
                        return new JsonResult(ResultCode.SUCCESS, token);
                    } else {
                        return new JsonResult(ResultCode.USER_LOGIN_ERROR, null);
                    }
                }
            }
        } catch (Exception e) {
            return new JsonResult(e.getMessage());
        }
    }

    @JwtIgnore
    @GetMapping("/register")
    public JsonResult register(@RequestBody Map<String, String> param) {
        String userName = param.get("userName");
        HrMember hrMember = userService.selectByUserName(userName);
        if (hrMember!= null){
            return new JsonResult(ResultCode.DATA_ALREADY_EXISTED,null);
        }
        String passWord = encoder().encode(param.get("password").toString());
        System.out.println(passWord);
        HrMember newUser = new HrMember();
        newUser.setUsername(userName);
        newUser.setPassword(passWord);
        userService.addUser(newUser);
        return new JsonResult(ResultCode.SUCCESS,null);
    }

    @RequestMapping("/getGroupUnpaid")
    public JsonResult getGroupUnpaid(@RequestBody Map<String, String> param){
        int workNo = Integer.parseInt(param.get("workNo"));
        List <GroupMember> group = groupService.selectGroupByWorkNo(workNo);//查找用户加入的团
        //System.out.println(group);
        if(group.size()==0){
            return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
        }
        Map <UnpayBean, BigDecimal> unpaid = new HashMap<>();

        for (int k = 0; k < group.size();k++){
            List<GroupFareDetail> fareTopicIdByGroupNo = groupService.findFareTopicIdByGroupNo(group.get(k).getGroupno()); //团下面的topic
            System.out.println(fareTopicIdByGroupNo);
            if (fareTopicIdByGroupNo.size()==0)
                continue; //下一个团
            for (int j = 0  ;j < fareTopicIdByGroupNo.size();j++){
                GroupFare notestByTopicIdAndGroupNo = groupService.findNotestByTopicIdAndGroupNo(group.get(k).getGroupno(),
                        fareTopicIdByGroupNo.get(j).getTopicid());//查找对应的记录
                if (notestByTopicIdAndGroupNo == null){
                    UnpayBean unpayBean = new UnpayBean(fareTopicIdByGroupNo.get(j).getGroupno(),
                            fareTopicIdByGroupNo.get(j).getTopicid(),fareTopicIdByGroupNo.get(j).getTopicname());
                    unpaid.put(unpayBean,fareTopicIdByGroupNo.get(j).getMoney());
                }
            }
        }

        return new JsonResult(ResultCode.SUCCESS,unpaid);
    }

    @RequestMapping("/payActivityFare")
    public JsonResult pay(){
        return null;
    }

    @RequestMapping("/payGroupFare")
    public JsonResult payGroupFare(@RequestBody Map<String, String> param){
        return null;
    }




}

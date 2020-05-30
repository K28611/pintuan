package me.k28611.pintuan.controller;

import me.k28611.pintuan.entity.Audience;
import me.k28611.pintuan.enums.ResultCode;
import me.k28611.pintuan.model.po.*;
import me.k28611.pintuan.model.vo.AddUnpay;
import me.k28611.pintuan.model.vo.OughtUnpay;
import me.k28611.pintuan.model.vo.UnpayBean;
import me.k28611.pintuan.service.ActivityService;
import me.k28611.pintuan.service.GroupService;
import me.k28611.pintuan.service.UserService;
import me.k28611.pintuan.utils.JsonResult;
import me.k28611.pintuan.utils.JwtTokenUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
    ActivityService activityService;
    @Autowired
    GroupService groupService;

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

    @RequestMapping("/login")
    @ResponseBody
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
                        return new JsonResult(ResultCode.SUCCESS, null);
                    } else {
                        return new JsonResult(ResultCode.USER_LOGIN_ERROR, null);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(ResultCode.DATA_IS_WRONG,null);

        }
    }

    /**
     * @Description:注册接口
     * @param userName passWord
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/register")
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

    /**
     * @Description:查询个人获取未支付的团信息
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/getGroupUnpaid")
    public JsonResult getGroupUnpaid(){
        int workNo = 1;
        List <GroupMember> group = groupService.selectGroupByWorkNo(workNo);//查找用户加入的团
        if(group.size()==0){
            return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
        }
        Map <UnpayBean, BigDecimal> unpaid = new HashMap<>();

        for (int k = 0; k < group.size();k++){
            List<GroupFareDetail> fareTopicIdByGroupNo = groupService.findFareTopicIdByGroupNo(group.get(k).getGroupno()); //团下面的topic
            if (fareTopicIdByGroupNo.size()==0)
                continue; //下一个团
            for (int j = 0  ;j < fareTopicIdByGroupNo.size();j++){
                GroupFare notestByTopicIdAndGroupNo = groupService.findNotestByTopicIdAndGroupNo(group.get(k).getGroupno(),
                        fareTopicIdByGroupNo.get(j).getTopicid(),
                workNo);//查找对应的记录
                if (notestByTopicIdAndGroupNo == null){
                    UnpayBean unpayBean = new UnpayBean(fareTopicIdByGroupNo.get(j).getGroupno(),
                            fareTopicIdByGroupNo.get(j).getTopicid(),fareTopicIdByGroupNo.get(j).getTopicname());
                        unpaid.put(unpayBean,fareTopicIdByGroupNo.get(j).getMoney());
                }
            }
        }

        return new JsonResult(ResultCode.SUCCESS,unpaid);
    }

    /**
     * @Description:交活动费
     * @param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/payActivityFare")
    public JsonResult payActivityFare(@RequestBody Map<String, String> param){
        int activityNo = Integer.parseInt(param.get("activityNo"));
        int activirtyMemberNo = Integer.parseInt(param.get("workNo"));
        ActivityMember activityMember = activityService.selectByActivityNoAndActivityMemberNo(activirtyMemberNo, activityNo);
        if (activityMember==null)
            return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
        activityMember.setOughtmoneystatus(true);
        return new JsonResult(ResultCode.SUCCESS,null);
    }
    /**
     * @Description:交附加活动费
     * @param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/payAddActivityFare")
    public JsonResult payAddActivityFare(@RequestBody Map<String, String> param){
        int activityNo = Integer.parseInt(param.get("activityNo"));
        int activirtyMemberNo = Integer.parseInt(param.get("workNo"));
        ActivityMember activityMember = activityService.selectByActivityNoAndActivityMemberNo(activirtyMemberNo, activityNo);
        if (activityMember==null)
            return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
        activityMember.setAddfarestatus(true);
        return new JsonResult(ResultCode.SUCCESS,null);
    }

    /**
     * @Description:交团费
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/payGroupFare")
    public JsonResult payGroupFare(@RequestBody GroupFare param){
        groupService.payGroupFare(param);
        return new JsonResult(ResultCode.SUCCESS);
    }

    /**
     * @Description:获取个人未支付的活动信息
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/
    @RequestMapping("/getActivityUnpaid")
    public JsonResult getActivityUnpaid(@RequestBody Map<String, String> param){
        int workNo = Integer.parseInt(param.get("workNo"));
        Map<OughtUnpay,BigDecimal> oughtMoneyUnPayMap = new HashMap<>();
        Map<AddUnpay,BigDecimal> addMoneyUnPayMap = new HashMap<>();
        List<ActivityMember> activityMemberByMemberNo = activityService.getActivityMemberByMemberNo(workNo);
        if (activityMemberByMemberNo.size()==0)
            return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
        for (int i = 0; i < activityMemberByMemberNo.size(); i++) {
            Boolean oughtmoneystatus = activityMemberByMemberNo.get(i).getOughtmoneystatus();
            Boolean addfarestatus = activityMemberByMemberNo.get(i).getAddfarestatus();
            if(!oughtmoneystatus){
                OughtUnpay oughtUnpay = new OughtUnpay();
                oughtUnpay.setActivityNo(activityMemberByMemberNo.get(i).getActivityno());
                oughtUnpay.setActivityName(activityMemberByMemberNo.get(i).getActivityname());
                oughtMoneyUnPayMap.put(oughtUnpay,activityMemberByMemberNo.get(i).getOughtmoney());
            }
            if (!addfarestatus){
                AddUnpay addUnpay = new AddUnpay();
                addUnpay.setActivityName(activityMemberByMemberNo.get(i).getActivityname());
                addUnpay.setActivityNo(activityMemberByMemberNo.get(i).getActivityno());
                addUnpay.setAddFareTopic(activityMemberByMemberNo.get(i).getAddfaretopic());
                addMoneyUnPayMap.put(addUnpay,activityMemberByMemberNo.get(i).getAddfare());
            }
        }
        return  new JsonResult(ResultCode.SUCCESS,oughtMoneyUnPayMap,addMoneyUnPayMap);
    }




}

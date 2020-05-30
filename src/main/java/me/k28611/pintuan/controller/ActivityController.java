package me.k28611.pintuan.controller;

import lombok.extern.slf4j.Slf4j;
import me.k28611.pintuan.enums.ResultCode;
import me.k28611.pintuan.model.po.ActivityMember;
import me.k28611.pintuan.model.vo.*;
import me.k28611.pintuan.service.ActivityService;
import me.k28611.pintuan.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author K28611
 * @date 2020/5/29 15:05
 */
@Controller
@Slf4j
@RequestMapping("/api/activityApi")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    /**
     * @Description:创建活动
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/
    @RequestMapping("/createActivity")
    public JsonResult createActivity(@RequestBody ActivityInfo param){
        activityService.createActivity(param);
        for (ActivityMember activityMember : param.getActivityMembers()){
            activityService.joinActivity(activityMember);
        }
        return new JsonResult(ResultCode.SUCCESS,null);
    }

    /**
     * @Description:获取活动信息
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/getActivityInfo")
    public JsonResult getActivityInfo(@RequestBody Map<String, String> param){
        int activityNo = Integer.parseInt(param.get("activityNo"));
        ActivityInfo activityInfo = activityService.findActivityInfoByActivityNo(activityNo);
        return new JsonResult(ResultCode.SUCCESS,activityInfo);
    }

    /**
     * @Description:获取活动中未支付的成员信息
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @RequestMapping("/getUnpayActivityMemberInfo")
    public JsonResult getUnpayActivityMemberInfo(@RequestBody  Map<String, String> param){
        int activityNo = Integer.parseInt(param.get("activityNo"));
        ActivityInfo activityInfo = activityService.findActivityInfoByActivityNo(activityNo);
        List<OughtUnpay2> oughtUnpayList = new ArrayList<>();
        List<AddUnpay2> addUnpayList = new ArrayList<>();
        Map<String ,List<OughtUnpay2> > oughtUnpayMap  = new HashMap<>();
        Map<String ,List<AddUnpay2> > addUnpayMap  = new HashMap<>();
        List<ActivityMember> activityMembers = activityInfo.getActivityMembers();
        for (ActivityMember activityMember :activityMembers){
            if (!activityMember.getOughtmoneystatus()){
                OughtUnpay2 oughtUnpay = new OughtUnpay2();
                oughtUnpay.oughtUnpay.setActivityNo(activityMember.getActivityno());
                oughtUnpay.oughtUnpay.setActivityName(activityInfo.getActivityName());
                oughtUnpay.setMoney(activityMember.getOughtmoney());
                oughtUnpayList.add(oughtUnpay);
            }
            if (!activityMember.getAddfarestatus()){
                AddUnpay2 addUnpay = new AddUnpay2();
                addUnpay.setAddMoney(activityMember.getAddfare());
                addUnpay.addUnpay.setAddFareTopic(activityMember.getAddfaretopic());
                addUnpay.addUnpay.setActivityNo(activityMember.getActivityno());
                addUnpay.addUnpay.setActivityName(activityInfo.getActivityName());
                addUnpayList.add(addUnpay);
            }
        }
        oughtUnpayMap.put("OughtPay",oughtUnpayList);
        addUnpayMap.put("addUnpay",addUnpayList);
        return new JsonResult(ResultCode.SUCCESS,oughtUnpayMap,addUnpayMap);
    }



}

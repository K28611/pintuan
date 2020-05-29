package me.k28611.pintuan.controller;

import lombok.extern.slf4j.Slf4j;
import me.k28611.pintuan.dao.ActivityMasterMapper;
import me.k28611.pintuan.enums.ResultCode;
import me.k28611.pintuan.model.po.ActivityMember;
import me.k28611.pintuan.model.vo.ActivityInfo;
import me.k28611.pintuan.service.ActivityService;
import me.k28611.pintuan.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
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
    @RequestMapping("/createActivity")
    public JsonResult createActivity(@RequestBody ActivityInfo param){
        activityService.createActivity(param);
        for (ActivityMember activityMember : param.getActivityMembers()){
            activityService.joinActivity(activityMember);
        }
        return new JsonResult(ResultCode.SUCCESS,null);
    }


}

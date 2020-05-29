package me.k28611.pintuan.controller;

import me.k28611.pintuan.enums.ResultCode;
import me.k28611.pintuan.model.po.GroupMaster;
import me.k28611.pintuan.model.po.HrMember;
import me.k28611.pintuan.model.vo.GroupInfo;
import me.k28611.pintuan.model.vo.User;
import me.k28611.pintuan.service.GroupService;
import me.k28611.pintuan.service.UserService;
import me.k28611.pintuan.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author K28611
 * @date 2020/5/28 22:48
 */
@RestController
@RequestMapping("/api/groupApi")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;
    @GetMapping("/getMyGroup")
    public JsonResult getMyGroup(int workNo){
       //groupService.c(workNo);
        return null;
    }
    /**
     * @Description:创建团队
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @GetMapping("/createGroup")
    public JsonResult createGroup(@RequestBody Map<String, String> param){
        int workNo = Integer.parseInt(param.get("workNo"));
        String groupName = param.get("groupName");
        groupService.createGroup(groupName,workNo);
        return new JsonResult(ResultCode.SUCCESS,null);
    }

    /**
     * @Description:加入团队
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @GetMapping("/joinGroup")
    public JsonResult joinGroup(@RequestBody Map<String, String> param){
        int groupNo = Integer.parseInt(param.get("groupNo"));
        int workNo = Integer.parseInt(param.get("workNo"));
        boolean flag = groupService.findGroupByGroupID(groupNo);
        if(flag){
            String groupName = param.get("groupName");
            groupService.joinMember(groupNo,workNo);
            return new JsonResult(ResultCode.SUCCESS,null);
        }
        return new JsonResult(ResultCode.RESULT_DATA_NONE,null);
    }

    /**
     * @Description:获取团信息
     * @param param
     * @return me.k28611.pintuan.utils.JsonResult
     **/

    @GetMapping("/getGroupInfo")
    public JsonResult getGroupInfo(@RequestBody Map<String, String> param ){
        int groupNo = Integer.parseInt(param.get("groupNo"));
        GroupInfo groupInfo = groupService.getGroupInfo(groupNo);
        List<User> members = groupService.getMembers(groupNo);
        return new JsonResult(ResultCode.SUCCESS,groupInfo,members);
    }

}

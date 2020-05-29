package me.k28611.pintuan.service;

import me.k28611.pintuan.model.po.ActivityMember;
import me.k28611.pintuan.model.vo.ActivityInfo;

import java.util.List;

/**
 * @author K28611
 * @date 2020/5/29 10:08
 */
public interface ActivityService {
    public void createActivity(ActivityInfo info);

    public void joinActivity(ActivityMember member);

    //查询某个活动的成员
    public List<ActivityMember> getActivityMemberByActivityNo(int activityNo);

    //查询某个人的活动
    public List<ActivityMember> getActivityMemberByMemberNo(int memberNo);


}

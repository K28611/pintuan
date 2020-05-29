package me.k28611.pintuan.service.Imp;

import me.k28611.pintuan.dao.ActivityMasterMapper;
import me.k28611.pintuan.dao.ActivityMemberMapper;
import me.k28611.pintuan.model.po.ActivityMaster;
import me.k28611.pintuan.model.po.ActivityMember;
import me.k28611.pintuan.model.vo.ActivityInfo;
import me.k28611.pintuan.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author K28611
 * @date 2020/5/29 10:08
 */
@Service("activityService")
public class ActivityServiceImp implements ActivityService {
    @Autowired
    ActivityMemberMapper activityMemberMapper;
    @Autowired
    ActivityMasterMapper activityMasterMapper;

    @Override
    public void createActivity(ActivityInfo info) {
        ActivityMaster activityMaster = new ActivityMaster();
        activityMaster.setActivityendtime(info.getActivityEndTime());
        activityMaster.setActivitystarttime(info.getActivityStartTime());
        activityMaster.setGroupno(info.getGroupNo());
        activityMaster.setSumaccount(info.getSumAccount());
        activityMasterMapper.insert(activityMaster);

    }

    @Override
    public void joinActivity(ActivityMember member) {
        activityMemberMapper.insert(member);
    }

    @Override
    public List<ActivityMember> getActivityMemberByActivityNo(int activityNo) {
        return activityMemberMapper.selectByActivityNo(activityNo);
    }

    @Override
    public List<ActivityMember> getActivityMemberByMemberNo(int memberNo) {
        return activityMemberMapper.selectByMemberNo(memberNo);
    }

}

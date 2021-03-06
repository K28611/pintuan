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
        for (ActivityMember activityMember:info.getActivityMembers()){
            activityMemberMapper.insert(activityMember);
        }
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

    @Override
    public List<ActivityMaster> findActivityByGroupName(int memberNo) {
        return activityMasterMapper.selectByGroupNo(memberNo);
    }

    @Override
    public ActivityInfo findActivityInfoByActivityNo(int activityNo) {
        ActivityMaster activityMaster = activityMasterMapper.selectByPrimaryKey(activityNo);
        List<ActivityMember> activityMembers = activityMemberMapper.selectByActivityNo(activityNo);
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityMembers(activityMembers);
        activityInfo.setActivityEndTime(activityMaster.getActivityendtime());
        activityInfo.setActivityStartTime(activityMaster.getActivitystarttime());
        activityInfo.setGroupNo(activityMaster.getGroupno());
        activityInfo.setSumAccount(activityMaster.getSumaccount());
        return activityInfo;
    }

    @Override
    public void payActivityFare(ActivityMember activityMember) {
        activityMemberMapper.updateByActivityNoAndActivityMemberNo(activityMember);
    }

    @Override
    public ActivityMember selectByActivityNoAndActivityMemberNo(Integer memberno, Integer activityno) {
        return activityMemberMapper.selectByActivityNoAndActivityMemberNo(memberno,activityno);
    }

}

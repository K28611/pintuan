package me.k28611.pintuan.service.Imp;

import me.k28611.pintuan.dao.*;
import me.k28611.pintuan.model.po.*;
import me.k28611.pintuan.model.vo.GroupInfo;
import me.k28611.pintuan.model.vo.User;
import me.k28611.pintuan.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author K28611
 * @date 2020/5/28 22:32
 */
@Service("groupService")
public class GroupServiceImp implements GroupService {
    @Autowired
    GroupMasterMapper groupMasterMapper;
    @Autowired
    GroupMemberMapper groupMemberMapper;
    @Autowired
    GroupFareMapper groupFareMapper;
    @Autowired
    HrMemberMapper hrMemberMapper;
    @Autowired
    GroupFareDetailMapper groupFareDetailMapper;
    @Override
    public void createGroup(String groupName, int memberNo) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        GroupMaster payGroup = new GroupMaster();
        payGroup.setGroupmaster(memberNo);
        payGroup.setGroupfoundtime(dateString);
        payGroup.setGroupname(groupName);
        groupMasterMapper.insert(payGroup);
    }

    @Override
    public void addMember(int groupNo, HrMember[] hrMembers) {
        for (HrMember hrMember : hrMembers) {
            GroupMember groupMember = new GroupMember();
            groupMember.setGroupno(groupNo);
            groupMember.setIsvalid(true);
            groupMember.setGroupmember(hrMember.getWorkno());
            groupMemberMapper.insert(groupMember);
        }

    }

    @Override
    public void payGroupFare(GroupFare fare) {
        groupFareMapper.insert(fare);
    }

    @Override
    public List<User> getMembers(int groupNo) {
        return hrMemberMapper.getMembersByGroup(groupNo);
    }

    @Override
    public void joinMember(int groupNo, int workNo) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupno(groupNo);
        groupMember.setIsvalid(true);
        groupMember.setGroupmember(workNo);
        groupMemberMapper.insert(groupMember);
    }

    @Override
    public GroupInfo getGroupInfo(int groupNo) {
        return groupMasterMapper.getGroupInfo(groupNo);
    }

    @Override
    public void addGroupFareDetail(String topicName, int groupNo) {
        GroupFareDetail groupFareDetail = new GroupFareDetail();
        groupFareDetail.setGroupno(groupNo);
        groupFareDetail.setTopicname(topicName);
        groupFareDetailMapper.insert(groupFareDetail);
    }

    @Override
    public boolean findGroupByGroupID(int GroupID) {
        GroupMaster groupMaster = groupMasterMapper.selectByPrimaryKey(GroupID);
        if(groupMaster!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<GroupMember> selectGroupByWorkNo(int workNo) {
           return  groupMemberMapper.selectByWorkNo(workNo);

    }

    @Override
    public List<GroupFareDetail> findFareTopicIdByGroupNo(Integer groupNo) {
        return groupFareDetailMapper.selectByGroupNo(groupNo) ;
    }

    @Override
    public GroupFare findNotestByTopicIdAndGroupNo(Integer groupNo,Integer topicId,Integer workNo) {
        return  groupFareMapper.selectByTopicIdAndGroupNo(groupNo,topicId,workNo);
    }

    @Override
    public void PayGroupFare(GroupFare fare) {
        groupFareMapper.insert(fare);
    }

    @Override
    public List<GroupMember> findMemberByGroupNo(int groupNo) {
        return   groupMemberMapper.selectBygroupNo(groupNo);
    }



    @Override
    public void ExitGroup() {

    }




}

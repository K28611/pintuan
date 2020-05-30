package me.k28611.pintuan.service;

import me.k28611.pintuan.model.po.*;
import me.k28611.pintuan.model.vo.GroupInfo;
import me.k28611.pintuan.model.vo.User;

import java.security.acl.Group;
import java.util.List;

/**
 * @author K28611
 * @date 2020/5/28 18:26
 */
public interface GroupService {
    public void createGroup(String groupName,int memberNo);

    //加入小组
    public void addMember(int groupNo,HrMember[] hrMembers);

    public void payGroupFare(GroupFare fare);

    public List<User> getMembers(int groupNo);

    public void joinMember(int groupNo,int workNo);

    public GroupInfo getGroupInfo(int groupNo);

    public void ExitGroup();

    public void addGroupFareDetail(String topicName,int groupNo);

    public boolean findGroupByGroupID(int GroupID);

    public  List<GroupMember> selectGroupByWorkNo(int workNo);

    public  List<GroupFareDetail> findFareTopicIdByGroupNo(Integer groupNo);

    public  GroupFare findNotestByTopicIdAndGroupNo(Integer groupNo,Integer topicId,Integer workNo);

    public void PayGroupFare(GroupFare fare);

    public  List<GroupMember> findMemberByGroupNo(int groupNo);


}

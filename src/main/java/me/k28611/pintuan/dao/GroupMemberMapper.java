package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.GroupMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMemberMapper {
    int deleteByPrimaryKey(Integer pno);

    int insert(GroupMember record);

    GroupMember selectByPrimaryKey(Integer pno);

    List<GroupMember> selectAll();

    int updateByPrimaryKey(GroupMember record);


    List<GroupMember> selectBygroupNo(int groupno);

    List<GroupMember> selectByWorkNo(int workNo);
}
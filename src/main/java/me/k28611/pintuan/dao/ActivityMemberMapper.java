package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.ActivityMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMemberMapper {
    int deleteByPrimaryKey(Integer pno);

    int insert(ActivityMember record);

    ActivityMember selectByPrimaryKey(Integer pno);

    List<ActivityMember> selectAll();

    int updateByPrimaryKey(ActivityMember record);

    List<ActivityMember> selectByActivityNo(Integer activityno);

    List<ActivityMember> selectByMemberNo(Integer memberno);

    int updateByActivityNoAndActivityMemberNo(ActivityMember record);

    ActivityMember selectByActivityNoAndActivityMemberNo(Integer memberno,Integer activityno);

}
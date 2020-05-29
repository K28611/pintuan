package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.HrMember;
import me.k28611.pintuan.model.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HrMemberMapper {
    int deleteByPrimaryKey(Integer workno);

    int insert(HrMember record);

    HrMember selectByPrimaryKey(Integer workno);

    List<HrMember> selectAll();

    int updateByPrimaryKey(HrMember record);

    List<User> getMembersByGroup(Integer groupNo);

    HrMember selectByUserName(String userName);
}
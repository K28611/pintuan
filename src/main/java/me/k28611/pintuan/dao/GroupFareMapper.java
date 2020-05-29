package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.GroupFare;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GroupFareMapper {
    int deleteByPrimaryKey(Integer fareno);

    int insert(GroupFare record);

    GroupFare selectByPrimaryKey(Integer fareno);

    List<GroupFare> selectAll();

    int updateByPrimaryKey(GroupFare record);

    GroupFare selectByTopicIdAndGroupNo(Integer groupno,Integer topicid);

}
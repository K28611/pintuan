package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.GroupFareDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupFareDetailMapper {
    int deleteByPrimaryKey(Integer topicid);

    int insert(GroupFareDetail record);

    GroupFareDetail selectByPrimaryKey(Integer topicid);

    List<GroupFareDetail> selectAll();

    int updateByPrimaryKey(GroupFareDetail record);

    List<GroupFareDetail> selectByGroupNo(Integer groupNo);

    GroupFareDetail selectByGroupNoAndTopicId(Integer groupNo,Integer topicid);
}
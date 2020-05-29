package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.ActivityMaster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMasterMapper {
    int deleteByPrimaryKey(Integer activityno);

    int insert(ActivityMaster record);

    ActivityMaster selectByPrimaryKey(Integer activityno);

    List<ActivityMaster> selectAll();

    int updateByPrimaryKey(ActivityMaster record);

    List<ActivityMaster> selectByGroupNo(Integer groupno);


}
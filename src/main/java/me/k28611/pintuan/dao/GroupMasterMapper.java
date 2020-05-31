package me.k28611.pintuan.dao;

import java.util.List;
import me.k28611.pintuan.model.po.GroupMaster;
import me.k28611.pintuan.model.vo.GroupInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMasterMapper {
    int deleteByPrimaryKey(Integer groupno);

    int insert(GroupMaster record);

    GroupMaster selectByPrimaryKey(Integer groupno);

    List<GroupMaster> selectAll();

    int updateByPrimaryKey(GroupMaster record);

    GroupInfo getGroupInfo (Integer groupno);

    List<GroupInfo>  getMyGroup(Integer workNo);
}
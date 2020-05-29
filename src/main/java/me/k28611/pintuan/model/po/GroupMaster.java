package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupMaster implements Serializable {
    private Integer groupno;

    private String groupname;

    private Integer groupmaster;

    private String groupfoundtime;



}
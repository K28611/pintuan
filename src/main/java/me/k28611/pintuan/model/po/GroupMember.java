package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupMember implements Serializable {
    private Integer pno;

    private Integer groupno;

    private Integer groupmember;

    private Boolean isvalid;


}
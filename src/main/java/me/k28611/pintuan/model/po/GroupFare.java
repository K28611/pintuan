package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupFare implements Serializable {
    private Integer fareno;

    private Integer groupno;

    private Integer groupmember;

    private Integer topicid;

    private static final long serialVersionUID = 1L;



}
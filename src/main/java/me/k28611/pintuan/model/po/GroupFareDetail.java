package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GroupFareDetail implements Serializable {
    private Integer topicid;

    private String topicname;

    private Integer groupno;

    private BigDecimal money;
}
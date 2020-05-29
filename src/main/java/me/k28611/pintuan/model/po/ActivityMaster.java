package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ActivityMaster implements Serializable {
    private Integer activityno;

    private String activitystarttime;

    private Integer groupno;

    private BigDecimal sumaccount;

    private String activityendtime;

    private String activityName;

    private static final long serialVersionUID = 1L;


}
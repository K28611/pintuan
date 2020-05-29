package me.k28611.pintuan.model.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ActivityMember implements Serializable {
    private Integer pno;

    private Integer activirtymemberno;

    private BigDecimal oughtmoney;

    private Boolean oughtmoneystatus;

    private String activityname;

    private Integer activityno;

    private BigDecimal addfare;

    private Boolean addfarestatus;

    private String addfaretopic;


}
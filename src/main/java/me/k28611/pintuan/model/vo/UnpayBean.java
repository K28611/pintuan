package me.k28611.pintuan.model.vo;

import lombok.Data;

/**
 * @author K28611
 * @date 2020/5/29 17:42
 */
@Data
public class UnpayBean {
    public UnpayBean(Integer GroupNo,Integer AcitivityNo,String ActivityName){
        this.groupNo = GroupNo;
        this.acitivityNo = AcitivityNo;
        this.activityName = ActivityName;
    }
    public Integer groupNo;

    public Integer acitivityNo;

    public String activityName;

}

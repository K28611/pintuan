package me.k28611.pintuan.model.vo;

import lombok.Data;

/**
 * @author K28611
 * @date 2020/5/30 9:48
 */
@Data
public class UnpayGroupBean {

    public UnpayGroupBean(int WorkNo,String MemberName,int TopicId, String TopicName){
        this.workNo = WorkNo;
        this.memberName = MemberName;
        this.topicId = TopicId;
        this.topicName = TopicName;
    }
    public int workNo;

    public String memberName;

    public int topicId;

    public String topicName;
}

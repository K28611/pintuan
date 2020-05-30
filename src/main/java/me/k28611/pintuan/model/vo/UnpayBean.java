package me.k28611.pintuan.model.vo;

import lombok.Data;

/**
 * @author K28611
 * @date 2020/5/29 17:42
 */
@Data
public class UnpayBean {
    public UnpayBean(Integer GroupNo,Integer TopicId,String TopicName){
        this.groupNo = GroupNo;
        this.topicID = TopicId;
        this.topicName = TopicName;
    }
    public Integer groupNo;

    public Integer topicID;

    public String topicName;

}

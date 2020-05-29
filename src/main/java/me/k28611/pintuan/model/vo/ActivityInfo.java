package me.k28611.pintuan.model.vo;

import lombok.Data;
import me.k28611.pintuan.model.po.ActivityMember;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author K28611
 * @date 2020/5/29 15:24
 */
@Data
public class ActivityInfo {

    int groupNo;

    String  activityName;

    BigDecimal sumAccount;

    String activityStartTime;

    String activityEndTime;

    List<ActivityMember> activityMembers;
}

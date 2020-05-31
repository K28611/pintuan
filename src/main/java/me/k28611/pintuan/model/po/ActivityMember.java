package me.k28611.pintuan.model.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ActivityMember  extends BaseRowModel implements Serializable {


    private Integer pno;

    @ExcelProperty(value = "编号",index = 0)
    private Integer activityno;

    @ExcelProperty(value = "活动名称",index =1 )
    private String activityname;

    private Integer activirtymemberno;

    @ExcelProperty(value = "应付金额",index =2 )
    private BigDecimal oughtmoney;

    @ExcelProperty(value = "支付状态",index = 3)
    private Boolean oughtmoneystatus;

    @ExcelProperty(value = "附加项目",index = 4)
    private String addfaretopic;

    @ExcelProperty(value = "附加费",index = 5)
    private BigDecimal addfare;

    @ExcelProperty(value = "附加费支付状态",index = 6)
    private Boolean addfarestatus;




}
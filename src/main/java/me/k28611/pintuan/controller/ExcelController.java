package me.k28611.pintuan.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import me.k28611.pintuan.model.po.ActivityMember;
import me.k28611.pintuan.service.ActivityService;
import me.k28611.pintuan.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author K28611
 * @date 2020/5/30 14:33
 */


@RestController
@RequestMapping("/api/ExcelApi")
public class ExcelController {
    @Autowired
    ActivityService activityService;

    @RequestMapping("/activity/{id}")
    public JsonResult getActivityExcel(@PathVariable("id")Integer id){
        List<ActivityMember> activityMembers = activityService.getActivityMemberByActivityNo(id);
        OutputStream out = null;
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            out = new FileOutputStream("D:\\"+uuid+".xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 0, ActivityMember.class);
            sheet1.setSheetName("第一个sheet");
            writer.write(activityMembers, sheet1);
            writer.finish();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping("/test")
    public void tesrt(){
        List<ActivityMember> activityMembers = activityService.getActivityMemberByActivityNo(1);
        System.out.println(activityMembers);
    }




}

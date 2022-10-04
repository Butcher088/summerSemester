package com.fan.controller;

import com.fan.entity.Report;
import com.fan.service.ReportService;
import com.fan.utils.AjaxResult;
import com.fan.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/report")
    public AjaxResult newReport(int userId, int articleId,
                                int reportedId, String content, String img){

        Report report = new Report();
        report.setUserId(userId);
        report.setArticleId(articleId);
        report.setReportedId(reportedId);
        report.setContent(content);
        report.setImg(img);
        report.setTime(new Date());

        int i = reportService.newReport(report);
        return AjaxResult.success("举报成功",i);
    }

    @GetMapping("/admin/report/update/{reportId}")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult updateReport(@PathVariable("reportId") int reportId) {
        System.out.println(reportId+"==reportId");
        int result = reportService.updateReport(reportId);
        return AjaxResult.success("更新成功");
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/admin/report/list")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult list(Report report)
    {
        System.out.println("report="+report);
        List<Report> list = reportService.selectReportList(report);
        return new ResponseResult(200,list);
    }


    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/admin/report/{reportId}")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return AjaxResult.success(reportService.selectReportByReportId(reportId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping("/admin/report")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult add(@RequestBody Report report)
    {
        return new ResponseResult(200,reportService.insertReport(report));
    }

//    /**
//     * 修改【请填写功能名称】
//     */
//    @PutMapping("/admin/report")
//    public ResponseResult edit(@RequestBody Report report)
//    {
//        return new ResponseResult(200,reportService.updateReport(report));
//    }

    /**
     * 删除【请填写功能名称】
     */
    @DeleteMapping("/admin/report/{reportIds}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult remove(@PathVariable Long[] reportIds)
    {
        return new ResponseResult(200,reportService.deleteReportByReportIds(reportIds));
    }
}

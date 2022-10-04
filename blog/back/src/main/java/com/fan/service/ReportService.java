package com.fan.service;

import com.fan.entity.Report;

import java.util.List;

public interface ReportService {


    int newReport(Report report);

    int updateReport(int reportId);


    /**
     * 查询【请填写功能名称】
     *
     * @param reportId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Report selectReportByReportId(Long reportId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param report 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增【请填写功能名称】
     *
     * @param report 【请填写功能名称】
     * @return 结果
     */
    public int insertReport(Report report);

//    /**
//     * 修改【请填写功能名称】
//     *
//     * @param report 【请填写功能名称】
//     * @return 结果
//     */
//    public int updateReport(Report report);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param reportIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteReportByReportIds(Long[] reportIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param reportId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteReportByReportId(Long reportId);
}

package com.fan.service.Impl;

import com.fan.entity.Report;
import com.fan.mapper.ReportMapper;
import com.fan.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public int newReport(Report report) {
        return reportMapper.newReport(report);
    }


    @Override
    public int updateReport(int reportId) {
        return reportMapper.updateReport(reportId);
    }
    /**
     * 查询【请填写功能名称】
     *
     * @param reportId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Report selectReportByReportId(Long reportId)
    {
        return reportMapper.selectReportByReportId(reportId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param report 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Report> selectReportList(Report report)
    {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param report 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertReport(Report report)
    {
        return reportMapper.insertReport(report);
    }



    /**
     * 批量删除【请填写功能名称】
     *
     * @param reportIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportIds(Long[] reportIds)
    {
        return reportMapper.deleteReportByReportIds(reportIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param reportId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportId(Long reportId)
    {
        return reportMapper.deleteReportByReportId(reportId);
    }
}

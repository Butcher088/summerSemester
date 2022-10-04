package com.fan.mapper;

import com.fan.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    /**
    * @Description: 进行举报
    * @Date:  2022/7/27 21:40
    **/
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

    /**
     * 修改【请填写功能名称】
     *
     * @param report 【请填写功能名称】
     * @return 结果
     */
//    public int updateReport(Report report);

    /**
     * 删除【请填写功能名称】
     *
     * @param reportId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteReportByReportId(Long reportId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param reportIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportByReportIds(Long[] reportIds);
}

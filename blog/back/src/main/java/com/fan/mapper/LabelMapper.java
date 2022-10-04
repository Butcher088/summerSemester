package com.fan.mapper;

import com.fan.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface LabelMapper {

    // 增加标签
    int addLabel(Label label);

    // 删除标签
    int deleteLabel(int labelId);

    // 改
    int updateLabel(Label label);

    // 查询所有存在的标签
    List<Label> queryAllLabel();

    // 根据标签ids查询文章的所有标签名
    List<String> queryLabelByIds(@Param("ids") List<Integer> ids);

    // 根据标签名数组查询标签Id
    ArrayList<Integer> queryLabelId(@Param("names") String[] names);

    /**
     * 查询【请填写功能名称】
     *
     * @param labelId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Label selectLabelByLabelId(Long labelId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param label 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Label> selectLabelList(Label label);

    /**
     * 新增【请填写功能名称】
     *
     * @param label 【请填写功能名称】
     * @return 结果
     */
    public int insertLabel(Label label);



    /**
     * 删除【请填写功能名称】
     *
     * @param labelId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteLabelByLabelId(Long labelId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param labelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabelByLabelIds(Long[] labelIds);
}

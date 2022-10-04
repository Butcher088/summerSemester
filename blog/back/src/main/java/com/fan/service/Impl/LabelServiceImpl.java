package com.fan.service.Impl;

import com.fan.entity.Label;
import com.fan.mapper.LabelMapper;
import com.fan.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    @Override
    public int addLabel(Label label) {
        return 0;
    }

    @Override
    public int deleteLabel(int labelId) {
        return 0;
    }

    @Override
    public int updateLabel(Label label) {
        return 0;
    }

    @Override
    public List<Label> queryAllLabel() {
        return labelMapper.queryAllLabel();
    }

    @Override
    public List<Label> queryLabelByIds(List<Integer> ids) {
        return null;
    }


    /**
     * 查询【请填写功能名称】
     *
     * @param labelId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Label selectLabelByLabelId(Long labelId)
    {
        return labelMapper.selectLabelByLabelId(labelId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param label 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Label> selectLabelList(Label label)
    {
        return labelMapper.selectLabelList(label);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param label 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertLabel(Label label)
    {
        return labelMapper.insertLabel(label);
    }


    /**
     * 批量删除【请填写功能名称】
     *
     * @param labelIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteLabelByLabelIds(Long[] labelIds)
    {
        return labelMapper.deleteLabelByLabelIds(labelIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param labelId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteLabelByLabelId(Long labelId)
    {
        return labelMapper.deleteLabelByLabelId(labelId);
    }

}

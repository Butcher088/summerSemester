package com.fan.controller;

import com.fan.entity.Label;
import com.fan.service.Impl.LabelServiceImpl;
import com.fan.utils.AjaxResult;
import com.fan.utils.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController  {

    @Autowired
    LabelServiceImpl labelService;

    @GetMapping("/queryAllLabel")
    public AjaxResult queryAllLabel() {
        List<Label> list = labelService.queryAllLabel();
        return AjaxResult.success("操作成功",list);
    }

    @GetMapping("/admin/queryAllLabel")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult queryAdminAllLabel() {
        List<Label> list = labelService.queryAllLabel();
        return AjaxResult.success("操作成功",list);
    }

    @GetMapping( "/admin/label")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult selectLabelList(@Param("name") String name) {
        Label label = new Label();
        label.setName(name);
        System.out.println("labelName"+name);
        System.out.println("执行selectLabelList"+labelService.selectLabelList(label));
        return  new ResponseResult(200,labelService.selectLabelList(label));
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/admin/label/{labelId}")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult getInfo(@PathVariable("labelId") Long labelId)
    {
        System.out.println("labelId="+labelId);
        return AjaxResult.success(labelService.selectLabelByLabelId(labelId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping("/admin/label")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult add(@RequestBody Label label)
    {
        return new ResponseResult(200,labelService.insertLabel(label));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping("/admin/label")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult edit(@RequestBody Label label)
    {
        return new ResponseResult(200,labelService.updateLabel(label));
    }

    /**
     * 删除【请填写功能名称】
     */
    @DeleteMapping("/admin/label/{labelIds}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult remove(@PathVariable Long[] labelIds)
    {
        return new ResponseResult(200,labelService.deleteLabelByLabelIds(labelIds));
    }

}

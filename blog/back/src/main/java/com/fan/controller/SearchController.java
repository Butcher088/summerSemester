package com.fan.controller;

import com.fan.entity.Report;
import com.fan.entity.Search;
import com.fan.service.Impl.SearchServiceImpl;
import com.fan.service.SearchService;
import com.fan.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    SearchServiceImpl searchService;

//    // 增加一条搜索记录
//    public int addSearch(Search search);
//
//    // 清空所有搜索记录
//    public int deleteAllSearch(int userId);
//
//    // 查询个人所有记录
//    public List<Search> getAllSearch(int userId);

    @GetMapping("/getAllSearch")
    public AjaxResult getAllSearch(int userId) {

        List<String> list = searchService.getAllSearch(userId);

        return AjaxResult.success("执行成功",list);
    }
    @GetMapping("/deleteAllSearch")
    public AjaxResult deleteAllSearch(int userId) {

        int result = searchService.deleteAllSearch(userId);

        return AjaxResult.success("执行成功",result);
    }
    @GetMapping("/addSearch")
    public AjaxResult addSearch(Search search) {

        int result = searchService.addSearch(search);

        return AjaxResult.success("执行成功",result);
    }

}

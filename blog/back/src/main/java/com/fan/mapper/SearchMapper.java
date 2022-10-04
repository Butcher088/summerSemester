package com.fan.mapper;

import com.fan.entity.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    // 增加一条搜索记录
    public int addSearch(Search search);

    // 清空所有搜索记录
    public int deleteAllSearch(int userId);

    // 查询个人所有记录
    public List<String> getAllSearch(int userId);

}

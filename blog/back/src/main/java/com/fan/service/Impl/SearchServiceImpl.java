package com.fan.service.Impl;

import com.fan.entity.Search;
import com.fan.mapper.SearchMapper;
import com.fan.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;

    @Override
    public int addSearch(Search search) {
        return searchMapper.addSearch(search);
    }

    @Override
    public int deleteAllSearch(int userId) {
        return searchMapper.deleteAllSearch(userId);
    }

    @Override
    public List<String> getAllSearch(int userId) {
        return searchMapper.getAllSearch(userId);
    }
}

package com.fan.service.Impl;

import com.fan.entity.Collect;
import com.fan.entity.Follow;
import com.fan.mapper.CollectMapper;
import com.fan.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;

    @Override
    public int collect(Collect collect) {
        return collectMapper.collect(collect);
    }

    @Override
    public int cancelCollect(Collect collect) {
        return collectMapper.cancelCollect(collect);
    }

    @Override
    public int reCollect(Collect collect) {
        return collectMapper.reCollect(collect);
    }

    @Override
    public List<Collect> whetherCollect(int userId, int articleId) {
        return collectMapper.whetherCollect(userId, articleId);
    }
}

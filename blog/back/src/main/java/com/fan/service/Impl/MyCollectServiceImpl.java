package com.fan.service.Impl;

import com.fan.entity.Collect;
import com.fan.entity.User;
import com.fan.mapper.MyCollectMapper;
import com.fan.mapper.UserMapper;
import com.fan.service.MyCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MyCollectServiceImpl implements MyCollectService {
    @Autowired
    private MyCollectMapper myCollectMapper;

    @Override
    public List<Collect> selctCollectByUserId(int userId){
        return myCollectMapper.selctCollectByUserId(userId);
    };
}

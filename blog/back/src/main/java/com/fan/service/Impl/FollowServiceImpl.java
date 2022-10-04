package com.fan.service.Impl;

import com.fan.entity.Follow;
import com.fan.mapper.FollowMapper;
import com.fan.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowMapper followMapper;

    @Override
    public int FollowUser(Follow follow) {
        return followMapper.followUser(follow);
    }

    @Override
    public int cancelFollow(Follow follow) {
        return followMapper.cancelFollow(follow);
    }

    @Override
    public List<Follow> whetherFollow(int userId, int followedId) {
        return followMapper.whetherFollow(userId,followedId);
    }

    @Override
    public int reFollowUser(Follow follow) {
        return followMapper.reFollowUser(follow);
    }
}

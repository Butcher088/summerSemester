package com.fan.service;

import com.fan.entity.Follow;

import java.util.List;

public interface FollowService {
    /**
    * @Description: 关注用户
    * @Date:  2022/7/26 18:47
    **/
    int FollowUser(Follow follow);

    int cancelFollow(Follow follow);

    List<Follow> whetherFollow(int userId, int followedId);

    int reFollowUser(Follow follow);

}

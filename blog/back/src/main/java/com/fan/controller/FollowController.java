package com.fan.controller;

import com.fan.entity.Follow;
import com.fan.service.FollowService;
import com.fan.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("follow")
@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("followUser")
    public AjaxResult followUser(int userId, int followedId){
        //先查询数据库中有没有记录，确定是insert还是update
        int i = followService.whetherFollow(userId,followedId).toArray().length;

        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFollowedId(followedId);
        follow.setTime(new Date());

        //update
        if(i == 1){
            followService.reFollowUser(follow);
            return AjaxResult.success("关注成功");
        }
        //insert
        else {
            follow.setStatus(1);
            followService.FollowUser(follow);
            return AjaxResult.success("关注成功");
        }

    }

    @GetMapping("cancelFollow")
    public AjaxResult cancelFollow(int userId, int followedId){
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFollowedId(followedId);
        follow.setTime(new Date());
        followService.cancelFollow(follow);
        return AjaxResult.success("取关成功");
    }

    @GetMapping("whetherFollow")
    public AjaxResult whetherFollow(int userId, int followedId){
        List<Follow> followList = followService.whetherFollow(userId, followedId);
        followList.removeIf(item -> item.getStatus()==0);
        int i = followList.toArray().length;
        return AjaxResult.success("判断成功",i);
    }
}

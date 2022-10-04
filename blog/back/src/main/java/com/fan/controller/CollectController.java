package com.fan.controller;

import com.fan.entity.Collect;
import com.fan.entity.Follow;
import com.fan.mapper.ArticleMapper;
import com.fan.service.CollectService;
import com.fan.service.FollowService;
import com.fan.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/collect")
    public AjaxResult collect(int userId, int articleId, int collectedId){

        //先查询数据库中有没有记录，确定是insert还是update
        int i = collectService.whetherCollect(userId,articleId).toArray().length;

        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setArticleId(articleId);
        collect.setCollectedId(collectedId);
        collect.setTime(new Date());

        articleMapper.addCollect(articleId);

        //update
        if(i == 1){
            collectService.reCollect(collect);
            return AjaxResult.success("收藏成功");
        }
        //insert
        else {
            collect.setStatus(1);
            collectService.collect(collect);
            return AjaxResult.success("收藏成功");
        }
    }

    @GetMapping("/cancelCollect")
    public AjaxResult cancelCollect(int userId, int articleId){
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setArticleId(articleId);
        collect.setTime(new Date());
        collectService.cancelCollect(collect);
        articleMapper.cancelCollect(articleId);
        return AjaxResult.success("取消收藏成功");
    }

    @GetMapping("/whetherCollect")
    public AjaxResult whetherCollect(int userId, int articleId){
        List<Collect> list = collectService.whetherCollect(userId, articleId);
        list.removeIf(item -> item.getStatus() == 0);
        int i = list.toArray().length;
        return AjaxResult.success("判断成功",i);
    }
}

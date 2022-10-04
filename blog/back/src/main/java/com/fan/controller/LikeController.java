package com.fan.controller;

import com.fan.entity.Like;
import com.fan.mapper.ArticleMapper;
import com.fan.service.LikeService;
import com.fan.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/like")
    public AjaxResult like(int userId, int articleId, int likedId){

        //先查询数据库中有没有记录，确定是insert还是update
        int i = likeService.whetherLike(userId, articleId).toArray().length;

        Like like = new Like();
        like.setUserId(userId);
        like.setArticleId(articleId);
        like.setLikedId(likedId);
        like.setTime(new Date());

        articleMapper.addLike(articleId);

        //update
        if(i == 1){
            likeService.reLike(like);
            return AjaxResult.success("点赞成功");
        }
        //insert
        else {
            like.setStatus(1);
            likeService.like(like);
            return AjaxResult.success("点赞成功");

        }
    }

    @GetMapping("cancelLike")
    public AjaxResult cancelLike(int userId, int articleId){
        Like like = new Like();
        like.setUserId(userId);
        like.setArticleId(articleId);
        like.setTime(new Date());
        likeService.cancelLike(like);
        articleMapper.cancelLike(articleId);
        return AjaxResult.success("取消点赞成功");

    }

    @GetMapping("whetherLike")
    public AjaxResult whetherLike(int userId, int articleId){
        List<Like> list = likeService.whetherLike(userId, articleId);
        list.removeIf(item -> item.getStatus() == 0);
        int i = list.toArray().length;
        return AjaxResult.success("判断成功",i);
    }
}

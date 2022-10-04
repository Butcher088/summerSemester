package com.fan.service;

import com.fan.entity.Comment;
import com.fan.entity.Like;
import com.fan.entity.User;

import java.util.List;

public interface CommentService {

    // 根据id查询文章评论
    List<Comment> getCommentsById(int articleId) ;

    // 插入评论
    int insertComment(Comment comment);

    // 获取用户信息
    User getUserById(int id);

    // 点赞
    int giveLikeToComment(Like like);

    // 判断我是否点赞了
    Like isMyLikeComment(Like like);

    // 取消点赞
    int deleteMyLikeComment(Like like);

}

package com.fan.mapper;

import com.fan.entity.Comment;
import com.fan.entity.Like;
import com.fan.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 根据id查询文章评论
    List<Comment> getCommentsById(int articleId) ;

    // 根据id查询文章评论
    int getCommentsCountById(int articleId) ;

    // 插入评论
    int insertComment(Comment comment);

    // 获取用户信息
    User getUserById(int id);

    // 点赞
    int giveLikeToComment(Like like);

    // 查询我是否点了赞
    Like isMyLikeComment(Like like);

    // 给评论+1
    int addCommentLike(Like like);

    // 取消点赞
    int deleteMyLikeComment(Like like);

    // 给评论总数-1
    int subCommentLike(Like like);
}

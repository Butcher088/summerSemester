package com.fan.service.Impl;

import com.fan.entity.Comment;
import com.fan.entity.Like;
import com.fan.entity.User;
import com.fan.mapper.CommentMapper;
import com.fan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsById(int articleId) {
        return commentMapper.getCommentsById(articleId);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    // 获取用户信息
    @Override
    public User getUserById(int id){
        return commentMapper.getUserById(id);
    }

    @Override
    public int giveLikeToComment(Like like) {
        commentMapper.addCommentLike(like);
        return commentMapper.giveLikeToComment(like);
    }

    @Override
    public Like isMyLikeComment(Like like) {
        return commentMapper.isMyLikeComment(like);
    }

    @Override
    public int deleteMyLikeComment(Like like) {
        commentMapper.subCommentLike(like);
        return commentMapper.deleteMyLikeComment(like);
    }

}

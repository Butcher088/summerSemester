package com.fan.service.Impl;


import com.fan.entity.Like;
import com.fan.mapper.LikeMapper;
import com.fan.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public int like(Like like) {
        return likeMapper.like(like);
    }

    @Override
    public int cancelLike(Like like) {
        return likeMapper.cancelLike(like);
    }

    @Override
    public int reLike(Like like) {
        return likeMapper.reLike(like);
    }

    @Override
    public List<Like> whetherLike(int userId, int articleId) {
        return likeMapper.whetherLike(userId, articleId);
    }
}

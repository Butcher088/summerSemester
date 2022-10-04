package com.fan.service.Impl;

import com.fan.entity.*;
import com.fan.mapper.ClassifyMapper;
import com.fan.mapper.CreateMapper;
import com.fan.mapper.LabelMapper;
import com.fan.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateServiceImpl implements CreateService {

    @Autowired
    CreateMapper createMapper;


    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    LabelMapper labelMapper;

    @Override
    public List<Article> getArticleListByUser(int userId) {

        List<Article> list = createMapper.getArticleListByUser(userId);
        for(Article article : list) {

            int articleId = article.getArticleId();
            // 根据文章id查对应的标签Id数组
            List<Integer> ids = classifyMapper.queryLabelId(articleId);
            // 查询id对应的标签名
            List<String> labels = labelMapper.queryLabelByIds((ids));
            // 将标签名给实体，传给前端
            article.setLabels(labels);

        }
        return list;
    }

    @Override
    public List<Follow> getFollowByUser(int userId) {
        return createMapper.getFollowByUser(userId);
    }

    @Override
    public List<User> getUserById(List<Integer> ids) {
        //如果列表长度为0，不要sql查询，直接返回空列表
        if(ids.toArray().length == 0){
            List<User> res = new ArrayList<>();
            return  res;
        }
        return createMapper.getUserById(ids);
    }

    @Override
    public List<Like> getLikeListByUser(int userId) {
        return createMapper.getLikeListByUser(userId);
    }

    @Override
    public List<Comment> getCommentListByUser(int userId) {
        return createMapper.getCommentListByUser(userId);
    }

    @Override
    public List<Collect> getCollectListByUser(int userId) {
        return createMapper.getCollectListByUser(userId);
    }

    @Override
    public List<Follow> getFansByUser(int userId) {
        return createMapper.getFansByUser(userId);
    }

    @Override
    public List<Click> getClickListByUser(int userId) {
        return createMapper.getClickListByUser(userId);
    }

    @Override
    public List<Article> searchArticle(String title, int userId) {
        return createMapper.searchArticle(title,userId);
    }

    @Override
    public List<Draft> getDraftListByUser(int userId) {
        return createMapper.getDraftListByUser(userId);
    }

    @Override
    public Draft getDraftArticleById(int articleId) {
        return createMapper.getDraftArticleById(articleId);
    }

    @Override
    public List<Draft> searchDraft(String title, int userId) {
        return createMapper.searchDraft(title,userId);
    }

    @Override
    public int deleteDraft(int articleId) {
        return createMapper.deleteDraft(articleId);
    }
}

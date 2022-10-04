package com.fan.service.Impl;

import com.fan.entity.Article;
import com.fan.entity.Label;
import com.fan.entity.User;
import com.fan.mapper.ArticleMapper;
import com.fan.mapper.ClassifyMapper;
import com.fan.mapper.CommentMapper;
import com.fan.mapper.LabelMapper;
import com.fan.service.ArticleService;
import com.fan.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    private CreateService createService;

    @Override
    public int addArticle(Article article) {

        // 插入文章
        articleMapper.addArticle(article);
//        System.out.println("文章实体id: " + article.getArticleId() + "===========");

        // 根据标签名查询标签ids
        ArrayList<Integer> ids = labelMapper.queryLabelId(article.getLabels());
        // 给文章添加标签
        classifyMapper.addClassify(article.getArticleId(),ids);

        return 0;
    }

    @Override
    public int deleteArticle(int id) {
        return articleMapper.deleteArticle(id);
    }

    @Override
    public int updateArticle(Article article) {
        return 0;
    }


    @Override
    public List<Article> queryAllArticle(Map<String,Object> map) {
        // map传来，判定是需要分页

        System.out.println("Impl里的map:" + map);
        List<Article> list = articleMapper.queryAllArticle(map);

        for(Article article : list) {

            int articleId = article.getArticleId();
            List<String> labels = classifyMapper.getAllLabelsByArticleId(articleId);
            // 将标签名给实体，传给前端
            article.setLabels(labels);

            int comment_count = commentMapper.getCommentsCountById(articleId);
            article.setComment_count(comment_count);

            List<Integer> userIds = new ArrayList<>();
            userIds.add(article.getUserId());
            List<User> user = createService.getUserById(userIds);
            article.setUserName(user.get(0).getUserName());
            article.setImg(user.get(0).getImg());
        }
        return list;
    }

    @Override
    public List<Article> queryAllArticleByTitle(Map<String, Object> map) {
        List<Article> list = articleMapper.queryAllArticleByTitle(map);
        for(Article article : list) {

            int articleId = article.getArticleId();
            List<String> labels = classifyMapper.getAllLabelsByArticleId(articleId);
            // 将标签名给实体，传给前端
            article.setLabels(labels);

            int comment_count = commentMapper.getCommentsCountById(articleId);
            article.setComment_count(comment_count);

            List<Integer> userIds = new ArrayList<>();
            userIds.add(article.getUserId());
            List<User> user = createService.getUserById(userIds);
            article.setUserName(user.get(0).getUserName());
            article.setImg(user.get(0).getImg());
        }
        return list;
    }

    @Override
    public Article getArticleById(int id) {
        return articleMapper.getArticleById(id);
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    @Override
    public int addViews(int articleId) {
        return articleMapper.addViews(articleId);
    }

    @Override
    public int getCommentCount(int articleId) {
        return articleMapper.getCommentCount(articleId);
    }

    @Override
    public int getLikeCount(int articleId) {
        return articleMapper.getLikeCount(articleId);
    }


}

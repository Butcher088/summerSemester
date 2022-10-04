package com.fan.service;

import com.fan.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    // 增加
    int addArticle(Article article);

    // 删除
    int deleteArticle(int id);

    // 编辑
    int updateArticle(Article article);

    // 查询所有
    List<Article> queryAllArticle(Map<String,Object> map);


    // 分类查询所有
    List<Article> queryAllArticleByTitle(Map<String,Object> map);

    //
    Article getArticleById(int id);

    /**
    * @Description: 文章浏览量增加
    * @Date:  2022/7/27 12:45
    **/
    int addViews(int articleId);

    /**
    * @Description: 文章评论数
    * @Date:  2022/7/27 14:04
    **/
    int getCommentCount(int articleId);

    /**
    * @Description: 文章点赞数
    * @Date:  2022/7/27 15:58
    **/
    int getLikeCount(int articleId);
}

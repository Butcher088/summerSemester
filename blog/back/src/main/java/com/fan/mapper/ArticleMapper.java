package com.fan.mapper;

import com.fan.entity.Article;
import com.fan.entity.Draft;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    // 增加
    int addArticle(Article article);

    // 删除
    int deleteArticle(@Param("articleId") int id);

    // 编辑
    int updateArticle(Article article);

    // 查询所有
    List<Article> queryAllArticle(Map<String,Object> map);

    // 分类查询所有
    List<Article> queryAllArticleByTitle(Map<String,Object> map);

    // 根据id查询
    Article getArticleById(@Param("articleId") int articleId);

    // 按标签分类查询


    /**
     * @Description: 文章浏览量增加
     * @Date:  2022/7/27 12:45
     **/
    int addViews(int articleId);

    /**
    * @Description: 获取文章的所有评论数
    * @Date:  2022/7/27 14:02
    **/
    int getCommentCount(int articleId);

    /**
    * @Description: 获取文章的所有点赞数
    * @Date:  2022/7/27 15:57
    **/
    int getLikeCount(int articleId);

    // 点赞加一
    int addLike(int articleId);

    // 取消点赞
    int cancelLike(int articleId);

    // 收藏加一
    int addCollect(int articleId);

    // 取消收藏
    int cancelCollect(int articleId);

    // 保存为草稿
    int addDraft(Draft draft);
}

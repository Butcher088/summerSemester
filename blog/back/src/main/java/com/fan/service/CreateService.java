package com.fan.service;

import com.fan.entity.*;

import java.util.List;

public interface CreateService {

    /**
    * @Description: 获取用户发布的所有文章
    * @Date:  2022/7/25 10:22
    **/
    List<Article> getArticleListByUser(int user_id);

    /**
     * @Description: 获取用户的关注用户id
     * @Date:  2022/7/25 10:48
     **/
    List<Follow> getFollowByUser(int userId);

    /**
     * @Description: 根据id获取用户信息
     * @Date:  2022/7/25 11:17
     **/
    List<User> getUserById(List<Integer> ids);

    /**
    * @Description: 获取用户的获赞信息
    * @Date:  2022/7/25 12:45
    **/
    List<Like> getLikeListByUser(int userId);

    /**
    * @Description: 获取用户的评论信息
    * @Date:  2022/7/25 13:01
    **/
    List<Comment> getCommentListByUser(int userId);

    /**
     * @Description: 获取用户的被收藏信息
     * @Date:  2022/7/25 13:18
     **/
    List<Collect> getCollectListByUser(int userId);

    /**
     * @Description: 获取用户的粉丝列表
     * @Date:  2022/7/25 13:27
     **/
    List<Follow> getFansByUser(int userId);

    /**
     * @Description: 获取用户文章的被浏览信息
     * @Date:  2022/7/25 13:33
     **/
    List<Click> getClickListByUser(int userId);

    /**
    * @Description: 文章列表模糊查询
    * @Date:  2022/7/26 19:33
    **/
    List<Article> searchArticle(String title, int userId);

    /**
    * @Description: 获取用户的的草稿箱列表
    * @Date:  2022/7/27 8:52
    **/
    List<Draft> getDraftListByUser(int userId);

    /**
    * @Description: 获取草稿箱的文章信息
    * @Date:  2022/7/27 9:26
    **/
    Draft getDraftArticleById(int articleId);

    /**
     * @Description: 草稿箱列表模糊查询
     * @Date:  2022/7/27 9:40
     **/
    List<Draft> searchDraft(String title, int userId);

    /**
     * @Description: 删除草稿
     * @Date:  2022/7/27 9:41
     **/
    int deleteDraft(int articleId);
}

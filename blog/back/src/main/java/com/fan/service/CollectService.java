package com.fan.service;

import com.fan.entity.Collect;
import com.fan.entity.Follow;

import java.util.List;

public interface CollectService {

    /**
     * @Description: 收藏文章
     * @Date:  2022/7/27 12:56
     **/
    int collect(Collect collect);

    /**
     * @Description: 取消收藏
     * @Date:  2022/7/27 12:57
     **/
    int cancelCollect(Collect collect);

    /**
     * @Description: 重新收藏
     * @Date:  2022/7/27 12:57
     **/
    int reCollect(Collect collect);

    /**
     * @Description: 查看是否收藏
     * @Date:  2022/7/27 12:58
     **/
    List<Collect> whetherCollect(int userId, int articleId);
}

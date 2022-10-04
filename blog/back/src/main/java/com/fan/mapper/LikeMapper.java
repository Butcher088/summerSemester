package com.fan.mapper;

import com.fan.entity.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    /**
    * @Description: 点赞
    * @Date:  2022/7/27 16:10
    **/
    int like(Like like);

    /**
    * @Description: 取消点赞
    * @Date:  2022/7/27 16:11
    **/
    int cancelLike(Like like);

    /**
    * @Description: 再次点赞
    * @Date:  2022/7/27 16:12
    **/
    int reLike(Like like);

    /**
    * @Description: 检查是否已经点赞
    * @Date:  2022/7/27 16:12
    **/
    List<Like> whetherLike(int userId, int articleId);
}

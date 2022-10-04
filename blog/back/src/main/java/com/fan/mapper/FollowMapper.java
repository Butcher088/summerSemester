package com.fan.mapper;

import com.fan.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    /**
    * @Description: 关注用户
    * @Date:  2022/7/26 18:42
    **/
    int followUser(Follow follow);

    /**
    * @Description: 取消关注
    * @Date:  2022/7/26 19:03
    **/
    int cancelFollow(Follow follow);

    /**
    * @Description: 查看是否已经关注
    * @Date:  2022/7/27 11:03
    **/
    List<Follow> whetherFollow(int userId, int followedId);

    /**
    * @Description: 如果用户再次关注用户，只需要update status即可
    * @Date:  2022/7/27 11:35
    **/
    int reFollowUser(Follow follow);

}

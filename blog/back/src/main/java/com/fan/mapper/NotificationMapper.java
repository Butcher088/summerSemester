package com.fan.mapper;

import com.fan.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    /**
    * @Description: 获取全部回复
    * @Date:  2022/7/28 21:11
    **/
    List<Comment> getAllComment(int userId);
}

package com.fan.mapper;


import com.fan.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyCollectMapper {

    List<Collect> selctCollectByUserId(int userId);
}

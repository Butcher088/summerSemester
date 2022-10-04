package com.fan.service;

import com.fan.entity.Collect;

import java.util.List;

public interface MyCollectService {

    List<Collect> selctCollectByUserId(int userId);
}

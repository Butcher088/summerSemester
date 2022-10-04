package com.fan;

import com.fan.entity.Search;
import com.fan.mapper.SearchMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchMapperTest extends TestCase {

//    // 增加一条搜索记录
//    public int addSearch(Search search);
//
//    // 清空所有搜索记录
//    public int deleteAllSearch(int userId);
//
//    // 查询个人所有记录
//    public List<Search> getAllSearch(int userId);

    @Autowired
    SearchMapper searchMapper;

    @Test
    public void addSearch() {
        Search search = new Search(0,"test",1);
        searchMapper.addSearch(search);
    }
    @Test
    public void deleteAllSearch() {
        searchMapper.deleteAllSearch(1);
    }
    @Test
    public void getAllSearch() {
        System.out.println(searchMapper.getAllSearch(1));
    }



}

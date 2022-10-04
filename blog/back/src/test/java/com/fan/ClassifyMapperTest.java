package com.fan;

import com.fan.mapper.ClassifyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassifyMapperTest {

    @Autowired
    ClassifyMapper classifyMapper;

    @Test
    public void addClassify() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(6);
        ids.add(5);

        classifyMapper.addClassify(29,ids);
    }

    // 查 查询一个文章的所有标签id
    @Test
    public void queryLabelId() {
        List<Integer> list = classifyMapper.queryLabelId(1);
        System.out.println("打印：" + list);
    }

    // 查询文章对应的所有标签名
    @Test
    public void getAllLabelsByArticleId() {
        int articleId = 105;
        List<String> names = classifyMapper.getAllLabelsByArticleId(articleId);
        System.out.println(names);
    }

}

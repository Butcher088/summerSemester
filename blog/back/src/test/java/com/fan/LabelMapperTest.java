package com.fan;

import com.fan.entity.Label;
import com.fan.mapper.LabelMapper;
import io.swagger.models.auth.In;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelMapperTest extends TestCase {

    @Autowired
    LabelMapper labelMapper;

    @Test
    public void addLabel() {
        Label label = new Label();
//        label.setLabelId(6); 可以传空值，会自增
        label.setName("test no id");
        labelMapper.addLabel(label);
    }

    @Test
    public void queryAllLabel() {
        List<Label> labelList = labelMapper.queryAllLabel();

        System.out.println(labelList);
    }

    @Test
    public void queryLabelByIds() {

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);

        List<String> list = labelMapper.queryLabelByIds(ids);
        for (String name : list) {
            System.out.println(name.toString());
        }
    }

    @Test
    public void queryLabelId() {

        String[] names = new String[2];
       names[0] = "前端";
        names[1] = "后端";

        ArrayList<Integer> integerList = labelMapper.queryLabelId(names);
        System.out.println(integerList.toString());
    }


}

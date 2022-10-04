package com.fan.mapper;

import com.fan.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClassifyMapper {

    // 增 给文章添加标签
    int addClassify(int articleId, ArrayList<Integer> ids);

    // 删 删除文章所有标签
    int deleteClassify(int articleId);

    // 改

    // 查 查询一个文章的所有标签id
    List<Integer> queryLabelId(int articleId);

    // 查询文章对应的所有标签名
    List<String> getAllLabelsByArticleId(int articleId);

    // 查询标签对应的所有文章
    List<Article> getAllArticleIdByLabels(String label);

}

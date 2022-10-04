package com.fan.controller;

import com.fan.entity.Article;
import com.fan.entity.Collect;
import com.fan.mapper.ClassifyMapper;
import com.fan.mapper.LabelMapper;
import com.fan.service.Impl.ArticleServiceImpl;
import com.fan.service.Impl.MyCollectServiceImpl;
import com.fan.service.Impl.UserServiceImpl;
import com.fan.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MyCollectController {
    @Autowired
    private MyCollectServiceImpl myCollectService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    LabelMapper labelMapper;

    @GetMapping("/collectSelect")
    public AjaxResult selctCollectByUserId(int userId) {
        System.out.println(userId);
        List<Collect> list = myCollectService.selctCollectByUserId(userId);
        System.out.println("list==="+list.size());

        for(int i=0;i<list.size();i++){
//            System.out.println("第几次===========   "+i);
            int collectId = list.get(i).getCollectedId();
            String a = userService.selectById(collectId).getUserName();//用户名
            list.get(i).setUserName(a);
            String b = userService.selectById(collectId).getImg();//头像
            list.get(i).setImg(b);

            int articleId = list.get(i).getArticleId();
//            System.out.println("articleId------"+articleId);
            Article article = articleService.getArticleById(articleId);
//            System.out.println("查到的article"+article);
            // 根据文章id查对应的标签Id数组
            List<Integer> ids = classifyMapper.queryLabelId(articleId);
            // 查询id对应的标签名
            List<String> labels = labelMapper.queryLabelByIds((ids));
//            System.out.println("labels==="+labels);
            // 将标签名给实体，传给前端
            article.setLabels(labels);

            list.get(i).setArticle(article);


        }

        if(list != null){
            return AjaxResult.success("查询成功",list);
        }else{
            return AjaxResult.error("无收藏");
        }
    }
}

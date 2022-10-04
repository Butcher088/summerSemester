package com.fan.controller;


import com.fan.entity.Article;
import com.fan.entity.Draft;
import com.fan.mapper.ArticleMapper;
import com.fan.mapper.ClassifyMapper;
import com.fan.utils.AjaxResult;
import com.fan.service.Impl.ArticleServiceImpl;
import com.fan.utils.JwtUtil;
import com.fan.utils.RedisCache;
import com.fan.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class ArticleController {

    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    ClassifyMapper classifyMapper;
    @Autowired
    RedisCache redisCache;
    @Autowired
    ArticleMapper articleMapper;

    @PostMapping("/addArticle")
    public AjaxResult addArticle(@RequestBody Article article) {

        System.out.println("article=="+article.toString());

        articleService.addArticle(article);

        return AjaxResult.success("操作成功");
    }

    @GetMapping("/deleteArticle")
    public AjaxResult deleteArticle() {
        int a  = articleService.deleteArticle(1);
        System.out.println(a);
        return AjaxResult.success("操作成功");
    }

    @PostMapping("/getAll")
    public AjaxResult getAll(@RequestBody HashMap<String,Object> map) {

        System.out.println("map为：" + map);

        if(redisCache.getCacheObject("allArticle") != null) {
            return AjaxResult.
                    success("操作成功",redisCache.getCacheObject("allArticle"));
        } else {
            List<Article> list = articleService.queryAllArticle(map);
            Integer time = new Integer(3);
            redisCache.setCacheObject("allArticle",list, time,TimeUnit.MINUTES);
            return AjaxResult.success("操作成功",list);
        }

    }

    @GetMapping("/getAllByTitle")
    public AjaxResult getAllByTitle(String title,int startIndex,int pageSize) {

//        System.out.println("参数为：" + title + startIndex);
        String key = title + startIndex + pageSize;

        if(redisCache.getCacheObject(key) != null) {
            System.out.println("======== 走缓存了 ===========");
            return AjaxResult.success("操作成功",redisCache.getCacheObject(key));
        } else {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("title",title);
            map.put("startIndex",startIndex);
            map.put("pageSize",pageSize);
            System.out.println("生成的map：" + map);
            List<Article> list = articleService.queryAllArticleByTitle(map);
            // 时间颗粒度为分钟 过期时间为3分钟
            Integer time = new Integer(3);
            redisCache.setCacheObject(key,list,time, TimeUnit.MINUTES);
            System.out.println("======== 没走缓存 ===========");
            return AjaxResult.success("操作成功",list);
        }

    }

    @GetMapping("/getArticleById")
    public AjaxResult getArticleById(int articleId) {
        Article article = articleService.getArticleById(articleId);
        return AjaxResult.success("操作成功",article);
    }

    @GetMapping("/getHeatArticle")
    public AjaxResult getHeatArticle(){

        if(redisCache.getCacheObject("heatArticle") != null) {
            return AjaxResult.
                    success("操作成功",redisCache.getCacheObject("heatArticle"));
        } else {
            Map<String,Object> map = new HashMap<String,Object>();
            List<Article> list = articleService.queryAllArticle(map);
            //按点赞排序
            list = list.stream().sorted(Comparator.comparing(Article::getLikeCount)
                    .reversed())
                    .collect(Collectors.toList());
            redisCache.setCacheObject("heatArticle",list.subList(0,5));
            return AjaxResult.success("操作成功",list.subList(0,5));
        }

    }

    @GetMapping("/addViews")
    public AjaxResult addViews(int articleId){
        int i = articleService.addViews(articleId);
        return AjaxResult.success("点击量+1",i);
    }

    @GetMapping("/getCommentCount")
    public AjaxResult getCommentCount(int articleId){
        int i = articleService.getCommentCount(articleId);
        return AjaxResult.success("查询评论数成功",i);
    }

    @GetMapping("/getLikeCount")
    public AjaxResult getLikeCount(int articleId){
        int i = articleService.getLikeCount(articleId);
        return AjaxResult.success("查询点赞数成功",i);
    }


    @GetMapping("/getLabelsById")
    public AjaxResult getAllLabelsByArticleId(int articleId){

        List<String> names = classifyMapper.getAllLabelsByArticleId(articleId);
        return AjaxResult.success("操作成功",names);
    }


    @PostMapping("/addDraft")
    public AjaxResult addDraft(Draft draft){

        System.out.println(draft.toString());
        System.out.println("请求了接口");
//        System.out.println("map为" + map);
        articleMapper.addDraft(draft);

        return AjaxResult.success("操作成功");
    }

    @PostMapping("/getRelative")
    public AjaxResult getRelative(String[] labels){

        System.out.println("接受参数" + labels[0]);

        List<Article> articles =   classifyMapper.getAllArticleIdByLabels(labels[0]);
        System.out.println("打印：" + articles);

        return AjaxResult.success("操作成功",articles);
    }
}

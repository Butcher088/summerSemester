package com.fan;

import com.fan.entity.Article;
import com.fan.entity.Comment;
import com.fan.mapper.ArticleMapper;
import com.fan.mapper.CommentMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest extends TestCase {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommentMapper commentMapper;



    @Test
    public void addArticle() {
        Date date =new Date();
        Timestamp createTime = new Timestamp(date.getTime());
        System.out.println("=======================]");
        System.out.println(createTime);
        System.out.println("=======================]");
        // 输出正确，插入时数据出错？
//        Article article = new Article(12,2,createTime,"<h1><a id=\"_0\"></a>标题</h1>\n" +
//                "<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> \n" +
//                "</code></div></pre>",
//                "title",1,2,4,createTime);
//        articleMapper.addArticle(article);
    }

    @Test
    public void deleteArticle() {
         articleMapper.deleteArticle(12);
    }

    @Test
    public void updateArticle() {

    }
    @Test
    public void queryAllArticle() {
        System.out.println("=======================]");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("startIndex",0);
        map.put("pageSize",10);
        System.out.println(articleMapper.queryAllArticle(map));
        System.out.println("=======================]");
    }
    @Test
    public void queryAllArticleByTitle() {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("title","最新");
        List<Article> articles = articleMapper.queryAllArticleByTitle(map);
        System.out.println(articles);
    }

    @Test
    public void getArticleById() {
        Article article = articleMapper.getArticleById(1);
        System.out.println(article.toString());
    }
    @Test
    public void getCommentsById() {
        System.out.println(commentMapper.getCommentsById(1));
    }



    @Test
    public void insertComment() {
        Comment comment = new Comment();
        comment.setFrom_id(3);
        comment.setContent("这是一个新评论");
        comment.setArticle_id(2);
        commentMapper.insertComment(comment);
    }


}

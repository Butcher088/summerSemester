package com.fan.controller;


import com.fan.entity.Comment;
import com.fan.entity.Like;
import com.fan.entity.User;
import com.fan.service.CommentService;
import com.fan.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "评论")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public List<Comment> getCommentsById(@ApiParam(value = "文章id") @PathVariable("id") int id,@ApiParam(value = "登陆人id") @Param("myId") int myId) {
//        System.out.println("欢迎你"+myId+"正在看文章："+id);
        List<Comment> commentList = commentService.getCommentsById(id);
        System.out.println(commentList);
        // 把comment_id和comment放入map，快速获取到Comment
        Map<Integer, Comment> map = new HashMap<>();
        // 最终要返回的List
        List<Comment> result = new ArrayList<>();
        for(Comment comment:commentList) {

            // 判断每条评论我是否点了赞
            Like like = new Like();
            like.setCommentId(comment.getComment_id());
            like.setUserId(myId);
            like.setArticleId(id);
            if(commentService.isMyLikeComment(like) != null) {
                comment.setIsYouLike(1);
//                System.out.println(comment.getComment_id()+" 你点赞了");
            } else {
                comment.setIsYouLike(0);
//                System.out.println(comment.getComment_id()+" 你没点赞");
            }


            // 如果是父评论直接放入result列表
            if(comment.getFather_id() == null ){
//                System.out.println(comment.getFather_id());
                result.add(comment);
            }
            map.put(comment.getComment_id(),comment);
        }
//        System.out.println("第一个 "+result);
        // 再次遍历，子评论放入到父评论的child中
        for(Comment comment:commentList) {
            if(comment.getFather_id() != null ) {
                Comment parentComment = map.get(comment.getFather_id());
                // 设置回复的内容
                comment.setFather_content(parentComment.getContent());
                comment.setFather_name(parentComment.getFrom_name());
            }
            Integer rootId = comment.getRoot_id();
            if(rootId != null) {
                Comment p = map.get(rootId);
                if(p.getChild() == null ) {
                    p.setChild(new ArrayList<>());
                }
                p.getChild().add(comment);
            }
        }
//        System.out.println("第二个 "+result);
        return result;
    }

    @PostMapping("/submit")
    public AjaxResult insertComment(@RequestBody Comment comment) {
        System.out.println(comment);
        int result = commentService.insertComment(comment);
        System.out.println(result);
        if(result == 1) {
            return AjaxResult.success("插入成功");
        } else {
            return new AjaxResult(401,"插入失败");
        }

    }

    @GetMapping("/user/{id}")
    public AjaxResult getUserById(@PathVariable("id") int id) {
        User user = commentService.getUserById(id);
        return AjaxResult.success(user);
    }

    @PostMapping("/giveLike")
    public AjaxResult giveLikeToComment(@RequestBody Like like) {
        System.out.println(like);
        int result = commentService.giveLikeToComment(like);
        if(result == 1) {
            return AjaxResult.success("点赞成功");
        } else {
            return new AjaxResult(401,"点赞失败");
        }
    }

    @PostMapping("/cancelLike")
    public AjaxResult deleteMyLikeComment(@RequestBody Like like) {
        System.out.println(like);
        int result = commentService.deleteMyLikeComment(like);
        if(result == 1) {
            return AjaxResult.success("取消点赞成功");
        } else {
            return new AjaxResult(401,"取消点赞失败");
        }
    }
}

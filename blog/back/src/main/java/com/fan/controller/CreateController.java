package com.fan.controller;

import com.fan.entity.*;
import com.fan.service.CreateService;
import com.fan.service.Impl.ArticleServiceImpl;
import com.fan.utils.AjaxResult;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/create")
@RestController
public class CreateController {

    @Autowired
    private CreateService createService;

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/getArticleByUser")
    public AjaxResult getArticleByUser(int userId){
        //获取文章列表
        List<Article> list =  createService.getArticleListByUser(userId);
        //按时间排序
        list = list.stream().sorted(Comparator.comparing(Article::getCreateTime)
                        .reversed())
                .collect(Collectors.toList());
        //判断今日文章列表
        List<Article> todayList = new ArrayList<>();
        for (Article article : list){
            if(DateUtils.isSameDay(new Date(),article.getCreateTime())){
                todayList.add(article);
            }
        }
        //返回结果封装
        List<List<Article>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("文章列表操作成功",res);
    }

    @GetMapping("/getFollow")
    public AjaxResult getFollowByUser(int userId){

        List<Follow> followList = createService.getFollowByUser(userId);
        //去掉status=0的记录
        followList.removeIf(
                item -> item.getStatus() == 0
        );
        List<Integer> ids = new ArrayList<>();
        for (Follow item: followList){
            ids.add(item.getFollowedId());
        }
        List<User> list = createService.getUserById(ids);
        return AjaxResult.success("关注列表操作成功",list);
    }

    @GetMapping("/getLike")
    public AjaxResult getLikeListByUser(int userId){
        //获取全部获赞
        List<Like> list = createService.getLikeListByUser(userId);
        //获取今日获赞
        List<Like> todayList = new ArrayList<>();
        for (Like like : list){
            if(DateUtils.isSameDay(new Date(),like.getTime())){
                todayList.add(like);
            }
        }
        //封装结果
        List<List<Like>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("点赞列表操作成功",res);
    }

    @GetMapping("/getComment")
    public AjaxResult getCommentListByUser(int userId){
        //获取全部评论
        List<Comment> list = createService.getCommentListByUser(userId);
        //获取今日评论
        List<Comment> todayList = new ArrayList<>();
        for (Comment comment : list){
            if (DateUtils.isSameDay(new Date(),comment.getTime())){
                todayList.add(comment);
            }
        }
        //封装结果
        List<List<Comment>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("评论列表操作成功",res);
    }

    @GetMapping("/getCollect")
    public AjaxResult getCollectListByUser(int userId){
        //获取收藏信息
        List<Collect> list = createService.getCollectListByUser(userId);
        //获取今日收藏
        List<Collect> todayList = new ArrayList<>();
        for (Collect collect : list){
            if (DateUtils.isSameDay(new Date(),collect.getTime())){
                todayList.add(collect);
            }
        }
        //封装结果
        List<List<Collect>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("收藏列表操作成功",res);
    }

    @GetMapping("/getFans")
    public AjaxResult getFansByUser(int userId){
        //获取全部粉丝列表
        List<Follow> fansList = createService.getFansByUser(userId);
        //去掉status=0的记录
        fansList.removeIf(
                item -> item.getStatus() == 0
        );
        //获取全部粉丝的用户信息
        List<Integer> ids = new ArrayList<>();
        for (Follow item: fansList){
            ids.add(item.getUserId());
        }
        List<User> list = createService.getUserById(ids);
        //获取今日粉丝
        List<Follow> fansTodayList = new ArrayList<>();
        for(Follow follow : fansList){
            if(DateUtils.isSameDay(new Date(),follow.getTime())){
                fansTodayList.add(follow);
            }
        }
        //获取今日粉丝的用户信息
        List<Integer> todayIds = new ArrayList<>();
        for (Follow item: fansTodayList){
            todayIds.add(item.getUserId());
        }
        List<User> todayList = createService.getUserById(todayIds);
        //封装结果
        List<List<User>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("粉丝列表操作成功",res);
    }

    @GetMapping("/getClick")
    public AjaxResult getClickListByUser(int userId){
        //获取全部浏览信息
        List<Click> list = createService.getClickListByUser(userId);
        //获取今日浏览
        List<Click> todayList = new ArrayList<>();
        for (Click click : list){
            if (DateUtils.isSameDay(new Date(),click.getTime())){
                todayList.add(click);
            }
        }
        //封装结果
        List<List<Click>> res = new ArrayList<>();
        res.add(list);
        res.add(todayList);
        return AjaxResult.success("浏览信息操作成功",res);
    }

    @GetMapping("getContentLineChart")
    public AjaxResult getContentLineChart(int userId){

        //查询全部数据
        List<Click> clickList = createService.getClickListByUser(userId);
        List<Collect> collectList = createService.getCollectListByUser(userId);
        List<Like> likeList = createService.getLikeListByUser(userId);
        List<Comment> commentList = createService.getCommentListByUser(userId);
        List<Article> articleList = createService.getArticleListByUser(userId);
        List<Follow> fanList = createService.getFansByUser(userId);

        //封装结果
        List<List<Integer>> res = new ArrayList<>();
        //获取第七天到第一天的数据
        for (int i=1; i>-6; i--){
            //获取第n天的时间
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            date = calendar.getTime();
            final Date time = date;
            //浏览
            clickList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getTime())
            );
            //收藏
            collectList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getTime())
            );
            //获赞
            likeList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getTime())
            );
            //评论
            commentList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getTime())
            );
            //文章
            articleList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getCreateTime())
            );
            //粉丝
            fanList.removeIf(
                    item -> DateUtils.isSameDay(time,item.getTime())
            );
            int click = clickList.toArray().length;
            int collect = collectList.toArray().length;
            int like = likeList.toArray().length;
            int comment = commentList.toArray().length;
            int article = articleList.toArray().length;
            int fan = fanList.toArray().length;
            List<Integer> dayList = new ArrayList<>();
            Collections.addAll(dayList,click,collect,like,comment,article,fan);

            //封装结果
            res.add(dayList);
        }

        return AjaxResult.success("内容折线图操作成功",res);
    }

    @GetMapping("getFollowMainData")
    public AjaxResult getFollowMainData(int userId){
        //获取所有的粉丝 包括0 1 两种状态
        List<Follow> fansList = createService.getFansByUser(userId);

        int cancelFan = 0;  //今天的取关粉丝数量
        int cancelFanYest = 0;  //昨天的取关粉丝数量
        //获取昨天的时间
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        for (Follow follow : fansList){
            //如果时间是今天并且status=0
            if (DateUtils.isSameDay(new Date(),follow.getTime()) && follow.getStatus()==0){
                cancelFan++;
            }
            //如果时间是昨天并且status=0
            if (DateUtils.isSameDay(date,follow.getTime()) && follow.getStatus() == 0){
                cancelFanYest++;
            }
        }
        //删除status=0的记录，查询粉丝总量和新增粉丝
        fansList.removeIf(
                item -> item.getStatus() == 0
        );
        int fan = fansList.toArray().length;    //粉丝总量
        int newFan = 0;   //新增粉丝
        int newFanYest = 0;     //昨日新增粉丝
        for (Follow follow : fansList){
            if (DateUtils.isSameDay(new Date(),follow.getTime())){
                newFan++;
            }
            if (DateUtils.isSameDay(date,follow.getTime())){
                newFanYest++;
            }
        }
        int fanYest = fan - newFan;       //昨日粉丝总量 = 今日粉丝总量 - 今日新增粉丝

        //封装粉丝结果
        List<Integer> fanRes = new ArrayList<>();
        Collections.addAll(fanRes,fan,fanYest,newFan,newFanYest,cancelFan,cancelFanYest);

        //获取所有的关注 包括0 1 两种状态
        List<Follow> followList = createService.getFollowByUser(userId);

        int cancelFollow = 0;  //今天的取关数量
        int cancelFollowYest = 0;  //昨天的取关数量

        for (Follow follow : followList){
            //如果时间是今天并且status=0
            if (DateUtils.isSameDay(new Date(),follow.getTime()) && follow.getStatus()==0){
                cancelFollow++;
            }
            //如果时间是昨天并且status=0
            if (DateUtils.isSameDay(date,follow.getTime()) && follow.getStatus() == 0){
                cancelFollowYest++;
            }
        }
        //删除status=0的记录，查询关注总量和新增关注
        followList.removeIf(
                item -> item.getStatus() == 0
        );
        int follow = followList.toArray().length;    //粉丝总量
        int newFollow = 0;   //新增粉丝
        int newFollowYest = 0;     //昨日新增粉丝
        for (Follow item : followList){
            if (DateUtils.isSameDay(new Date(),item.getTime())){
                newFollow++;
            }
            if (DateUtils.isSameDay(date,item.getTime())){
                newFollowYest++;
            }
        }
        int followYest = follow - newFollow;       //昨日关注总量 = 今日关注总量 - 今日新增关注

        //封装关注结果
        List<Integer> followRes = new ArrayList<>();
        Collections.addAll(followRes,follow,followYest,newFollow,newFollowYest,cancelFollow,cancelFollowYest);

        List<List<Integer>> res = new ArrayList<>();
        res.add(fanRes);
        res.add(followRes);
        return AjaxResult.success("数据获取成功",res);
    }

    @GetMapping("getFollowLineChart")
    public AjaxResult getFollowLineChart(int userId){
        //封装结果
        List<List<Integer>> res = new ArrayList<>();

        //获取全部数据
        List<Follow> fanList = createService.getFansByUser(userId);
        List<Follow> followList = createService.getFollowByUser(userId);
        //获取粉丝和关注的取消总数据
        List<Follow> cancelFanList = new ArrayList<>();
        List<Follow> cancelFollowList = new ArrayList<>();
        for (Follow follow : fanList){
            if (follow.getStatus() == 0){
                cancelFanList.add(follow);
            }
        }
        for (Follow follow : followList){
            if (follow.getStatus() == 0){
                cancelFollowList.add(follow);
            }
        }
        //获取粉丝和关注的总数据
        fanList.removeIf(item -> item.getStatus() == 0);
        followList.removeIf(item -> item.getStatus() == 0);

        int fanCount = fanList.toArray().length;     //粉丝总数
        int followCount = followList.toArray().length;       //关注总数

        //获取7天的数据
        for (int i=0; i>-7; i--) {
            //获取第n天的时间
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            date = calendar.getTime();
            final Date time = date;

            int newFan = 0;      //第i天的新增粉丝
            for (Follow follow : fanList){
                if (DateUtils.isSameDay(time,follow.getTime())){
                    newFan++;
                }
            }
            int cancelFan = 0;      //第i天的取关粉丝
            for (Follow follow : cancelFanList){
                if (DateUtils.isSameDay(time,follow.getTime())){
                    cancelFan++;
                }
            }
            int newFollow = 0;      //第i天的新增关注
            for (Follow follow : followList){
                if (DateUtils.isSameDay(time,follow.getTime())){
                    newFollow++;
                }
            }
            int cancelFollow = 0;      //第i天的取关
            for (Follow follow : cancelFollowList){
                if (DateUtils.isSameDay(time,follow.getTime())){
                    cancelFollow++;
                }
            }

            //第i天的数据
            List<Integer> dayList = new ArrayList<>();
            Collections.addAll(dayList,fanCount,newFan,cancelFan,
                                        followCount,newFollow,cancelFollow);

            //封装结果
            res.add(dayList);

            //用当天数据减去当天新增，得到前一天的数据
            fanCount = fanCount - newFan;
            followCount = followCount - newFollow;

        }
        return AjaxResult.success("折线图数据获取成功",res);
    }

    @GetMapping("getUser")
    public AjaxResult getUserById(int userId){
        List<Integer> list = new ArrayList<>();
        list.add(userId);
        List<User> user = createService.getUserById(list);
        return AjaxResult.success("用户获取成功",user);
    }

    @GetMapping("deleteArticle")
    public AjaxResult deleteArticle(int articleId){
        articleService.deleteArticle(articleId);
        return AjaxResult.success("删除成功");
    }

    @GetMapping("/searchArticle")
    public AjaxResult searchArticle(String title,int userId){
        List<Article> list = createService.searchArticle(title,userId);
        return AjaxResult.success("搜索成功", list);
    }

    @GetMapping("getDrafts")
    public AjaxResult getDraftListByUser(int userId){
        List<Draft> list = createService.getDraftListByUser(userId);
        return AjaxResult.success("草稿箱获取成功！",list);
    }

    @GetMapping("getDraftArticle")
    public AjaxResult getDraftArticleById(int articleId){
        Draft draft = createService.getDraftArticleById(articleId);
        return AjaxResult.success("草稿文章获取成功",draft);
    }

    @GetMapping("searchDraft")
    public AjaxResult searchDraft(String title, int userId){
        List<Draft> list = createService.searchDraft(title,userId);
        return AjaxResult.success("搜索成功", list);
    }

    @GetMapping("deleteDraft")
    public AjaxResult deleteDraft(int articleId){
        createService.deleteDraft(articleId);
        return AjaxResult.success("操作成功");
    }

}

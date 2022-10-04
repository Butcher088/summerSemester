package com.fan.controller;

import com.fan.entity.Article;
import com.fan.entity.Click;
import com.fan.entity.Like;
import com.fan.entity.User;
import com.fan.service.CreateService;
import com.fan.service.UserService;
import com.fan.service.Impl.LoginServiceImpl;
import com.fan.service.Impl.UserServiceImpl;
import com.fan.utils.AjaxResult;
import com.fan.utils.RedisCache;
import com.fan.utils.ResponseResult;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CreateService createService;

    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    RedisCache redisCache;

//
//    @GetMapping("/login")
//    public AjaxResult login(@Param("userName") String userName, @Param("passWord") String passWord) {
//        System.out.println(userName + " === " + passWord);
//        User user = userService.login(userName, passWord);
//        if (user != null) {
//            // 将传给前端的信息存入map
//            HashMap<String,Object> map = new HashMap<String,Object>();
//            map.put("userId",user.getUserId());
//            map.put("userName",user.getUserName());
//            return AjaxResult.success("操作成功",map);
//        } else {
//            return AjaxResult.error("操作失败");
//        }
//    }

    @GetMapping("/login")
    public ResponseResult testLogin(@Param("userName") String userName, @Param("passWord") String passWord) {
        User user = new User(userName,passWord);
//        user.setPassWord(new BCryptPasswordEncoder().encode(passWord));
        return loginService.login(user);
    }


    @GetMapping("/findByName")
    public AjaxResult selectByName(String userName){
        System.out.println(userName);
        User user = userService.selectByName(userName) ;
        if(user != null){
            //用户名已经存在
            return  AjaxResult.error("操作失败");
        }else{
            //用户名不存在，可以注册
            return AjaxResult.success("操作成功");
        }
    }

    @GetMapping("/findById")
    public AjaxResult selectById(@Param("userId") int userId) {
        System.out.println(userId);
        User user = userService.selectById(userId);
        System.out.println(user);
        if (user != null) {
            //id已经存在
            // 将传给前端的信息存入map
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getUserId());
            map.put("userName", user.getUserName());
            map.put("passWord", user.getPassWord());
            map.put("email", user.getEmail());
            map.put("phone", user.getPhone());
            map.put("img", user.getImg());
            return AjaxResult.success("操作成功", map);
        } else {
            //id不存在，可以注册
            return AjaxResult.error("操作失败");
        }
    }

    @GetMapping("/register")
    public AjaxResult insertUser(int userId,String userName,String passWord){
        System.out.println(userId+' '+userName +' '+passWord);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(passWord);
        //System.out.println(encode); // $2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC
        //System.out.println("match="+encoder.matches("root","$2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC"));
        int user = userService.insertUser(userId,userName,encode);
        if(user == 1){
            //注册成功
            return AjaxResult.success("操作成功");
        }else{
            //注册失败
            return  AjaxResult.error("操作失败");
        }
    }

    @GetMapping("/getCount")
    public int getCount(){
        int count = userService.getCount();
        System.out.println(count);
        return count;
    }

    @GetMapping("/editProfile")
    public AjaxResult updateAll(String userName, String passWord, String email, String phone, String img, Integer userId) {
        System.out.println(userName + " " + passWord + " " + email + " " + phone + " " + img + " " + userId);
        boolean b = userService.updateAll(userName, passWord, email, phone, img, userId);
        if (b) {
            return AjaxResult.success("操作成功");
        } else {
            return AjaxResult.error("操作失败");
        }
    }

    @GetMapping("getHeatWriter")
    public AjaxResult getHeatWriter(){

        if(redisCache.getCacheObject("heatWriter") != null) {
            return AjaxResult.
                    success("操作成功",redisCache.getCacheObject("heatWriter"));
        } else {
            //获取全部用户信息
            List<User> list = userService.getAllUser();
            //获取每个用户的粉丝量
            for (User user : list){
                int fan =  createService.getFansByUser(user.getUserId()).toArray().length;
                user.setFanCount(fan);
            }
            //按粉丝量排序
            list = list.stream().sorted(Comparator.comparing(User::getFanCount)
                    .reversed())
                    .collect(Collectors.toList());
            redisCache.setCacheObject("heatWriter",list.subList(0,3));
            return AjaxResult.success("操作成功",list.subList(0,3));
        }

    }

    // 查看文章时获得作者总浏览数
    @GetMapping("/getAllClick")
    public AjaxResult getClickByUser(int userId){

        //获取全部浏览信息
        List<Click> list = createService.getClickListByUser(userId);

        return AjaxResult.success("浏览信息操作成功",list);
    }
    // 查看文章时获得作者总被点赞数
    @GetMapping("/getAllLike")
    public AjaxResult getLikeByUser(int userId){

        //获取全部获赞
        List<Like> list = createService.getLikeListByUser(userId);

        return AjaxResult.success("点赞列表操作成功",list);
    }


    @GetMapping("/userLayout")
    public ResponseResult logout(@Param("userId") int userId) {
        System.out.println("userId=="+userId);
        return loginService.logout(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/admin/user/list")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult list(User user)
    {
        System.out.println("admin/user---"+user );
        List<User> list = userService.selectUserList(user);
        return new ResponseResult(200,list);
    }



    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/admin/user/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(userService.selectUserByUserId(userId));
    }



    /**
     * 修改【请填写功能名称】
     */
    @PutMapping("/admin/user")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult edit(@RequestBody User user)
    {
        return AjaxResult.success(userService.updateUser(user));
    }

    /**
     * 删除【请填写功能名称】
     */
    @DeleteMapping("/admin/user/{userIds}")
    @PreAuthorize("hasAuthority('admin')")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return AjaxResult.success(userService.deleteUserByUserIds(userIds));
    }
}

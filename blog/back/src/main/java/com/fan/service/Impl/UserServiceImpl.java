package com.fan.service.Impl;

import com.fan.entity.User;
import com.fan.mapper.UserMapper;
import com.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName,String passWord){
        return userMapper.login(userName, passWord);
    };

    @Override
    public User selectByName(String userName){
        return userMapper.selectByName(userName);
    }

    @Override
    public User selectById(int userId){
        return userMapper.selectById(userId);
    }



    @Override
    public int insertUser(int userId,String userName,String passWord){
        return userMapper.insertUser(userId,userName,passWord);
    }

    @Override
    public  int getCount(){
        return userMapper.getCount();
    }

    @Override
    public boolean updateAll(String userName,String passWord,String email,String phone,String img,int userId){
        return userMapper.updateAll(userName,passWord,email,phone,img,userId);

    }


    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public User selectUserByUserId(Long userId)
    {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param user 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }


    /**
     * 修改【请填写功能名称】
     *
     * @param user 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Long[] userIds)
    {
        return userMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Long userId)
    {
        return userMapper.deleteUserByUserId(userId);
    }
}

package com.fan.service;

import com.fan.entity.User;

import java.util.List;

public interface UserService {

    User login(String userName, String passWord);

    User selectByName(String userName);

    User selectById(int userId);

    int insertUser(int userId,String userName, String passWord);

    int getCount();

    boolean updateAll(String userName,String passWord,String email,String phone,String img,int userId);

    List<User> getAllUser();

    /**
     * 查询【请填写功能名称】
     *
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public User selectUserByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param user 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<User> selectUserList(User user);

    /**
     * 修改【请填写功能名称】
     *
     * @param user 【请填写功能名称】
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);
}

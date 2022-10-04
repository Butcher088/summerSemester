package com.fan.mapper;

import com.fan.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User login(String userName, String passWord);

    User selectByName(String userName);

    User selectById(int userId);

    int insertUser(int userId,String userName, String passWord);

    int getCount();

    boolean updateAll(String userName,String passWord,String email,String phone,String img,int userId);

    /**
    * @Description: 获取全部用户信息
    * @Date:  2022/7/27 10:36
    **/
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
     * 删除【请填写功能名称】
     *
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);
}

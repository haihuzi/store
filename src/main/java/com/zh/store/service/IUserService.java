package com.zh.store.service;

import com.zh.store.entity.User;

/**
 * TODO
 *
 * @Description 处理用户数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/23 12:35
 **/

public interface IUserService {
    /**
     * TODO
     *
     * @Description 用户注册接口
     * @Param user 用户数据
     * @Return void
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 12:37
     **/
    void reg(User user);
    /***
     * @Description 登录接口
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 20:20
     * @param username
     * @Param password
     * @Return: com.zh.store.entity.User
     **/
    User login(String username, String password);

    /***
     * @Description 修改用户密码
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 22:27
     * @param uid
     * @Param username
     * @Param oldPassword
     * @Param newPassword
     * @Return: void
     **/
    void changePassword(Integer uid, String username, String oldPassword, String
            newPassword);

    /**
     * @Description 根据uid查询用户
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 13:59
     * @param uid
     * @Return: com.zh.store.entity.User
     **/
    User findByUid(Integer uid);

    /**
     * @Description 修改用户信息
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 14:03
     * @param uid
     * @Param username
     * @Param user
     * @Return: void
     **/
    void changeUser(Integer uid,String username,User user);

    /**
     * @Description 修改用户头像
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 12:11
     * @param uid
     * @Param username
     * @Param avatar
     * @Return: void
     **/
    void changeAvatar(Integer uid, String username, String avatar);
}

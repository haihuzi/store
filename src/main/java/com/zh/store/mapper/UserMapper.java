package com.zh.store.mapper;

import com.zh.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * TODO
 *
 * @Description 处理用户信息持久层接口
 * @Author yuzhenhai
 * @Date 2023/02/22 23:27
 **/


public interface UserMapper {

    /**
     * TODO
     *
     * @Description
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 15:56
     * @param user
     * @Return: java.lang.Integer
     **/
    Integer insert(User user);

    /**
     * TODO
     *
     * @Description 查询用户名是否存在
     * @Param username
     * @Return com.zh.store.entity.User
     * @Author yuzh
     * @Date 2023/2/22 0022 23:56
     **/
    User findByUsername(String username);

    /***
     * @Description 修改密码
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 22:18
     * @param uid
     * @Param password
     * @Param modifiedUser
     * @Param modifiedTime
     * @Return: java.lang.Integer
     **/
    Integer updatePasswordByUid(@Param("uid")Integer uid,
                                @Param("password")String password,@Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime,@Param("salt")String salt);
    
    /***
    * @Description 根据uid查询用户数据
    * @Author yuzhenhai
    * @Date 2023/2/23 0023 22:20
    * @param uid
    * @Return: com.zh.store.entity.User
    **/
    User findByUid(Integer uid);
    
    /**
    * @Description 修改用户信息
    * @Author yuzhenhai
    * @Date 2023/2/24 0024 13:48
    * @param user
    * @Return: java.lang.Integer
    **/
    Integer updateUserByUid(User user);

    /**
    * @Description 根据uid更新用户的头像
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 12:09
    * @param uid
     * @Param avatar
     * @Param modifiedUser
     * @Param modifiedTime
    * @Return: java.lang.Integer
    **/
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}

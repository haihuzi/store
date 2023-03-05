package com.zh.store.service.impl;

import com.zh.store.entity.User;
import com.zh.store.mapper.UserMapper;
import com.zh.store.service.IUserService;
import com.zh.store.service.ex.*;
import com.zh.store.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * TODO
 *
 * @Description 操作用户的实现类
 * @Author yuzhenhai
 * @Date 2023/02/23 12:40
 **/

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 根据参数user对象获取注册的用户名
        String username = user.getUsername();
        // 调用持久层的User findByUsername(String username)方法，根据用户名查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("尝试注册的用户名[" + username + "]已经被占用");
        }
        log.info("========================="+user.toString());
        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = Md5Util.getMd5(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        // 补全数据：isDelete(0)
        user.setIsDelete(0);
        // 补全数据：4项日志属性
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);
        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    /***
     * @Description 登录接口
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 20:25
     * @param username
     * @Param password
     * @Return: com.zh.store.entity.User
     **/
    @Override
    public User login(String username, String password) {
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User result = userMapper.findByUsername(username);
        System.out.println(result.toString());
        // 查询用户是否存在,不存在则抛出异常
        extracted(result);
        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = Md5Util.getMd5(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("密码验证失败的错误");
        }
        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /***
     * @Description 修改用户密码
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 22:27
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     * @Param username
     * @Param oldPassword
     * @Param newPassword
     * @Return: void
     **/
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        //查找用户是否存在
        User user = userMapper.findByUid(uid);
        // 查询用户是否存在,不存在则抛出异常
        extracted(user);
        //用户存在，开始对比密码
        //将老密码进行加密与加密密码对比
        String md5Password = Md5Util.getMd5(oldPassword, user.getSalt());
        if(!md5Password.contentEquals(user.getPassword())){
            throw new PasswordNotMatchException("原密码输入错误！");
        }
        //生成新的盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //对新密码加密准备修改用户信息
        String password = Md5Util.getMd5(newPassword, salt);
        //修改用户信息
        Integer rows = userMapper.updatePasswordByUid(uid, password, username, new Date(),salt);
        if(rows != 1){
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    /**
     * @param uid
     * @Description 根据uid查询用户
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 13:59
     * @Return: com.zh.store.entity.User
     **/
    @Override
    public User findByUid(Integer uid) {
        //查询用户信息
        User user = userMapper.findByUid(uid);
        // 查询用户是否存在,不存在则抛出异常
        extracted(user);
        //新建一个user，只返回可见的信息
        User data = new User();
        data.setAvatar(user.getAvatar());
        data.setPhone(user.getPhone());
        data.setEmail(user.getEmail());
        data.setGender(user.getGender());
        data.setUsername(user.getUsername());
        return data;
    }

    /**
     * @param uid
     * @param username
     * @param user
     * @Description 修改用户信息
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 14:03
     * @Param username
     * @Param user
     * @Return: void
     **/
    @Override
    public void changeUser(Integer uid, String username, User user) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 查询用户是否存在
        extracted(result);
        // 向参数user中补全数据：uid
        user.setUid(uid);
        // 向参数user中补全数据：modifiedUser(username)
        user.setModifiedUser(username);
        // 向参数user中补全数据：modifiedTime(new Date())
        user.setModifiedTime(new Date());
        // 调用userMapper的updateUserByUid(User user)方法执行修改，并获取返回值
        Integer rows = userMapper.updateUserByUid(user);
        if (rows != 1) {
            throw new UpdateException("修改用户信息异常，请联系管理员");
        }
    }

    /**
     * @param uid
     * @param username
     * @param avatar
     * @Description 修改用户头像
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 12:11
     * @Param username
     * @Param avatar
     * @Return: void
     **/
    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 检查查询结果是否为null
        extracted(result);
        // 创建当前时间对象
        Date now = new Date();
        // 调用userMapper的updateAvatarByUid()方法执行更新，并获取返回值
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, now);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    private static void extracted(User result) {
        if (result == null) {
        // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
    }


}

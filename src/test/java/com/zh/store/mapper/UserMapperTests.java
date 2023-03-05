package com.zh.store.mapper;

import com.zh.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * TODO
 *
 * @Description
 * @Author yuzhenhai
 * @Date 2023/02/23 0:32
 **/
//表示这个类是一个测试类，不随代码打包
@SpringBootTest
//表示启动这个单元测试类(单元测试类是不能够运行的)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试方法: 可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     **/

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123456");
        int rows = userMapper.insert(user);
        System.out.println("影响条数："+rows);

    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tom");
        System.out.println(user.toString());
    }

    @Test
    public void updatePasswordByUid(){
        Integer uid=1;
        String password ="1234567";
        String modifiedUser = "admin";
        Date modifiedTime = new Date();
        String salt = UUID.randomUUID().toString().toUpperCase();
        Integer rows = userMapper.updatePasswordByUid(uid,password,modifiedUser,modifiedTime,"");
        System.out.println("影响条数："+rows);
    }

    @Test
    public void findByUid(){
        Integer uid=1;
        User user= userMapper.findByUid(uid);
        System.out.println(user.toString());
    }

    @Test
    public void updateUserByUid() {
        User user = new User();
        user.setUid(1);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateUserByUid(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 1;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }


}

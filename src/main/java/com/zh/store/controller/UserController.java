package com.zh.store.controller;

import com.zh.store.entity.User;
import com.zh.store.service.IUserService;
import com.zh.store.service.ex.*;
import com.zh.store.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @Description 处理用户相关请求的控制器类
 * @Author yuzhenhai
 * @Date 2023/02/23 14:59
 **/
@RestController
@RequestMapping("users")
@Slf4j
public class UserController extends BaseController{
    @Autowired
    private IUserService iUserService;

    /***
     * @Description 注册接口
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 19:07
     * @param user
     * @Return: com.zh.store.utils.JsonResult<java.lang.Void>
     **/
    @RequestMapping(value = "reg")
    public JsonResult<Void> reg(@RequestBody User user){
        log.info("user:"+user);
        iUserService.reg(user);
        return  new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(@RequestParam("username")String username,@RequestParam("password") String password, HttpSession session){
        System.out.println(username+"---"+password);
        // 调用业务对象的方法执行登录，并获取返回值
        User data = iUserService.login(username, password);
        //将用户信息保存在session中
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new JsonResult<User>(OK, data);
    }

    /***
     * @Description 修改用户密码
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 23:20
     * @param oldPassword
     * @Param newPassword
     * @Param session
     * @Return: com.zh.store.utils.JsonResult<com.zh.store.entity.User>
     **/
    @RequestMapping("changePassword")
    public JsonResult<String> changePassword(String oldPassword,String newPassword, HttpSession session) {
        //获取session里的用户参数
        Integer uid = getUidFromSession(session);
        String username = getUnameFromSession(session);
        System.out.println("old："+oldPassword+",new:"+newPassword);
        //执行修改密码
        iUserService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<String>(OK,"执行成功");
    }

    /**
     * @Description 查询当前登录用户的数据
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 14:26
     * @param session
     * @Return: com.zh.store.utils.JsonResult<com.zh.store.entity.User>
     **/
    @GetMapping("findUser")
    public JsonResult<User> findUser(HttpSession session){
        //从session中拿到用户的uid
        Integer uid = getUidFromSession(session);
        //调用查询 查询用户的数据
        User user = iUserService.findByUid(uid);
        //返回数据
        return new JsonResult<User>(OK, user);
    }

    /**
     * @Description 修改用户信息接口
     * @Author yuzhenhai
     * @Date 2023/2/24 0024 14:36
     * @param user
     * @Param session
     * @Return: com.zh.store.utils.JsonResult<java.lang.Void>
     **/
    @RequestMapping("updateInfo")
    public JsonResult<Void> updateInfo(@RequestBody User user,HttpSession session){
        //获取到当前用户的用户名和密码
        Integer uid = getUidFromSession(session);
        String uname = getUnameFromSession(session);
        //调用修改方法修改数据
        iUserService.changeUser(uid,uname,user);
        //返回结果
        return new JsonResult<Void>(OK);
    }


    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    /**
     * @Description 上传头像
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 12:24
     * @param file
     * @Param session
     * @Return: com.zh.store.utils.JsonResult<java.lang.String>
     **/
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        // getSize()：返回文件的大小，以字节为单位
        if (file.getSize() > AVATAR_MAX_SIZE) {
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);

        // 保存头像文件的文件夹
        File dir = new File("D:/SpringBoot-dianshang/store/src/main/resources/static/img/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/store/img/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUnameFromSession(session);
        // 将头像写入到数据库中
        iUserService.changeAvatar(uid, username, avatar);

        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }
}

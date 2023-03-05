package com.zh.store.controller;

import com.zh.store.entity.Address;
import com.zh.store.entity.User;
import com.zh.store.service.IAddressService;
import com.zh.store.service.IUserService;
import com.zh.store.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * TODO
 *
 * @Description 处理用户收货地址相关请求的控制器类
 * @Author yuzhenhai
 * @Date 2023/02/23 14:59
 **/
@RestController
@RequestMapping("address")
@Slf4j
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add")
    public JsonResult<Void> newAddress(@RequestBody Address address,HttpSession session) {
        //从session中获取用户id和name
        Integer uid = getUidFromSession(session);
        String uname = getUnameFromSession(session);
        //新增数据
        addressService.newAddress(uid,uname,address);
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUnameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUnameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

}

package com.zh.store.service;

import com.zh.store.entity.Address;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理收货地址数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/25 10:48
 **/

public interface IAddressService {
    /**
     * @Description 新增用户的收货地址
     * @Author yuzhenhai
     * @Date 2023/2/25 0025 10:51
     * @param uid 当前登录用户的uid
     * @Param username 当前登录用户的用户名
     * @Param address 用户提交的收货地址数据
     * @Return: void
     **/
    void newAddress(Integer uid, String username, Address address);

    /**
     * @Description 查询某用户的收货地址列表数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 1:46
     * @param uid
     * @Return: java.util.List<com.zh.store.entity.Address>
     **/
    List<Address> getByUid(Integer uid);

    /**
     * @Description 设置默认收货地址
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 14:55
     * @param aid
     * @Param uid
     * @Param username
     * @Return: void
     **/
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * @Description 删除收货地址
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 15:27
     * @param aid
     * @Param uid
     * @Param username
     * @Return: void
     **/
    void delete(Integer aid, Integer uid, String username);


    /**
     * @Description 根据收货地址数据的id，查询收货地址详情
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:36
     * @param aid
     * @Param uid
     * @Return: com.zh.store.entity.Address
     **/
    Address getByAid(Integer aid, Integer uid);
}

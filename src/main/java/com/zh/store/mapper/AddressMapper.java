package com.zh.store.mapper;

import com.zh.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description 处理收货地址表持久层接口
 * @Author yuzhenhai
 * @Date 2023/02/24 14:54
 **/

public interface AddressMapper {
    /**
    * @Description 插入用户收货地址
    * @Author yuzhenhai
    * @Date 2023/2/24 0024 14:57
    * @param address
    * @Return: java.lang.Integer
    **/
    Integer insert(Address address);

    /**
    * @Description 统计用户收货地址数量
    * @Author yuzhenhai
    * @Date 2023/2/24 0024 14:58
    * @param uid
    * @Return: java.lang.Integer
    **/
    Integer countByUid(Integer uid);
    
    /**
    * @Description 查询用户的收货地址数据
    * @Author yuzhenhai
    * @Date 2023/2/25 0025 23:09
    * @param uid
    * @Return: com.zh.store.entity.Address
    **/
    List<Address> findByUid(Integer uid);


    /**
    * @Description 将某用户的所有收货地址设置为非默认地址
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 14:49
    * @param uid
    * @Return: java.lang.Integer
    **/
    Integer updateNonDefaultByUid(Integer uid);

    /**
    * @Description 将指定的收货地址设置为默认地址
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 14:49
    * @param aid
     * @Param modifiedUser
     * @Param modifiedTime
    * @Return: java.lang.Integer
    **/
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
    * @Description 根据收货地址aid值，查询收货地址详情
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 14:49
    * @param aid
    * @Return: com.zh.store.entity.Address
    **/
    Address findByAid(Integer aid);

    /**
    * @Description 根据收货地址id删除数据
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 15:11
    * @param aid
    * @Return: java.lang.Integer
    **/
    Integer deleteByAid(Integer aid);

    /**
    * @Description 查询某用户最后修改的收货地址
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 15:10
    * @param uid
    * @Return: com.zh.store.entity.Address
    **/
    Address findLastModified(Integer uid);
}

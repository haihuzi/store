package com.zh.store.service;

import com.zh.store.vo.CartVO;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理商品数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 22:51
 **/

public interface ICartService {
    /**
     * @Description 将商品添加到购物车
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:52
     * @param uid
     * @Param pid
     * @Param amount
     * @Param username
     * @Return: void
     **/
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * @Description 查询某用户的购物车数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:07
     * @param uid
     * @Return: java.util.List<com.zh.store.vo.CartVO>
     **/
    List<CartVO> getVOByUid(Integer uid);

    /**
     * @Description 将购物车中某商品的数量加1
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:13
     * @param cid
     * @Param uid
     * @Param username
     * @Return: java.lang.Integer
     **/
    Integer addNum(Integer cid, Integer uid, String username);

    /**
    * @Description 根据若干个购物车数据id查询详情的列表
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:28
    * @param uid
 * @Param cids
    * @Return: java.util.List<com.zh.store.vo.CartVO>
    **/
    List<CartVO> getVOByCids(Integer uid, Integer[] cids);
}

package com.zh.store.mapper;

import com.zh.store.entity.Cart;
import com.zh.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description 购物车数据持久层
 * @Author yuzhenhai
 * @Date 2023/02/27 22:46
 **/

public interface CartMapper {
    /**
     * @Description 插入购物车数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:46
     * @param cart
     * @Return: java.lang.Integer
     **/
    Integer insert(Cart cart);
    /**
     * @Description 修改购物车数据中商品的数量
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:47
     * @param cid
     * @Param num
     * @Param modifiedUser
     * @Param modifiedTime
     * @Return: java.lang.Integer
     **/
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    /**
     * @Description 根据用户id和商品id查询购物车中的数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:47
     * @param uid
     * @Param pid
     * @Return: com.zh.store.entity.Cart
     **/
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    /**
    * @Description 查询某用户的购物车数据
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:05
    * @param uid
    * @Return: java.util.List<com.zh.store.vo.CartVO>
    **/
    List<CartVO> findVOByUid(Integer uid);

    /**
    * @Description 根据购物车数据id查询购物车数据详情
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:12
    * @param cid
    * @Return: com.zh.store.entity.Cart
    **/
    Cart findByCid(Integer cid);

    /**
    * @Description 根据若干个购物车数据id查询详情的列表
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:20
    * @param cids
    * @Return: java.util.List<com.zh.store.vo.CartVO>
    **/
    List<CartVO> findVOByCids(Integer[] cids);

}

package com.zh.store.service.impl;

import com.zh.store.entity.Cart;
import com.zh.store.entity.Product;
import com.zh.store.mapper.CartMapper;
import com.zh.store.service.ICartService;
import com.zh.store.service.IProductService;
import com.zh.store.service.ex.AccessDeniedException;
import com.zh.store.service.ex.CartNotFoundException;
import com.zh.store.service.ex.InsertException;
import com.zh.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @Description 处理购物车数据的业务层实现类
 * @Author yuzhenhai
 * @Date 2023/02/27 22:52
 **/

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    /**
     * @param uid
     * @param pid
     * @param amount
     * @param username
     * @Description 将商品添加到购物车
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:52
     * @Param pid
     * @Param amount
     * @Param username
     * @Return: void
     **/
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date now = new Date();
        // 判断查询结果是否为null
        if (result == null) {
            // 是：表示该用户并未将该商品添加到购物车
            // 创建Cart对象
            Cart cart = new Cart();
            // 封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = productService.findById(pid);
            // 封装数据：price
            cart.setPrice(product.getPrice());
            // 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            // 调用insert(cart)执行将数据插入到数据表中
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        } else {
            // 否：表示该用户的购物车中已有该商品
            //从购物车中获取数据
            Integer cid = result.getCid();
            // 从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer num = result.getNum() + amount;
            // 执行更新数量
            Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
            if (rows != 1) {
                throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
            }
        }
    }

    /**
     * @param uid
     * @Description 查询某用户的购物车数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:07
     * @Return: java.util.List<com.zh.store.vo.CartVO>
     **/
    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    /**
     * @param cid
     * @param uid
     * @param username
     * @Description 将购物车中某商品的数量加1
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:13
     * @Param uid
     * @Param username
     * @Return: java.lang.Integer
     **/
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }
        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() + 1;
        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        // 返回新的数量
        return num;
    }

    /**
     * @param uid
     * @param cids
     * @Description 根据若干个购物车数据id查询详情的列表
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:19
     * @Param cids
     * @Return: java.util.List<com.zh.store.vo.CartVO>
     **/
    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        /**
         for (CartVO cart : list) {
         if (!cart.getUid().equals(uid)) {
         list.remove(cart);}
         }
         */
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }
}
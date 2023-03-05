package com.zh.store.service;

import com.zh.store.entity.Order;

/**
 * TODO
 *
 * @Description 处理订单和订单数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 23:37
 **/

public interface IOrderService {
    /**
     * @Description 创建订单
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:38
     * @param aid
     * @Param cids
     * @Param uid
     * @Param username
     * @Return: com.zh.store.entity.Order
     **/
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}

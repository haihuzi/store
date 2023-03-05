package com.zh.store.mapper;

import com.zh.store.entity.Order;
import com.zh.store.entity.OrderItem;

/**
 * TODO
 *
 * @Description 处理订单及订单商品数据的持久层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 23:30
 **/

public interface OrderMapper {

    /**
    * @Description 插入订单数据
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:30
    * @param order
    * @Return: java.lang.Integer
    **/
    Integer insertOrder(Order order);
    /**
    * @Description 插入订单商品数据
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 23:42
    * @param orderItem
    * @Return: java.lang.Integer
    **/
    Integer insertOrderItem(OrderItem orderItem);
}

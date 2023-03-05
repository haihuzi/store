package com.zh.store.service;

import com.zh.store.entity.Product;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理商品数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 15:51
 **/

public interface IProductService {
    /**
     * @Description 查询热销商品的前四名
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 15:52
     * @Return: java.util.List<com.zh.store.entity.Product>
     **/
    List<Product> findHotList();

    /**
    * @Description 根据商品id查询商品详情
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 22:52
    * @param id
    * @Return: com.zh.store.entity.Product
    **/
    Product findById(Integer id);
}

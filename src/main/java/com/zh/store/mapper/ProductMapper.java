package com.zh.store.mapper;

import com.sun.org.apache.xpath.internal.operations.String;
import com.zh.store.entity.Product;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理商品数据的持久层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 15:45
 **/

public interface ProductMapper {
    /**
    * @Description 查询热销商品的前四名
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 15:46
    
    * @Return: java.util.List<com.zh.store.entity.Product>
    **/
    List<Product> findHotList();

    /**
    * @Description 
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 16:07
    * @param id
    * @Return: com.zh.store.entity.Product
    **/
    Product findById(Integer id);

}

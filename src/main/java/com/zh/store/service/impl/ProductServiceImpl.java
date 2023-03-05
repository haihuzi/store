package com.zh.store.service.impl;

import com.zh.store.entity.Product;
import com.zh.store.mapper.ProductMapper;
import com.zh.store.service.IProductService;
import com.zh.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理商品数据的业务层实现类
 * @Author yuzhenhai
 * @Date 2023/02/27 15:52
 **/
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * @Description 查询热销商品的前四名
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 15:52
     * @Return: java.util.List<com.zh.store.entity.Product>
     **/
    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    /**
     * @param id
     * @Description 根据商品id查询商品详情
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 22:39
     * @Return: com.zh.store.entity.Product
     **/
    @Override
    public Product findById(Integer id) {
        // 根据参数id调用私有方法执行查询，获取商品数据
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
        // 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查询结果
        return product;
    }
}

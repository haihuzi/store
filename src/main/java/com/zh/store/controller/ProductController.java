package com.zh.store.controller;

import com.zh.store.entity.Product;
import com.zh.store.service.IProductService;
import com.zh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description 商品的控制类
 * @Author yuzhenhai
 * @Date 2023/02/27 15:55
 **/

@RequestMapping("products")
@RestController
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
    // 调用业务对象执行获取数据
        Product data = productService.findById(id);
    // 返回成功和数据
        return new JsonResult<Product>(OK, data);
    }

}

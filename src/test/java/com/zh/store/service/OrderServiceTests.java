package com.zh.store.service;

import com.zh.store.entity.Address;
import com.zh.store.entity.Order;
import com.zh.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author yuzhenhai
 * @Date 2023/02/23 0:32
 **/
//表示这个类是一个测试类，不随代码打包
@SpringBootTest
//表示启动这个单元测试类(单元测试类是不能够运行的)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create() {
        try {
            Integer aid = 21;
            Integer[] cids = {4, 5, 6,7};
            Integer uid = 31;
            String username = "订单管理员";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}

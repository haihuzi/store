package com.zh.store.mapper;

import com.zh.store.entity.Address;
import com.zh.store.entity.District;
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
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    public void findByParent() {
        String parent = "110100";
        List<District> list = districtMapper.findByParent(parent);
        System.out.println("count=" + list.size());
        for (District district : list) {
            System.out.println(district);
        }
    }
    @Test
    public void findNameByCode() {
        String code = "540000";
        String name = districtMapper.findNameByCode(code);
        System.out.println(name);
    }



}

package com.zh.store.controller;

import com.zh.store.entity.District;
import com.zh.store.service.IDistrictService;
import com.zh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description 获取省/市/区的控制类
 * @Author yuzhenhai
 * @Date 2023/02/27 0:50
 **/

@RequestMapping("district")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    @GetMapping({"", "/"})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK, data);
    }
}

package com.zh.store.service;

import com.zh.store.entity.District;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理省/市/区数据的业务层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 0:46
 **/

public interface IDistrictService {
    /**
    * @Description 获取全国所有省/某省所有市/某市所有区
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 0:56
    * @param parent
    * @Return: java.util.List<com.zh.store.entity.District>
    **/
    List<District> getByParent(String parent);

    /**
    * @Description 根据省/市/区的行政代号获取省/市/区的名称
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 1:35
    * @param code
    * @Return: java.lang.String
    **/
    String getNameByCode(String code);
}

package com.zh.store.mapper;

import com.zh.store.entity.District;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理省/市/区数据的持久层接口
 * @Author yuzhenhai
 * @Date 2023/02/27 0:25
 **/

public interface DistrictMapper {
    /**
    * @Description 获取全国所有省/某省所有市/某市所有区
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 0:28
    * @param parent
    * @Return: java.util.List<com.zh.store.entity.District>
    **/
    List<District> findByParent(String parent);

    /**
    * @Description 根据省/市/区的行政代号获取省/市/区的名称
    * @Author yuzhenhai
    * @Date 2023/2/27 0027 0:59
    * @param code
    * @Return: java.lang.String
    **/
    String findNameByCode(String code);
}

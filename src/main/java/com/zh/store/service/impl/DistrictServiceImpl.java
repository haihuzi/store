package com.zh.store.service.impl;

import com.zh.store.entity.District;
import com.zh.store.mapper.DistrictMapper;
import com.zh.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Description 处理省/市/区数据的业务层实现类
 * @Author yuzhenhai
 * @Date 2023/02/27 0:46
 **/

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    /**
     * @param parent
     * @Description 获取全国所有省/某省所有市/某市所有区
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 0:46
     * @Return: java.util.List<com.zh.store.entity.District>
     **/
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    /**
     * @param code
     * @Description 根据省/市/区的行政代号获取省/市/区的名称
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 0:57
     * @Return: java.lang.String
     **/
    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}

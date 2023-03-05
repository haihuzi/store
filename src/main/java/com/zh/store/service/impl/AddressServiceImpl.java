package com.zh.store.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zh.store.entity.Address;
import com.zh.store.mapper.AddressMapper;
import com.zh.store.service.IAddressService;
import com.zh.store.service.IDistrictService;
import com.zh.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @Description 业务层实现类
 * @Author yuzhenhai
 * @Date 2023/02/25 10:53
 **/

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;


    @Value("${user.address.max-count}")
    private Integer MaxCount;
    /**
     * @Description 新增用户的收货地址
     * @Author yuzhenhai
     * @Date 2023/2/25 0025 10:51
     * @Param username 当前登录用户的用户名
     * @Param address 用户提交的收货地址数据
     * @Return: void
     **/
    @Override
    public void newAddress(Integer uid, String username, Address address) {
        //查找用户的收获地址是否已经达到上限
        Integer count = addressMapper.countByUid(uid);
        //判断是否达到上限
        if (count > MaxCount) {
            throw new AddressCountLimitException("收货地址已达到上限(\" + maxCount + \")！");
        }
        //补全数据
        Date now = new Date();
        address.setUid(uid);
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        //判断是否为默认值 (是否默认：0-不默认，1-默认)
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        // 补全数据：省、市、区的名称
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        //新增收获数据
        Integer rows = addressMapper.insert(address);
        //判断是否新增成功
        if(rows != 1){
            throw new InsertException("创建收货地址失败，请联系管理员");
        }
    }

    /**
     * @param uid
     * @Description 查询某用户的收货地址列表数据
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 1:02
     * @Return: java.util.List<com.zh.store.entity.Address>
     **/
    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    /**
     * @param aid
     * @param uid
     * @param username
     * @Description 设置默认收货地址
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 14:55
     * @Param uid
     * @Param username
     * @Return: void
     **/
    @Transactional
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用addressMapper中的findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问的异常");
        }

        // 调用addressMapper的updateNonDefaultByUid()将该用户的所有收货地址全部设置为非默认，并获取返回受影响的行数
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        // 判断受影响的行数是否小于1(不大于0)
        if (rows < 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[1]");
        }

        // 调用addressMapper的updateDefaultByAid()将指定aid的收货地址设置为默认，并获取返回的受影响的行数
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[2]");
        }
    }

    /**
     * @param aid
     * @param uid
     * @param username
     * @Description 删除收货地址
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 15:27
     * @Param uid
     * @Param username
     * @Return: void
     **/
    @Transactional
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException：非法访问
            throw new AccessDeniedException("非常访问");
        }

        // 根据参数aid，调用deleteByAid()执行删除
        Integer rows1 = addressMapper.deleteByAid(aid);
        if (rows1 != 1) {
            throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
        }

        // 判断查询结果中的isDefault是否为0
        if (result.getIsDefault() == 0) {
            return;
        }

        // 调用持久层的countByUid()统计目前还有多少收货地址
        Integer count = addressMapper.countByUid(uid);
        // 判断目前的收货地址的数量是否为0
        if (count == 0) {
            return;
        }

        // 调用findLastModified()找出用户最近修改的收货地址数据
        Address lastModified = addressMapper.findLastModified(uid);
        // 从以上查询结果中找出aid属性值
        Integer lastModifiedAid = lastModified.getAid();
        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows2 != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
        }
    }

    /**
     * @param aid
     * @param uid
     * @Description 根据收货地址数据的id，查询收货地址详情
     * @Author yuzhenhai
     * @Date 2023/2/27 0027 23:36
     * @Param uid
     * @Return: com.zh.store.entity.Address
     **/
    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}

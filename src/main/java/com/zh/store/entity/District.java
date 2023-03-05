package com.zh.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 省/市/区数据的实体类
 * @Author yuzhenhai
 * @Date 2023/02/27 0:22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}

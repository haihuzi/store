package com.zh.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 商品实体类
 * @Author yuzhenhai
 * @Date 2023/02/27 15:44
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product  extends BaseEntity implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
}

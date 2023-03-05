package com.zh.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @Description 订单中的商品数据
 * @Author yuzhenhai
 * @Date 2023/02/24 14:52
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}

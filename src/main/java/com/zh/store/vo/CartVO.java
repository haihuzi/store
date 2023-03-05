package com.zh.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 购物车数据的Value Object类
 * @Author yuzhenhai
 * @Date 2023/02/27 23:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

}

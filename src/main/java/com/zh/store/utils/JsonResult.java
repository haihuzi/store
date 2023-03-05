package com.zh.store.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description 响应结果类 Json格式的响应类型
 * @Author yuzhenhai
 * @Date 2023/02/23 14:52
 **/

@Data
@NoArgsConstructor
public class JsonResult<E> implements Serializable{
    /** 状态码 */
    private Integer state = 200;
    /** 状态描述信息 */
    private String message;
    /** 数据 */
    private E data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
}

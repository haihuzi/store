package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 商品数据不存在的异常
 * @Author yuzhenhai
 * @Date 2023/02/27 22:38
 **/

public class ProductNotFoundException extends ServiceException{
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

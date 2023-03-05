package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 收货地址数据不存在的异常
 * @Author yuzhenhai
 * @Date 2023/02/27 14:53
 **/

public class AddressNotFoundException extends ServiceException{
    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

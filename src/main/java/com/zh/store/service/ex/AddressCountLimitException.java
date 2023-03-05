package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 添加收获地址达到上限
 * @Author yuzhenhai
 * @Date 2023/02/25 10:43
 **/

public class AddressCountLimitException extends ServiceException{
    public AddressCountLimitException() {
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

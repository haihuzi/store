package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 用户登录不存在异常
 * @Author yuzhenhai
 * @Date 2023/02/23 20:17
 **/


public class UserNotFoundException extends ServiceException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

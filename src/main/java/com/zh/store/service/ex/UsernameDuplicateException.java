package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 用户名重复的异常
 * @Author yuzhenhai
 * @Date 2023/02/23 12:30
 **/

public class UsernameDuplicateException extends ServiceException{
    public UsernameDuplicateException() {
        super();
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

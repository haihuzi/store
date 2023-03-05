package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 非法访问的异常
 * @Author yuzhenhai
 * @Date 2023/02/27 14:54
 **/

public class AccessDeniedException extends ServiceException{
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

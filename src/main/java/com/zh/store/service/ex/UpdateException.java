package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 修改用户异常
 * @Author yuzhenhai
 * @Date 2023/02/23 22:26
 **/

public class UpdateException extends ServiceException{
    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

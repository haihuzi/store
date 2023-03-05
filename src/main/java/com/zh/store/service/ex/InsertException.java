package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 数据库在插入时的异常
 * @Author yuzhenhai
 * @Date 2023/02/23 12:34
 **/

public class InsertException extends ServiceException{
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

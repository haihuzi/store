package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 删除数据失败的异常
 * @Author yuzhenhai
 * @Date 2023/02/27 15:11
 **/

public class DeleteException extends ServiceException{
    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 创建目录异常
 * @Author yuzhenhai
 * @Date 2023/02/27 13:01
 **/

public class ImoocMallException extends ServiceException{
    public ImoocMallException() {
    }

    public ImoocMallException(String message) {
        super(message);
    }

    public ImoocMallException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImoocMallException(Throwable cause) {
        super(cause);
    }

    public ImoocMallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

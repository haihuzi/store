package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 上传文件时读写异常
 * @Author yuzhenhai
 * @Date 2023/02/27 12:18
 **/

public class FileUploadIOException extends FileUploadException{
    public FileUploadIOException() {
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    public FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

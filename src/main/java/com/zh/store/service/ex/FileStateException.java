package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 上传的文件状态异常
 * @Author yuzhenhai
 * @Date 2023/02/27 12:18
 **/

public class FileStateException extends FileUploadException{
    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

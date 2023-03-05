package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 上传的文件的大小超出了限制值
 * @Author yuzhenhai
 * @Date 2023/02/27 12:17
 **/

public class FileSizeException extends FileUploadException{
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.zh.store.service.ex;

/**
 * TODO
 *
 * @Description 上传的文件为空的异常，例如没有选择上传的文件就提交了表单，或选择的文件是0字节的空文件
 * @Author yuzhenhai
 * @Date 2023/02/27 12:17
 **/

public class FileEmptyException extends FileUploadException{
    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

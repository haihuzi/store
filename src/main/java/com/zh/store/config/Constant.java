package com.zh.store.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * TODO
 *
 * @Description 存放常量
 * @Author yuzhenhai
 * @Date 2023/02/27 12:35
 **/

public class Constant {

    /**设置上传图片的存储路径*/
    public static String FILE_UPLOAD_DIR;

    /**需要spring注入这个value，因此需要创建bean，使用@component注解 */
    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

}

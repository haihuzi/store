package com.zh.store.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * TODO
 *
 * @Description 生成md5
 * @Author yuzhenhai
 * @Date 2023/02/23 22:42
 **/

public class Md5Util {
    /***
     * @Description md5加密
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 22:46
     * @param oldVal
     * @Param salt
     * @Return: java.lang.String
     **/
    public static String getMd5(String oldVal,String salt){
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            oldVal = DigestUtils.md5DigestAsHex((salt + oldVal +
                    salt).getBytes()).toUpperCase();
        }
        return oldVal;
    }

}

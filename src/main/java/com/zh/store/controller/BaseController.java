package com.zh.store.controller;

import com.zh.store.service.ex.*;
import com.zh.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @Description 控制器类的基类
 * @Author yuzhenhai
 * @Date 2023/02/23 16:12
 **/

public class BaseController {

    /** 操作成功的状态码 */
    public static final int OK = 200;

    /***
     * @Description 获取当前登录用户的uid
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 16:19
     * @param session
     * @Return: java.lang.Integer
     **/
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /***
     * @Description 获取当前用户的用户名
     * @Author yuzhenhai
     * @Date 2023/2/23 0023 16:20
     * @param session
     * @Return: java.lang.String
     **/
    protected final String getUnameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    /***
    * @Description 统一处理异常
    * @Author yuzhenhai
    * @Date 2023/2/23 0023 16:54
    * @param e
    * @Return: com.zh.store.utils.JsonResult<java.lang.Void>
    **/
    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof InsertException) {
            result.setState(500);
        }else if (e instanceof UsernameDuplicateException) {
            result.setState(401);
        }else if (e instanceof UserNotFoundException) {
            result.setState(402);
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(403);
        }else if (e instanceof UpdateException) {
            result.setState(406);
        }else if (e instanceof AddressCountLimitException){
            result.setState(407);
        }else if (e instanceof FileEmptyException) {
            result.setState(600);
        } else if (e instanceof FileSizeException) {
            result.setState(601);
        } else if (e instanceof FileTypeException) {
            result.setState(602);
        } else if (e instanceof FileStateException) {
            result.setState(603);
        } else if (e instanceof FileUploadIOException) {
            result.setState(604);
        }else if (e instanceof AddressNotFoundException) {
            result.setState(404);
        } else if (e instanceof AccessDeniedException) {
            result.setState(405);
        }else if (e instanceof DeleteException) {
            result.setState(502);
        }else if (e instanceof ProductNotFoundException) {
            result.setState(406);
        }else if (e instanceof CartNotFoundException) {
            result.setState(407);
        }
        return result;
    }

}

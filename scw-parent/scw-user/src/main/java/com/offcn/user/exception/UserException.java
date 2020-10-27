package com.offcn.user.exception;

import com.offcn.user.enums.UserExceptionEnum;

/**
 * @author 胡长生
 * @create 2020-10-22 22:56
 */
public class UserException extends RuntimeException {
    public UserException(UserExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
    }

}

package com.exam.exam_backend.model.request;

import lombok.Data;

/**
 * 登录请求类
 */
@Data
public class UserLoginRequest {
    //用户名
    private String username;
    //密码
    private String password;
}

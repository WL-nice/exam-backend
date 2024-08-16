package com.exam.exam_backend.service;

import com.exam.exam_backend.model.User;
import jakarta.servlet.http.HttpServletRequest;


public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求
     * @return
     */
    User login(String username, String password, HttpServletRequest request);


    /**
     * 获取登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户登出
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}

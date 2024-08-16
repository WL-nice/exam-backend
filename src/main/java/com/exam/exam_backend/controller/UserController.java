package com.exam.exam_backend.controller;

import com.exam.exam_backend.common.BaseResponse;
import com.exam.exam_backend.common.ErrorCode;
import com.exam.exam_backend.common.ResultUtils;
import com.exam.exam_backend.exception.BusinessException;
import com.exam.exam_backend.model.User;
import com.exam.exam_backend.model.request.UserLoginRequest;
import com.exam.exam_backend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        //判断是否为空
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        User loginUser = userService.login(username, password, request);
        return ResultUtils.success(loginUser);
    }

    @GetMapping("/hello")
    public BaseResponse<String> helloWorld(HttpServletRequest request) {
        //获取登录用户
        User loginUser = userService.getLoginUser(request);

        //判断是否登录
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NO_LOGIN);
        }
        return ResultUtils.success("hello world");

    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

}

package com.exam.exam_backend.service.impl;


import com.exam.exam_backend.common.ErrorCode;
import com.exam.exam_backend.exception.BusinessException;
import com.exam.exam_backend.model.User;
import com.exam.exam_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 盐值
     */
    private static final String SALT = "exam";

    /**
     * 测试账号
     */
    private static final String USERNAME = "test";

    /**
     * 测试密码
     */
    private static final String PASSWORD = "123456";

    /**
     * 用户登录态
     */
    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public User login(String username, String password, HttpServletRequest request) {
        //校验
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号或密码不能为空");
        }
        User loginUser = new User();
        //加密
        String userPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        String truePassword = DigestUtils.md5DigestAsHex((SALT + PASSWORD).getBytes());
        if (!username.equals(USERNAME) || !userPassword.equals(truePassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号或密码错误");
        }
        loginUser.setUsername(username);

        //记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, loginUser);
        return loginUser;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NO_LOGIN);
        }
        //获取当前登录用户
        Object user = request.getSession().getAttribute(USER_LOGIN_STATE);
        return (User) user;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        //移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}

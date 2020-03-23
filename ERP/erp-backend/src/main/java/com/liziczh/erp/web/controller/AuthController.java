package com.liziczh.erp.web.controller;

import com.liziczh.erp.common.code.BaseRestCode;
import com.liziczh.erp.common.response.BaseRestResponse;
import com.liziczh.erp.dto.request.LoginDTO;
import com.liziczh.erp.entity.User;
import com.liziczh.erp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jehaw-Chen
 */

@Api(tags = "用户模块")
@RestController
@RequestMapping("api/erp/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public BaseRestResponse loginByUsername(@RequestBody LoginDTO loginDTO) {
        User user = userService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword());
        if (user == null) {
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "用户名或密码不正确");
        }
        return BaseRestResponse.ok(user);
    }

}

package com.liziczh.erp.web.controller;

import com.liziczh.erp.common.code.BaseRestCode;
import com.liziczh.erp.common.response.BaseRestResponse;
import com.liziczh.erp.common.response.PageRestResponse;
import com.liziczh.erp.dto.request.ManagedUserDTO;
import com.liziczh.erp.dto.request.PasswordDTO;
import com.liziczh.erp.dto.request.UserDTO;
import com.liziczh.erp.entity.User;
import com.liziczh.erp.entity.UserRole;
import com.liziczh.erp.service.UserService;
import com.liziczh.erp.web.exception.EmailAlreadyUsedException;
import com.liziczh.erp.web.exception.LoginAlreadyUsedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Jehaw-Chen
 */

@Api(tags = "用户模块")
@RestController
@RequestMapping("api/erp")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页条件查询用户
     *
     * @return
     */
    @ApiOperation(value = "分页条件查询用户")
    @GetMapping("/users")
    public PageRestResponse pagingQueryUserList(
            @RequestParam Integer roleId, @RequestParam String search,
            @RequestParam Integer currentPage, @RequestParam Integer limit
    ) {
        Page<User> response = userService.pagingQueryUserList(roleId, search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @ApiOperation(value = "查询所有用户")
    @GetMapping("/user/all")
    public BaseRestResponse getAllUser() {
        List<User> userList = userService.findAllUser();
        return BaseRestResponse.ok(userList);
    }

    /**
     * 查询所有用户角色
     *
     * @return
     */
    @ApiOperation(value = "查询所有用户角色")
    @GetMapping("/user/roles")
    public BaseRestResponse getAllUserRole() {
        List<UserRole> userRoleList = userService.findAllUserRole();
        return BaseRestResponse.ok(userRoleList);
    }

    /**
     * 新增用户
     *
     * @param managedUserDTO
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping("/user")
    public BaseRestResponse addUser(@RequestBody ManagedUserDTO managedUserDTO) {
        if (userService.findUserByUsername(managedUserDTO.getUsername()).isPresent()) {
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "用户名已被占用");
        } else if (userService.findUserByEmail(managedUserDTO.getEmail()).isPresent()) {
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "邮箱已被占用");
        } else {
            User addUser = userService.addUser(managedUserDTO);
            return BaseRestResponse.ok(addUser);
        }
    }

    /**
     * 修改用户
     *
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "修改用户")
    @PutMapping("user")
    public BaseRestResponse modifyUser(@RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userService.findUserByEmail(userDTO.getEmail());
        if (existingUser.isPresent() && (existingUser.get().getId() != userDTO.getId())) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userService.findUserByUsername(userDTO.getUsername());
        if (existingUser.isPresent() && (existingUser.get().getId() != userDTO.getId())) {
            throw new LoginAlreadyUsedException();
        }
        User modifyUser = userService.modifyUser(userDTO);
        return BaseRestResponse.ok(modifyUser);
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("user/password")
    public BaseRestResponse modifyUserPassword(@RequestBody PasswordDTO passwordDTO) {
        userService.modifyUserPassword(passwordDTO);
        return BaseRestResponse.ok();
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @ApiOperation(value = "删除用户")
    @DeleteMapping("user")
    public BaseRestResponse removeUser(@RequestParam long userId) {
        userService.removeUser(userId);
        return BaseRestResponse.ok();
    }


}

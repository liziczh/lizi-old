package com.liziczh.erp.service;

import com.liziczh.erp.dto.request.ManagedUserDTO;
import com.liziczh.erp.dto.request.PasswordDTO;
import com.liziczh.erp.dto.request.UserDTO;
import com.liziczh.erp.entity.User;
import com.liziczh.erp.entity.UserRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @Description 用户服务
 * @Author czh
 * @Date 2018/10/8
 */

public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User loginByUsername(String username, String password);

    /**
     * 分页条件查询用户
     * @param roleId
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<User> pagingQueryUserList(Integer roleId, String search, Integer currentPage, Integer limit);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 查询所有用户角色
     * @return
     */
    List<UserRole> findAllUserRole();

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    Optional<User> findUserByUsername(String username);

    /**
     * 根据邮箱查找用户
     *
     * @param email
     * @return
     */
    Optional<User> findUserByEmail(String email);

    /**
     * 新增用户
     *
     * @param managedUserDTO
     * @return User
     */
    User addUser(ManagedUserDTO managedUserDTO);

    /**
     * 修改用户信息
     *
     * @param userDTO
     * @return User
     */
    User modifyUser(UserDTO userDTO);

    /**
     * 修改用户密码
     *
     * @param passwordDTO
     * @return User
     */
    User modifyUserPassword(PasswordDTO passwordDTO);

    /**
     * 移除用户
     *
     * @param userId
     */
    void removeUser(long userId);

}

package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.ManagerAdmin;

public interface ManagerAdminMapper {
    // 根据用户账号获取用户
    public ManagerAdmin getManagerAdminByAdminCount(String adminAccount);
}

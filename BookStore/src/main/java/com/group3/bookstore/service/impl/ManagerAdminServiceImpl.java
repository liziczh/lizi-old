package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.ManagerAdminMapper;
import com.group3.bookstore.pojo.ManagerAdmin;
import com.group3.bookstore.service.IManagerAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerAdminServiceImpl implements IManagerAdminService {
    @Autowired
    ManagerAdminMapper mapper;
    @Override
    public ManagerAdmin getManagerAdmin(String adminAccount) {
        ManagerAdmin managerAdmin = mapper.getManagerAdminByAdminCount(adminAccount);
        return managerAdmin;
    }
}

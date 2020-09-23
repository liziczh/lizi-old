package com.lizi.company.dao;

import com.lizi.company.pojo.Company;

import java.util.List;

public interface CompanyMapper {
    // 获取公司信息
    public List<Company> getCompanyList();

}

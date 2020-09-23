package com.lizi.company.service;

import com.lizi.company.pojo.SubCompany;

import java.util.List;

public interface ISubCompanyService {
    // 根据总公司ID查询分公司
    public List<SubCompany> getSubCompanyListByComId(int comId);
}

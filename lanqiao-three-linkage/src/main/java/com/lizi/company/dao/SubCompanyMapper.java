package com.lizi.company.dao;

import com.lizi.company.pojo.SubCompany;

import java.util.List;

public interface SubCompanyMapper {
    // 根据总公司ID获取所有分公司
    public List<SubCompany> getSubCompanyListByComId(int comId);
}

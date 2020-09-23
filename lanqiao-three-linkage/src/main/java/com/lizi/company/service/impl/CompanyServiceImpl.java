package com.lizi.company.service.impl;

import com.lizi.company.dao.CompanyMapper;
import com.lizi.company.pojo.Company;
import com.lizi.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanyList() {
        List<Company> companyList = companyMapper.getCompanyList();
        return companyList;
    }
}

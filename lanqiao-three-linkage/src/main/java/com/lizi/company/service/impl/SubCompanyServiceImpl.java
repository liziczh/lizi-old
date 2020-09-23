package com.lizi.company.service.impl;

import com.lizi.company.dao.SubCompanyMapper;
import com.lizi.company.pojo.SubCompany;
import com.lizi.company.service.ISubCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCompanyServiceImpl implements ISubCompanyService {

    @Autowired
    private SubCompanyMapper subCompanyMapper;

    @Override
    public List<SubCompany> getSubCompanyListByComId(int comId) {
        List<SubCompany> subCompanyList = subCompanyMapper.getSubCompanyListByComId(comId);
        return subCompanyList;
    }

}

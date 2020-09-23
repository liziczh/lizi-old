package com.lizi.company.service.impl;

import com.lizi.company.dao.EmpMapper;
import com.lizi.company.pojo.Emp;
import com.lizi.company.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements IEmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getEmpListBySubComId(int subComId) {
        List<Emp> empList = empMapper.getEmpListBySubComId(subComId);
        return empList;
    }
}

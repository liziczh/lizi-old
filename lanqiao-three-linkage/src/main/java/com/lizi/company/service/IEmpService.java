package com.lizi.company.service;

import com.lizi.company.pojo.Emp;

import java.util.List;

public interface IEmpService {
    // 根据分公司ID获取员工
    public List<Emp> getEmpListBySubComId(int subComId);
}

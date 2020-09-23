package com.lizi.company.dao;

import com.lizi.company.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    // 根据分公司ID获取员工信息
    public List<Emp> getEmpListBySubComId(int subComId);
}

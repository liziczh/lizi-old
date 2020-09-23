package com.lizi.test;

import com.lizi.company.dao.CompanyMapper;
import com.lizi.company.dao.EmpMapper;
import com.lizi.company.dao.SubCompanyMapper;
import com.lizi.company.pojo.Company;
import com.lizi.company.pojo.Emp;
import com.lizi.company.pojo.SubCompany;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CompanyMapperTest {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private SubCompanyMapper subCompanyMapper;

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void getCompanyListTest(){
        List<Company> companyList = companyMapper.getCompanyList();
        System.out.println(companyList);
    }

    @Test
    public void getSubCompanyListTest(){
        List<SubCompany> subCompanyList = subCompanyMapper.getSubCompanyListByComId(1);
        System.out.println(subCompanyList);
    }

    @Test
    public void getEmpTestListTest(){
        List<Emp> empList = empMapper.getEmpListBySubComId(1);
        System.out.println(empList);
    }

}

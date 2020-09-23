package com.lizi.company.controller;

import com.alibaba.fastjson.JSON;
import com.lizi.company.pojo.Company;
import com.lizi.company.pojo.Emp;
import com.lizi.company.pojo.SubCompany;
import com.lizi.company.service.ICompanyService;
import com.lizi.company.service.IEmpService;
import com.lizi.company.service.ISubCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/com/lizi/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ISubCompanyService subCompanyService;

    @Autowired
    private IEmpService empService;

    @RequestMapping(value = "/getCompany")
    public ModelAndView getCompany(){
        List<Company> companyList = companyService.getCompanyList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyList",companyList);
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/getSubCompany")
    @ResponseBody
    public void getSubCompany(@RequestParam("comId") int comId,HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        List<SubCompany> subCompanyList = subCompanyService.getSubCompanyListByComId(comId);
        String json = JSON.toJSONString(subCompanyList);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getEmp", produces = "application/json;charset=utf-8")
    @ResponseBody
    public void getEmp(@RequestParam("subComId") int subComId, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        List<Emp> empList = empService.getEmpListBySubComId(subComId);
        String json = JSON.toJSONString(empList);
        System.out.println(json);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.ManagerAdmin;
import com.group3.bookstore.service.IManagerAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/managerAdmin")
public class ManagerLoginController {

    @Autowired
    private IManagerAdminService iManagerAdminService;

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String account, String password, String verifyCode, HttpSession session){
        ManagerAdmin managerAdmin = null;
        ModelAndView modelAndView = new ModelAndView();
        // 错误信息 Map
        Map<String,String> errors = new HashMap<>();
        // 账号验证
        if(account == null || account.equals("")){
            errors.put("accountMsg","输入不能为空");
        }
        // 密码验证
        if(password == null || password.equals("")){
            errors.put("passwordMsg","输入不能为空");
        }
        // 验证码验证
        String checkCode = String.valueOf(session.getAttribute("verifyCode"));
        if(verifyCode == null || verifyCode.equals("")){
            errors.put("verifyCodeMsg","输入不能为空");
        }else if(!verifyCode.equalsIgnoreCase(checkCode)){
            errors.put("verifyCodeMsg","验证码输入有误");
        }

        // 登录验证
        if(iManagerAdminService.getManagerAdmin(account) == null){
            errors.put("accountMsg","用户不存在");
        }else{
            managerAdmin = iManagerAdminService.getManagerAdmin(account);
            System.out.println(managerAdmin.getPassword()+managerAdmin.getUserAccount());
            if(!managerAdmin.getPassword().equals(password)){
                errors.put("passwordMsg","密码输入错误");
            }
        }

        // 验证 跳转
        if(errors.isEmpty()){
            modelAndView.addObject("managerAdmin",managerAdmin);
            modelAndView.setViewName("manager/admin");
        }else{
            modelAndView.addObject("errors",errors);
            modelAndView.addObject("account",account);
            modelAndView.addObject("password",password);
            modelAndView.setViewName("forward:/managerLogin.jsp");
        }
        return modelAndView;
    }

}

package com.lizi.user.controller;

import com.lizi.user.pojo.User;
import com.lizi.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username,password);
        ModelAndView modelAndView = new ModelAndView();
        Map<String,String> errors = new HashMap();
        if(user == null){
            errors.put("loginMsg","登录名或密码错误");
        }
        if (errors.isEmpty()){
            modelAndView.setViewName("success");
        }else{
            modelAndView.setViewName("failed");
        }
        return modelAndView;
    }
}

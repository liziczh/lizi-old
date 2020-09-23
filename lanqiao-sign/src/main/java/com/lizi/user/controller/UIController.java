package com.lizi.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

    @RequestMapping("/loginUI")
    public String loginUI(){
        return "login";
    }
}

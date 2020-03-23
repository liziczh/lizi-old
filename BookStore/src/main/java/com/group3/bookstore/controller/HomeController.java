package com.group3.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.group3.bookstore.pojo.Commodity;
import com.group3.bookstore.service.impl.CategoryServiceImpl;
import com.group3.bookstore.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/main")
public class HomeController {
    @Autowired
    CommodityServiceImpl commodityService;
    @RequestMapping(value = "/refresh")
//    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//        PrintWriter out =response.getWriter();
//        String json = JSON.toJSONString(commodityService.selectByTime());
//        out.write(json);
//        System.out.println(json);
//        json = JSON.toJSONString(commodityService.selectBySaleCount());
//        out.write(json);
//        System.out.println(json);
//        response.setCharacterEncoding("UTF-8");
//        request.getSession().setAttribute("new",commodityService.selectByTime());
//        request.getSession().setAttribute("hot",commodityService.selectBySaleCount());
//        request.getRequestDispatcher("/index.jsp").forward(request,response);
//    }
    public ModelAndView refresh(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("commodity/main");
        modelAndView.addObject("news",commodityService.selectByTime());
        modelAndView.addObject("hot",commodityService.selectBySaleCount());
        System.out.println(commodityService.selectBySaleCount());
        return modelAndView;
    }
}

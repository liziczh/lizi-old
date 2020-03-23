package com.group3.bookstore.controller;

import com.group3.bookstore.service.ICategoryService;
import com.group3.bookstore.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/category")
public class CategoryCotroller {
    @Autowired
    ICategoryService categoryService;
    @RequestMapping(value = "secondclass",method = RequestMethod.GET)
    public ModelAndView secondCategoryClass(String ancestorCategory)
    {
        ModelAndView modelAndView =new ModelAndView("sortgoods","categories",categoryService.selectChildCategory(ancestorCategory));
        return  modelAndView;
    }
    @RequestMapping(value = "thirdclass",method = RequestMethod.GET)
    public ModelAndView thirdCategoryClass(String secondCategory)
    {
        ModelAndView modelAndView =new ModelAndView("sortgoods","categories",categoryService.selectChildCategory(secondCategory));
        return  modelAndView;
    }
}

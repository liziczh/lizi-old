package com.group3.bookstore.service;

import com.group3.bookstore.pojo.Category;

import java.util.List;

public interface ICategoryService {
    //查找所有父分类
    public List<Category> selectAncestorCategory();
    //查找某分类的子分类
    public List<Category> selectChildCategory(String categoryId);
    //查找某分类的父分类
    public List<Category> selectParrentCategory(String categoryId);

}

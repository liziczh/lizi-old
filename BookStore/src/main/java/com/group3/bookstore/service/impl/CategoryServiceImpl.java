package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.CategoryMapper;
import com.group3.bookstore.pojo.Category;
import com.group3.bookstore.service.ICategoryService;
import com.group3.bookstore.service.ICommodityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private CategoryMapper  mapper;
    @Override
    public List<Category> selectAncestorCategory() {
        return mapper.selectAncestorCategory();
    }

    @Override
    public List<Category> selectChildCategory(String categoryId) {
        return mapper.selectChildCategory(categoryId);
    }

    @Override
    public List<Category> selectParrentCategory(String categoryId) {
        return mapper.selectParrentCategory(categoryId);
    }


}

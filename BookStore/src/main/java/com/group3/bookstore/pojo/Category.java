package com.group3.bookstore.pojo;

import java.util.Objects;

public class Category {
    private String categoryId;
    private String categoryName;
    private String parrentCategoryId;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryId, String categoryName, String parrentCategoryId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parrentCategoryId = parrentCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) &&
                Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(parrentCategoryId, category.parrentCategoryId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryName, parrentCategoryId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", parrentCategoryId='" + parrentCategoryId + '\'' +
                '}';
    }
}

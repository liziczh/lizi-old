package com.panda.bbs.service.impl;

import com.panda.bbs.dao.IPostDao;
import com.panda.bbs.dao.impl.PostDaoImpl;
import com.panda.bbs.domain.Post;
import com.panda.bbs.service.IPostService;

import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements IPostService {
    IPostDao postDao = new PostDaoImpl();
    // 获取所有文章
    @Override
    public List<Post> getAllPost() {
        List<Post> postList = null;
        try {
            postList = postDao.getAllPost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
    // 获取所有文章根据分类&排序方式&分页
    @Override
    public List<Post> getPostByDirAndOrder(String dir, String order, int currentPage, int pageSize) {
        List<Post> postList = null;
        try {
            postList = postDao.getPostByDirAndOrder(dir,order,currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
    // 获取文章数量根据分类&排序方式&分页
    @Override
    public int getTotalByDirAndOrder(String dir, String order) {
        int postTotal = 0;
        try {
            postTotal = postDao.getTotalByDirAndOrder(dir,order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postTotal;
    }
    // 根据文章ID获取文章
    @Override
    public Post getPostByPostId(String postId) {
        Post post = null;
        try {
            post = postDao.getPostByPostId(postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    // 根据文章标题模糊查询
    @Override
    public List<Post> getPostByTitle(String postTitle) {
        List<Post> postList = null;
        try {
            postList = postDao.getPostByTitle(postTitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
    // 根据作者ID获取文章
    @Override
    public List<Post> getPostByAuthorId(String authorId) {
        List<Post> postList = null;
        try {
            postList = postDao.getPostByAuthorId(authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
    // 发帖
    @Override
    public void addPost(Post post) {
        try {
            postDao.insertPost(post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 编辑帖子
    @Override
    public void modifyPost(Post post) {
        try {
            postDao.updatePost(post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删帖
    @Override
    public void removePost(String postId) {
        try {
            postDao.deletePostById(postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

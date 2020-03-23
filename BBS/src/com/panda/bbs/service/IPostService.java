package com.panda.bbs.service;

import com.panda.bbs.domain.Post;

import java.util.List;

public interface IPostService {
    // 展示文章
    public List<Post> getAllPost();
    // 获取所有文章根据分类&排序方式&分页
    public List<Post> getPostByDirAndOrder(String dir, String order, int currentPage, int pageSize);
    // 获取所有文章数量根据分类&排序方式&分页
    public int getTotalByDirAndOrder(String dir, String order);
    // 根据文章ID获取文章
    public Post getPostByPostId(String postId);
    // 根据文章标题模糊查询
    public List<Post> getPostByTitle(String postTitle);
    // 根据作者ID获取文章
    public List<Post> getPostByAuthorId(String authorId);
    // 发帖
    public void addPost(Post post);
    // 编辑帖子
    public void modifyPost(Post post);
    // 删帖
    public void removePost(String postId);

}

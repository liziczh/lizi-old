package com.panda.bbs.dao;

import com.panda.bbs.domain.Post;

import java.sql.SQLException;
import java.util.List;

public interface IPostDao {
    // 获取所有文章
    public List<Post> getAllPost() throws SQLException;
    // 获取文章根据分类&排序方式&分页
    public List<Post> getPostByDirAndOrder(String dir, String order, int currentPage, int pageSize) throws SQLException;
    // 获取文章数量根据分类&排序方式&分页
    public int getTotalByDirAndOrder(String dir, String order) throws SQLException;
    // 根据文章ID获取文章
    public Post getPostByPostId(String postId) throws SQLException;
    // 根据作者ID获取文章
    public List<Post> getPostByAuthorId(String authorId) throws SQLException;
    // 根据文章标题模糊查询
    public List<Post> getPostByTitle(String title) throws SQLException;
    // 新增一篇文章
    public void insertPost(Post post) throws SQLException;
    // 更新一篇文章
    public void updatePost(Post post) throws SQLException;
    // 删除一篇文章
    public void deletePostById(String postId) throws SQLException;

}

package com.panda.bbs.dao.impl;

import com.panda.bbs.dao.IPostDao;
import com.panda.bbs.domain.Post;
import com.panda.bbs.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class PostDaoImpl implements IPostDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    // 获取所有文章
    @Override
    public List<Post> getAllPost() throws SQLException {
        String sql = "select * from post order by updateTime desc";
        List<Post> postList = queryRunner.query(sql,new BeanListHandler<Post>(Post.class));
        return postList;
    }

    // 获取文章根据分类&排序方式&分页
    @Override
    public List<Post> getPostByDirAndOrder(String dir, String order, int currentPage, int pageSize) throws SQLException {
        // 主SQL语句
        String sql = " select * from post ";
        // 排序，默认按最新
        String orderSql = " order by updateTime desc ";
        if(order.equals("按热议")){
            orderSql = " order by commentCount desc ";
        }
        // 分页语句
        String pagenation = " limit ?,?";
        // 分类
        String dirSql = "";
        List<Post> postList = null;
        if(!dir.equals("")){
            // 分类查询
            dirSql = " where dir = ? ";
            postList = queryRunner.query(sql+dirSql+orderSql+pagenation,new BeanListHandler<Post>(Post.class),dir,(currentPage-1)*pageSize, pageSize);
        }else{
            // 查询全部
            postList = queryRunner.query(sql+dirSql+orderSql+pagenation,new BeanListHandler<Post>(Post.class),(currentPage-1)*pageSize, pageSize);
        }
        return postList;
    }

    // 获取文章总数
    @Override
    public int getTotalByDirAndOrder(String dir, String order) throws SQLException {
        // 主SQL语句
        String sql = " select count(*) from post ";
        // 分类
        String dirSql = "";
        int postTotal = 0;
        if(dir.equals("") || dir == null){
            // 查询全部
            postTotal = Integer.parseInt(queryRunner.query(sql+dirSql,new ScalarHandler<>(1)).toString());
        }else{
            // 分类查询
            dirSql = " where dir = ? ";
            postTotal = Integer.parseInt(queryRunner.query(sql+dirSql,new ScalarHandler<>(1),dir).toString());
        }
        return postTotal;
    }

    // 根据文章ID获取文章
    @Override
    public Post getPostByPostId(String postId) throws SQLException {
        String sql = "select * from post where postId = ?";
        Post post = queryRunner.query(sql,new BeanHandler<Post>(Post.class),postId);
        return post;
    }

    // 根据作者ID获取文章
    @Override
    public List<Post> getPostByAuthorId(String authorId) throws SQLException {
        String sql = "select * from post where authorId = ? order by updateTime desc";
        List<Post> postList = queryRunner.query(sql,new BeanListHandler<Post>(Post.class),authorId);
        return postList;
    }

    // 根据文章标题模糊查询
    @Override
    public List<Post> getPostByTitle(String title) throws SQLException {
        String sql = "select * from post where postTitle like ?";
        List<Post> postList = queryRunner.query(sql,new BeanListHandler<Post>(Post.class),title);
        return postList;
    }

    // 新增一篇文章
    @Override
    public void insertPost(Post post) throws SQLException {
        String sql = "insert into post values( ?,?,?,?,?,?,?,?,?,?) ";
        queryRunner.update(sql,post.getPostId(),post.getAuthorId(),post.getDir(),post.getPostTitle(),post.getPostContent(),post.getCreateTime(),post.getUpdateTime(),post.getLikeCount(),post.getCommentCount(),post.getPostViewCount());
    }

    // 更新一篇文章
    @Override
    public void updatePost(Post post) throws SQLException {
        String sql = "update post set postTitle = ?, postContent = ?,updateTime = ?,likeCount = ?,commentCount = ?,postViewCount = ? where postId = ?";
        queryRunner.update(sql,post.getPostTitle(),post.getPostContent(),post.getUpdateTime(),post.getLikeCount(),post.getCommentCount(),post.getPostViewCount(),post.getPostId());
    }

    // 删除一篇文章
    @Override
    public void deletePostById(String postId) throws SQLException {
        String sql = "delete from post where postId = ?";
        queryRunner.update(sql,postId);
    }

}

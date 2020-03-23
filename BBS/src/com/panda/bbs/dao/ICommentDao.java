package com.panda.bbs.dao;

import com.panda.bbs.domain.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentDao {
    //    给数据库评论表中新增一条评论
    public void insertComment(Comment comment) throws SQLException;
    //    给数据库评论添加一条回复评论
    //    public void insertReplyComment(int commentId,String replyCommentContent);
    //    根据评论ID删除数据库评论表中的一条评论
    public void deleteCommentByCommentId(String commentId) throws SQLException;
    //    根据用户ID查询评论，查出一个用户之前参与过的所有评论
    public List<Comment> selectCommentByUserId(String userId) throws SQLException;
    //    根据帖子ID查询评论，查出关于此帖子的所有评论
    public List<Comment> selectCommentByPostId(String postId) throws SQLException;
    //    根据评论ID查询一条评论的点赞总数
    public int selectCommentthumbUpNum(String commentId) throws SQLException;
    //    根据帖子ID查询该帖子的评论总数
    public int selectCommentCountByPostId(String postId) throws SQLException;
    //    根据帖子ID查询用户对象
//    public User selectUserByCommentUserId(String commentUserId) throws SQLException;
    //根据评论id查询评论对象
    public Comment selectCommentByCommentId(String commentId) throws SQLException;
}

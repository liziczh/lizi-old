package com.panda.bbs.service;

import com.panda.bbs.domain.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentService {
    public void addComment(Comment comment) throws SQLException;
    public void removeComment(String commentId) throws SQLException;
    public List<Comment> queryCommentByUserId(String userId) throws SQLException;
    public List<Comment> queryCommentByPostId(String postId) throws SQLException;
    public int queryCommentthumbUpNum(String commentId) throws SQLException;
    public int queryCommentCountByPostId(String postId) throws SQLException;
//    public CommentDetail queryUserByCommentUserId(String commentUserId) throws SQLException;
    public Comment queryCommentByCommentId(String commentId) throws SQLException;
}

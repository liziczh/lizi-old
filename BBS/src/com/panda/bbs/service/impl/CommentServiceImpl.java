package com.panda.bbs.service.impl;

import com.panda.bbs.dao.ICommentDao;
import com.panda.bbs.dao.impl.CommentDaoImpl;
import com.panda.bbs.domain.Comment;
import com.panda.bbs.service.ICommentService;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements ICommentService {
//  创建dao对象，dao接口指向dao实现
    ICommentDao dao = new CommentDaoImpl();
//   调用dao对象的新增评论方法
    @Override
    public void addComment(Comment comment) throws SQLException {
      dao.insertComment(comment);
    }
//   调用dao对象的删除评论方法
    @Override
    public void removeComment(String commentId) throws SQLException {
        dao.deleteCommentByCommentId(commentId);
    }
//   调用dao对象的根据用户ID查询评论方法
    @Override
    public List<Comment> queryCommentByUserId(String userId) throws SQLException {
       List<Comment> commentList =  dao.selectCommentByUserId(userId);
        return commentList;

    }
//    调用dao对象的根据帖子ID查询评论方法
    @Override
    public List<Comment> queryCommentByPostId(String postId) throws SQLException {
        List<Comment> commentList = dao.selectCommentByPostId(postId);
        return commentList;
    }
//    调用dao对象的根据评论ID查询点赞数方法
    @Override
    public int queryCommentthumbUpNum(String commentId) throws SQLException {
        return dao.selectCommentthumbUpNum(commentId);
    }

    @Override
    public int queryCommentCountByPostId(String postId) throws SQLException {
        return dao.selectCommentCountByPostId(postId);
    }

    @Override
    public Comment queryCommentByCommentId(String commentId) throws SQLException {
        return dao.selectCommentByCommentId(commentId);
    }

//    @Override
//    public CommentDetail queryUserByCommentUserId(String commentUserId) throws SQLException {
//
//        return dao.selectUserByCommentUserId(commentUserId);
//    }

}

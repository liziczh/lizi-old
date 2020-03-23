package com.panda.bbs.dao.impl;

import com.panda.bbs.dao.ICommentDao;
import com.panda.bbs.domain.Comment;
import com.panda.bbs.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;


public class CommentDaoImpl implements ICommentDao {
    //    使用QueryRunner类,实现对数据表的 insert delete update ，，
//    创建QueryRunner类对象
    private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    //    实现给数据库评论表中新增一条评论的方法
    @Override
    public void insertComment(Comment comment) throws SQLException {
//    写增加评论的SQL语句
        String sql = "insert into comment (commentId,commentUserId,postId,commentContent,thumbUpNum,commentTime) values (?,?,?,?,?,?)";
//      格式化当前时间，设置当前时间为评论创建时间
//        String str1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //    调用QueryRunner类的方法实现新增 update (String sql,Object...param)，Object...param 为可变参数,Object类型,SQL语句会出现?占位符
        qr.update(sql,comment.getCommentId(),comment.getCommentUserId(), comment.getPostId(), comment.getCommentContent(),comment.getThumbUpNum(), comment.getCommentTime());
    }

//    @Override
//    public void insertReplyComment(int commentId, String replyCommentContent) {
//        String sql = "insert into comment (userId,postId,commentContent,commentTime,thumbUpNum,replyCommentId)values(?,?,?,?,?,?)";
//
//    }

    //    实现根据评论ID删除数据库评论表中的一条评论的方法
    @Override
    public void deleteCommentByCommentId(String commentId) throws SQLException {
//    写删除评论的SQL语句
        String sql = "delete from comment where commentId = ?";
//    调用QueryRunner类的方法 update(String sql,Object param)实现删除
        qr.update(sql, commentId);
    }
    //    实现根据用户ID查询评论，查出一个用户之前参与过的所有评论的方法
    @Override
    public List<Comment> selectCommentByUserId(String userId) throws SQLException {
//    写根据userId查询评论的SQL语句
        String sql = "select * from comment where userId = ?";
//    调用QueryRunner类方法query(String sql,ResultSetHandler r,Object..params)
//	  ResultSetHandler r 结果集的处理方式，传递ResultSetHandler接口实现类  ,Object...params sql语句的?占位符
//    query方法返回值，返回的是T 泛型，具体返回值类型，跟随结果集处理方式变化,此处返回的是评论对象列表
//    BeanListHandler<>(Comment.class) 将数据结果集的每一行数据，封装为JavaBean对象，多个JavaBean对象封装到List集合中
        List<Comment> commentList =qr.query(sql,new BeanListHandler<>(Comment.class),userId);
        return commentList;
    }
    //    实现根据帖子ID查询评论，查出关于此帖子的所有评论
    @Override
    public List<Comment> selectCommentByPostId(String postId) throws SQLException {
        String sql = "select * from comment where postId = ?";
        List<Comment> commentList =qr.query(sql,new BeanListHandler<>(Comment.class),postId);
        return commentList;
    }
    //   实现根据评论ID查询一条评论的点赞总数
    @Override
    public int selectCommentthumbUpNum(String commentId) throws SQLException {
        String sql = "select thumbUpNum from comment where commentId = ?";
        Comment comment = qr.query(sql,new BeanHandler<>(Comment.class),commentId);
        return comment.getThumbUpNum();
    }
    //  根据帖子ID查询该帖子的评论总数
    @Override
    public int selectCommentCountByPostId(String postId) throws SQLException {
        String sql = "select COUNT(commentId) from comment where postId = ?";
        return (qr.query(sql,new ScalarHandler<Long>(),postId)).intValue();
    }

    @Override
    public Comment selectCommentByCommentId(String commentId) throws SQLException {
        String sql = "select * from comment where commentId = ?";
        return (qr.query(sql,new BeanHandler<Comment>(Comment.class),commentId));

    }

//    @Override
//    public CommentDetail selectUserByCommentUserId(String commentUserId) throws SQLException {
//        String sql ="select user from user where userId = ?";
//        CommentDetail CommentDetail  = qr.query(sql,new BeanHandler<CommentDetail>(CommentDetail.class),commentUserId);
//        return CommentDetail;
//    }
}


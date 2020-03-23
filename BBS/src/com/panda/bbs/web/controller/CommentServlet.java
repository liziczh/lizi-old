package com.panda.bbs.web.controller;

import com.panda.bbs.domain.Comment;
import com.panda.bbs.domain.CommentDetail;
import com.panda.bbs.domain.Post;
import com.panda.bbs.domain.User;
import com.panda.bbs.service.ICommentService;
import com.panda.bbs.service.IPostService;
import com.panda.bbs.service.IUserService;
import com.panda.bbs.service.impl.CommentServiceImpl;
import com.panda.bbs.service.impl.PostServiceImpl;
import com.panda.bbs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/commentServlet")
public class CommentServlet extends HttpServlet {
    private IPostService postService = new PostServiceImpl();
    private ICommentService commentService = new CommentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if(method.equals("getCommentByPostId")){
            getCommentByPostId(request, response);
        }else if(method.equals("editComment")){
            editComment(request, response);
        }


    }

    //根据文章ID 获取评论
    protected void getCommentByPostId(HttpServletRequest request, HttpServletResponse response){
        String postId = request.getParameter("postId");
        ICommentService commentService = new CommentServiceImpl();
        IUserService userService = new UserServiceImpl();
        try {
            List<Comment> commentList =  commentService.queryCommentByPostId(postId);
            List<CommentDetail> commentDetailsList = new ArrayList<>();
            for (Comment comment : commentList) {
                User commentUser = userService.getUserByUserId(comment.getCommentUserId());
                CommentDetail commentDetail = new CommentDetail(comment,commentUser,postId);
                commentDetailsList.add(commentDetail);
            }
            request.setAttribute("commentDetailsList",commentDetailsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/WEB-INF/pages/post/post.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 评论
    protected void editComment(HttpServletRequest request, HttpServletResponse response){
        String postId = request.getParameter("postId");
        Post post = postService.getPostByPostId(postId);
        String commentId = String.valueOf(UUID.randomUUID());
        String commentUserId = request.getParameter("commentUserId");
        String commentContent = request.getParameter("commentContent");
        String commentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int thumbUpNum = 0;
        Comment comment = new Comment(commentId,commentUserId,postId,commentContent,commentTime,thumbUpNum);
        try {
            commentService.addComment(comment);
            post.setCommentCount(post.getCommentCount()+1);
            postService.modifyPost(post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("comment",comment);
        try {
            request.getRequestDispatcher("/postServlet?method=getPostDetailByPostId&postId="+postId).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

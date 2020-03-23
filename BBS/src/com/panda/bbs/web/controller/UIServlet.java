package com.panda.bbs.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uiServlet")
public class UIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        // 转发页面 WEB-INF 下
        if(method.equals("indexUI")){
            // 转发首页界面
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else if(method.equals("loginUI")){
            // 转发登录界面
            request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request,response);
        }else if(method.equals("registerUI")){
            // 转发注册界面
            request.getRequestDispatcher("/WEB-INF/pages/user/register.jsp").forward(request,response);
        }else if(method.equals("userUI")){
            // 转发查看个人主页界面
            request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request,response);
        }else if(method.equals("mypostUI")){
            // 转发我的帖子界面
            request.getRequestDispatcher("/WEB-INF/pages/user/mypost.jsp").forward(request,response);
        } else if(method.equals("setUI")){
            // 转发编辑个人信息界面
            request.getRequestDispatcher("/WEB-INF/pages/user/set.jsp").forward(request,response);
        }else if(method.equals("forgetUI")){
            // 转发忘记密码界面
            request.getRequestDispatcher("/WEB-INF/pages/user/forget.jsp").forward(request,response);
        }else if(method.equals("postUI")){
            // 转发文章内容界面
            request.getRequestDispatcher("/WEB-INF/pages/post/post.jsp").forward(request,response);
        }else if(method.equals("addPostUI")){
            // 转发发表新帖界面
            request.getRequestDispatcher("/WEB-INF/pages/post/addPost.jsp").forward(request,response);
        }else if(method.equals("editPostUI")){
            // 转发编辑帖子界面
            request.getRequestDispatcher("/WEB-INF/pages/post/editPost.jsp").forward(request,response);
        }

    }
}

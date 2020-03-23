package com.panda.bbs.web.controller;

import com.panda.bbs.domain.Post;
import com.panda.bbs.domain.User;
import com.panda.bbs.service.IPostService;
import com.panda.bbs.service.IUserService;
import com.panda.bbs.service.impl.PostServiceImpl;
import com.panda.bbs.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();
    private IPostService postService = new PostServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (method.equals("login")) {
            // 登录请求
            login(request, response);
        } else if (method.equals("register")) {
            // 注册请求
            register(request, response);
        } else if (method.equals("logout")) {
            // 注销请求
            logout(request, response);
        } else if (method.equals("getUserByUserId")) {
            // 根据用户ID获取用户
            getUserByUserId(request, response);
        } else if (method.equals("getUserAndPostByUserId")) {
            // 根据用户ID获取用户信息&用户所有文章
            getUserAndPostByUserId(request, response);
        } else if (method.equals("modifyUser")) {
            // 修改个人信息
            modifyUser(request, response);
        } else if (method.equals("uploadAvatar")) {
            uploadAvatar(request, response);
        }
    }

    // 根据用户ID获取用户
    public void getUserByUserId(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        User user = userService.getUserByUserId(userId);
        request.setAttribute("user", user);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据用户ID获取用户信息&用户所有文章
    public void getUserAndPostByUserId(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        // 获取用户信息
        User user = userService.getUserByUserId(userId);
        // 获取用户的所有文章
        List<Post> userPostList = postService.getPostByAuthorId(userId);
        request.setAttribute("user", user);
        request.setAttribute("userPostList", userPostList);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 修改用户信息
    public void modifyUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        // 获取表单数据
        String username = request.getParameter("username");
        String gender = request.getParameter("sex");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String location = request.getParameter("city");
        String bio = request.getParameter("sign");
        // 设置表单数据
        user.setUsername(username);
        user.setGender(gender);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setLocation(location);
        user.setBio(bio);
        userService.editProfile(user);
        try {
            // 获取用户的所有文章
            List<Post> userPostList = postService.getPostByAuthorId(user.getUserId());
            request.setAttribute("user", user);
            request.setAttribute("userPostList", userPostList);
            request.getRequestDispatcher("/WEB-INF/pages/user/user.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    // 登录请求
    public void login(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        // 获取用户输入信息
        String account = request.getParameter("account");
        String password = request.getParameter("pass");
        String verifyCode = request.getParameter("vercode");
        // Email正则表达式
        String regexEmail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        // 手机正则表达式
        String regexPhoneNumber = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        // 错误信息 Map
        Map<String, String> errors = new HashMap<>();
        // 账号验证
        if (account == null || account.equals("")) {
            errors.put("accountMsg", "输入不能为空");
        } else if (!account.matches(regexEmail) && !account.matches(regexPhoneNumber)) {
            errors.put("accountMsg", "手机/邮箱格式不正确");
        }
        // 密码验证
        if (password == null || password.equals("")) {
            errors.put("passwordMsg", "输入不能为空");
        }
        // 验证码验证
        String checkCode = String.valueOf(request.getSession().getAttribute("verifyCode"));
        if (verifyCode == null || verifyCode.equals("")) {
            errors.put("verifyCodeMsg", "输入不能为空");
        } else if (!verifyCode.equalsIgnoreCase(checkCode)) {
            errors.put("verifyCodeMsg", "验证码输入有误");
        }

        // 登录验证
        if (account.matches(regexEmail)) {
            if (!userService.isExistingEmail(account)) {
                errors.put("accountMsg", "邮箱不存在");
            } else {
                user = userService.getUserByEmailAndPassword(account, password);
                if (user == null) {
                    errors.put("passwordMsg", "密码输入错误");
                }
            }
        } else if (account.matches(regexPhoneNumber)) {
            if (!userService.isExistingPhoneNumber(account)) {
                errors.put("accountMsg", "手机号不存在");
            } else {
                user = userService.getUserByPhoneNumberAndPassword(account, password);
                if (user == null) {
                    errors.put("passwordMsg", "密码输入错误");
                }
            }
        }

        // 验证 跳转
        if (errors.isEmpty()) {
            request.getSession().setAttribute("user", user);
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("account", account);
            request.setAttribute("password", password);
            try {
                request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 注册请求
    public void register(HttpServletRequest request, HttpServletResponse response) {
        String userId = String.valueOf(UUID.randomUUID());
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("repass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String avatar = "default.png";
        String joinTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String verifyCode = request.getParameter("vercode");
        String checkCode = String.valueOf(request.getSession().getAttribute("verifyCode"));
        String regexEmail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        String regexPhoneNumber = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        String passwordRegex = "^.*(?=.{6,18})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$";

        Map<String, Object> errors = new HashMap();
        // 用户名验证
        if (username == null || username.equals("")) {
            errors.put("usernameMsg", "输入为空");
        } else if (userService.isExistingUsername(username)) {
            errors.put("usernameMsg", "用户已存在");
        }
        // 密码验证
        if (password == null || password.equals("")) {
            errors.put("passwordMsg", "输入为空");
        } else if (password.matches(passwordRegex)) {
            errors.put("passwordMsg", "请输入6-18位字母、数字、字符");
        }
        // 确认密码
        if (confirmPassword == null || confirmPassword.equals("")) {
            errors.put("confirmPasswordMsg", "输入为空");
        } else if (!password.equals(confirmPassword)) {
            errors.put("confirmPasswordMsg", "两次密码输入不一致");
        }
        // 邮箱验证
        if (email == null || email.equals("")) {
            errors.put("emailMsg", "输入为空");
        } else if (!email.matches(regexEmail)) {
            errors.put("emailMsg", "邮箱格式不正确");
        } else if (userService.isExistingEmail(email)) {
            errors.put("emailMsg", "邮箱已存在");
        }
        // 手机验证
        if (phoneNumber == null || phoneNumber.equals("")) {
            errors.put("phoneNumberMsg", "输入为空");
        } else if (!phoneNumber.matches(regexPhoneNumber)) {
            errors.put("phoneNumberMsg", "手机格式不正确");
        } else if (userService.isExistingPhoneNumber(phoneNumber)) {
            errors.put("phoneNumberMsg", "手机已存在");
        }
        // 验证码
        if (verifyCode == null || verifyCode.equals("")) {
            errors.put("verifyCodeMsg", "输入为空");
        } else if (verifyCode.equals(checkCode)) {
            errors.put("verifyCodeMsg", "验证码输入错误");
        }
        // 验证成功
        if (errors.isEmpty()) {
            User user = new User(userId, username, password, email, phoneNumber, avatar, null, joinTime, null, null, null, null);
            userService.register(user);
            try {
                request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("email", email);
            request.setAttribute("phoneNumber", phoneNumber);
            try {
                request.getRequestDispatcher("/WEB-INF/pages/user/register.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 注销请求
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 上传头像
    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List items = null;
        try {
            items = sfu.parseRequest(request);//从request得到所有上传域的列表
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (Iterator iter = items.iterator(); iter.hasNext(); ) {
            FileItem fileitem = (FileItem) iter.next();
            if (!fileitem.isFormField() && fileitem != null) {//判读不是普通表单域即是file
                String filename = user.getUserId() + fileitem.getName().substring(fileitem.getName().lastIndexOf("."));
                System.out.println(filename);
                FileUtils.copyInputStreamToFile(fileitem.getInputStream(), new File(getDevAvatarPath(request) + filename));
                FileUtils.copyInputStreamToFile(fileitem.getInputStream(), new File(getWarAvatarPath(request) + filename));
                user.setAvatar(filename);
                userService.editProfile(user);
            }
        }
    }

    /*
     * 工具方法：根据userId获取postContent的txt文件路径，用于下载文件
     */
    private static String getDevAvatarPath(HttpServletRequest request) {
        // 开发路径
        String outPath = request.getServletContext().getRealPath("/");
        String realPath = outPath.substring(0, outPath.lastIndexOf("out"));
        String avatarPath = realPath + "web/res/images/avatar/";
        return avatarPath;
    }

    private static String getWarAvatarPath(HttpServletRequest request) {
        // 部署路径
        String avatarPath = request.getServletContext().getRealPath("/") + "/res/images/avatar/";
        return avatarPath;
    }

}

package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService userService;

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String account, String password, String verifyCode, HttpSession session){
        User user = null;
        // Email正则表达式
        String regexEmail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        // 手机正则表达式
        String regexPhoneNumber = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        ModelAndView modelAndView = new ModelAndView();
        // 错误信息 Map
        Map<String,String> errors = new HashMap<>();
        // 账号验证
        if(account == null || account.equals("")){
            errors.put("accountMsg","输入不能为空");
        }else if(!account.matches(regexEmail) && !account.matches(regexPhoneNumber)){
            errors.put("accountMsg","手机/邮箱格式不正确");
        }
        // 密码验证
        if(password == null || password.equals("")){
            errors.put("passwordMsg","输入不能为空");
        }
        // 验证码验证
        String checkCode = String.valueOf(session.getAttribute("verifyCode"));
        if(verifyCode == null || verifyCode.equals("")){
            errors.put("verifyCodeMsg","输入不能为空");
        }else if(!verifyCode.equalsIgnoreCase(checkCode)){
            errors.put("verifyCodeMsg","验证码输入有误");
        }

        // 登录验证
        if(account.matches(regexEmail)){
            if(!userService.isExistingEmail(account)){
                errors.put("accountMsg","邮箱不存在");
            }else{
                user = userService.getUserByEmailAndPassword(account,password);
                if(user == null){
                    errors.put("passwordMsg","密码输入错误");
                }
            }
        }else if(account.matches(regexPhoneNumber)){
            if(!userService.isExistingPhoneNumber(account)){
                errors.put("accountMsg","手机号不存在");
            } else {
                user = userService.getUserByPhoneNumberAndPassword(account,password);
                if(user == null){
                    errors.put("passwordMsg","密码输入错误");
                }
            }
        }

        // 验证 跳转
        if(errors.isEmpty()){
            modelAndView.addObject("user",user);
            modelAndView.setViewName("forward:/index.jsp");
        }else{
            modelAndView.addObject("errors",errors);
            modelAndView.addObject("account",account);
            modelAndView.addObject("password",password);
            modelAndView.setViewName("user/login");
        }
        return modelAndView;
    }

    // 注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(String username,String password,String confirmPassword,String email,String phoneNumber,String verifyCode,HttpSession session){
        String emailRegex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        String phoneNumberRegex = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        String passwordRegex = "^.*(?=.{6,18})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$";
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> errors = new HashMap();
        // 用户名验证
        if(username == null || username.equals("")){
            errors.put("usernameMsg","输入不能为空");
        }else if(userService.isExistingUsername(username)){
            errors.put("usernameMsg","用户已存在");
        }
        // 密码验证
        if(password == null || password.equals("")){
            errors.put("passwordMsg","输入不能为空");
        }else if(password.matches(passwordRegex)){
            errors.put("passwordMsg","请输入6-18位，字母、数字、字符");
        }
        // 确认密码
        if(confirmPassword == null && confirmPassword.equals("")){
            errors.put("confirmPasswordMsg","输入不能为空");
        }else if(!password.equals(confirmPassword)){
            errors.put("confirmPasswordMsg","两次密码输入不一致");
        }
        // 邮箱验证
        if(email == null || email.equals("")){
            errors.put("emailMsg","输入不能为空");
        }else if(!email.matches(emailRegex)){
            errors.put("emailMsg","邮箱格式不正确");
        }else if(userService.isExistingEmail(email)){
            errors.put("emailMsg","邮箱已存在");
        }
        // 手机验证
        if(phoneNumber == null || phoneNumber.equals("")){
            errors.put("phoneNumberMsg","输入不能为空");
        }else if(!phoneNumber.matches(phoneNumberRegex)){
            errors.put("phoneNumberMsg","手机格式不正确");
        }else if(userService.isExistingPhoneNumber(phoneNumber)){
            errors.put("phoneNumberMsg","手机已存在");
        }

        // 验证码验证
        String checkCode = String.valueOf(session.getAttribute("verifyCode"));
        if(verifyCode == null || verifyCode.equals("")){
            errors.put("verifyCodeMsg","输入不能为空");
        }else if(!verifyCode.equalsIgnoreCase(checkCode)){
            errors.put("verifyCodeMsg","验证码输入有误");
        }

        // 验证成功
        if (errors.isEmpty()){
            String userId = String.valueOf(UUID.randomUUID());
            String avatar = "default.png";
            String joinTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            User user = new User(userId,username,password,email,phoneNumber,avatar,null,joinTime,null,null);
            userService.register(user);
            modelAndView.setViewName("user/login");
        }else{
            modelAndView.addObject("errors",errors);
            modelAndView.addObject("username",username);
            modelAndView.addObject("password",password);
            modelAndView.addObject("confirmPassword",confirmPassword);
            modelAndView.addObject("email",email);
            modelAndView.addObject("phoneNumber",phoneNumber);
            modelAndView.setViewName("user/register");
        }
        return modelAndView;
    }

    // 修改用户信息
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public ModelAndView modifyUser(String username,String gender,String bio,HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> errors = new HashMap();
        // 用户名验证
        if(username == null || username.equals("")){
            errors.put("usernameMsg","输入不能为空");
        }else if(userService.isExistingUsername(username)){
            errors.put("usernameMsg","用户已存在");
        }
        // 验证提交
        if(errors.isEmpty()){
            user.setUsername(username);
            user.setGender(gender);
            user.setBio(bio);
            userService.modifyUser(user);
            modelAndView.addObject("user",user);
            modelAndView.setViewName("forward:/index.jsp");
        }else{
            modelAndView.addObject("errors",errors);
            modelAndView.addObject("username",username);
            modelAndView.addObject("gender",gender);
            modelAndView.addObject("bio",bio);
            // 重回编辑资料页面
            modelAndView.setViewName("");
        }
        return modelAndView;
    }

    // 注销
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "forward:/index.jsp";
    }

    // 上传头像
//    @RequestMapping(value = "uploadAvatar")
//    public void uploadAvatar(HttpServletRequest request, HttpSession session) throws IOException {
//        User user = (User) session.getAttribute("user");
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload sfu = new ServletFileUpload(factory);
//        List items = null;
//        try {
//            items = sfu.parseRequest(request);//从request得到所有上传域的列表
//        } catch (Exception e) {
//        }
//        for (Iterator iter = items.iterator(); iter.hasNext(); ) {
//            FileItem fileitem = (FileItem) iter.next();
//            if (!fileitem.isFormField() && fileitem != null) {//判读不是普通表单域即是file
//                String filename = user.getUserId() + fileitem.getName().substring(fileitem.getName().lastIndexOf("."));
//                System.out.println(filename);
//                FileUtils.copyInputStreamToFile(fileitem.getInputStream(), new File(getDevAvatarPath(request) + filename));
//                FileUtils.copyInputStreamToFile(fileitem.getInputStream(), new File(getWarAvatarPath(request) + filename));
//                user.setAvatar(filename);
//                userService.modifyUser(user);
//            }
//        }
//    }

    /*
     * 工具方法：根据userId获取postContent的txt文件路径，用于下载文件
     */
    private static String getDevAvatarPath(HttpServletRequest request){
        // 开发路径
        String outPath = request.getServletContext().getRealPath("/");
        String realPath = outPath.substring(0,outPath.lastIndexOf("out"));
        String avatarPath = realPath+"web/res/images/avatar/";
        return avatarPath;
    }
    private static String getWarAvatarPath(HttpServletRequest request){
        // 部署路径
        String avatarPath = request.getServletContext().getRealPath("/")+"/res/images/avatar/";
        return avatarPath;
    }
}

package com.group3.bookstore.controller;

import com.group3.bookstore.util.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class VerifyCodeController {
    // 验证码
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response){
        // 1. 创建验证码类
        VerifyCode vcode = new VerifyCode();
        // 2. 得到验证码图片
        BufferedImage image = vcode.getImage();
        // 3. 把图片上的文本保存到session中
        System.out.println(vcode.getText());
        request.getSession().setAttribute("verifyCode", vcode.getText());
        // 4. 把图片响应给客户端
        try {
            VerifyCode.output(image, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

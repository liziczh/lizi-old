package com.panda.bbs.web.controller;

import com.panda.bbs.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 创建验证码类
		 */
		VerifyCode vcode = new VerifyCode();
		/*
		 * 2. 得到验证码图片
		 */
		BufferedImage image = vcode.getImage();
		/*
		 * 3. 把图片上的文本保存到session中
		 */
		System.out.println(vcode.getText());
		request.getSession().setAttribute("verifyCode", vcode.getText());
		/*
		 * 4. 把图片响应给客户端
		 */
		VerifyCode.output(image, response.getOutputStream());
	}
}

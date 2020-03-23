package com.liziczh.erp.service.impl;

import com.liziczh.erp.dto.enumeration.EmailType;
import com.liziczh.erp.dto.request.EmailDTO;
import com.liziczh.erp.entity.EmailLog;
import com.liziczh.erp.repository.EmailLogRepository;
import com.liziczh.erp.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Override
    @Async
    public Future<Boolean> sendRegister(EmailDTO emailDTO) {
        EmailType emailType = EmailType.REGISTER;
        String emailSubject = "您好, 已成功注册账号! ";
        // 封装页面动态属性
        Map<String, String> map = new HashMap<>(2);
        map.put("username", emailDTO.getEmail());
        String emailText = getHtmlText(map, "register.html");
        boolean success = sendEmail(emailDTO, emailType, emailSubject, emailText);
        return new AsyncResult<>(success);
    }

    @Override
    @Async
    public Future<Boolean> sendResetPassword(EmailDTO emailDTO) {
        EmailType emailType = EmailType.RESETPASSWORD;
        String emailSubject = "您好, 已成功修改账号信息! ";
        // 封装页面动态属性
        Map<String, String> map = new HashMap<>(2);
        map.put("username", emailDTO.getEmail());
        map.put("password", emailDTO.getPassword());
        String emailText = getHtmlText(map, "password.html");
        boolean success = sendEmail(emailDTO, emailType, emailSubject, emailText);
        return new AsyncResult<>(success);
    }

    /**
     * 工具方法：发送邮件
     *
     * @param emailDTO 邮件包装类
     * @param emailType    邮件类型
     * @param emailSubject 邮件主题
     * @param emailText    邮件内容
     * @return boolean
     */
    private boolean sendEmail(EmailDTO emailDTO, EmailType emailType, String emailSubject, String emailText) {
        boolean success = true;
        String emailFrom = this.mailMessage.getFrom();
        String emailTo = emailDTO.getEmail();
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        Date sentDate = new Date();
        String log = null;
        try {
            assert emailFrom != null;
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(emailTo);
            messageHelper.setSubject(emailSubject);
            messageHelper.setText(emailText, true);
            this.mailSender.send(mimeMessage);
            sentDate = mimeMessage.getSentDate();
        } catch (MessagingException | MailException e) {
            success = false;
            log = e.getMessage();
        } finally {
            // 添加邮件日志
            EmailLog emailLog = new EmailLog();
            emailLog.setUserId(emailDTO.getUserId());
            emailLog.setType(emailType);
            emailLog.setMessage(emailType.getMessage());
            emailLog.setEmailSubject(emailSubject);
            emailLog.setEmailText(emailText);
            emailLog.setEmailSentDate(sentDate);
            emailLog.setEmailFrom(this.mailMessage.getFrom());
            emailLog.setEmailTo(emailDTO.getEmail());
            emailLog.setStatus(success);
            emailLog.setCreateTime(new Date());
            emailLog.setLog(log);
            emailLogRepository.save(emailLog);
        }
        return success;
    }

    /**
     * 工具方法：使用FreeMaker模板生成一封HTML邮件
     *
     * @param map          页面动态属性
     * @param templateName 模板名称
     * @return String
     */
    private String getHtmlText(Map<String, String> map, String templateName) {
        String htmlText = "";
        Template template = null;
        try {
            template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return htmlText;
    }


}

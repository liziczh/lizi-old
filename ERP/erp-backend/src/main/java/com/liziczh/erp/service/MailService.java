package com.liziczh.erp.service;



import com.liziczh.erp.dto.request.EmailDTO;

import java.util.concurrent.Future;

/**
 * @Description 邮件服务
 * @Author czh
 * @Date 2018/10/8
 */
public interface MailService {

    /**
     * 发送用户注册提示邮件
     *
     * @param emailDTO 邮件包装类
     * @return Future<Boolean>
     */
    Future<Boolean> sendRegister(EmailDTO emailDTO);

    /**
     * 发送用户重置密码邮件
     *
     * @param emailDTO 邮件包装类
     * @return Future<Boolean>
     */
    Future<Boolean> sendResetPassword(EmailDTO emailDTO);

}

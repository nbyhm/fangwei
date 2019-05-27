package com.dowell.web;

import com.dowell.WebApplicationTests;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * @author: NanBo
 * @description: 发送QQ邮件
 * @date: 2019-05-27
 */
@Slf4j
public class SendQQMailTests extends WebApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    /**
     * 简单的邮件发送
     */
    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        //501 mail from address must be same as authorization user
        //邮件必须与授权邮件相同(与配置mail.host.username)
        message.setFrom("8******8@qq.com");
        message.setTo("1163213732@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("20190523-测试邮件内容");

        javaMailSender.send(message);
    }


    @Test
    public void sendMail2() throws Exception{
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", "smtp");
        //服务器
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        //端口
        prop.setProperty("mail.smtp.port", "465");
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //
        //获取Session对象
        Session s = Session.getDefaultInstance(prop,new Authenticator() {
            //此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 这里必须为 配置文件中 mail.host.username一致
                PasswordAuthentication pa = new PasswordAuthentication("8******8@qq.com", "Nb1*****2");
                return pa;
            }
        });
        //设置session的调试模式，发布时取消
        s.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(s);
        try {
            mimeMessage.setFrom(new InternetAddress("8******8@qq.com","8******8@qq.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("8******8@qq.com"));
            //设置主题
            mimeMessage.setSubject("NOAH主题");
            mimeMessage.setSentDate(new Date());
            //设置内容
            mimeMessage.setText("测试-正文内容");
            mimeMessage.saveChanges();

            //发送
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送模板邮件
     */
    @Test
    public void sendHtmlEmail(){

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            Context context = new Context();
            //设置参数
            context.setVariable("email", "8******8@qq.com");
            context.setVariable("username", "test");
            context.setVariable("password", "123456");
            String tempContext = templateEngine.process("mail/sendMailTemplate", context);

            //设置内容
            //mimeMessage.setText("DW-正文内容");
            mimeMessage.setText(tempContext);
            mimeMessage.saveChanges();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件必须与授权邮件相同(与配置mail.host.username)
            helper.setFrom("8******8@qq.com");
            helper.setTo("8******8@qq.com");
            helper.setSubject("测试主题");
            helper.setText(tempContext, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.info("发送邮件失败：{}",e.getMessage());
        }

    }
}

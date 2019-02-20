package com.example.yummy.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil implements Runnable {
    private static final String senderEmail = "lei_cheng2008@126.com";
    private static final String senderPassword = "LC2008@live.cn";

    private String receiverEmail;
    private String memberId;
    private String code;

    public MailUtil(String receiverEmail, String memberId, String code) {
        this.receiverEmail = receiverEmail;
        this.memberId = memberId;
        this.code = code;
    }

    @Override
    public void run() {
        String host = "smtp.126.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        try {
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(senderEmail));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String url = "http://localhost:8080/register/activateMember/" + memberId +
                    "?code=" + code;
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>" +
                    "<h3><a href='" + url + "'>"
                    + url +
                    "</href></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");

            // 3.发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

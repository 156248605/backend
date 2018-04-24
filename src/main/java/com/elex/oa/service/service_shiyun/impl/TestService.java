package com.elex.oa.service.impl;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Author:ShiYun;
 * @Description;测试邮箱服务
 * @Date:Create in 2018/2/26 17:19
 * @Modify By:
 */
public class TestService {
    /**
     *@Author:ShiYun;
     *@Description;测试邮件接收
     *@Date:2018/2/27 11:07
     */
    public String tetPop3Server(String pop3,String username,String password,String MAIL_FROM){
        String backMessage = "";
        // 创建一个有具体连接信息的Properties对象
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");//bug
        props.setProperty("mail.store.protocol", "pop3");//接收器
        props.setProperty("mail.pop3.host", pop3);//邮箱接收服务器
        String[] strs = pop3.split("\\.");
        if(strs[1].equals("qq")){
            props.setProperty("mail.pop3.ssl.enable","true");
            props.setProperty("mail.pop3.socketFactory.class","javax.NET.ssl.SSLSocketFactory");
            props.setProperty("mail.pop3.port","995");
            props.setProperty("mail.pop3.socketFactory.port","995");
        }

        // 1、创建session
        Session session = Session.getInstance(props);
        Store store = null;
        try {
            // 2、通过session得到Store对象
            store = session.getStore();

            // 3、连上邮件服务器
            store.connect(pop3, username, password);
            backMessage = backMessage + "连接邮箱接收服务器成功！登录邮箱接收服务器成功！";
        } catch (MessagingException e) {
            e.printStackTrace();
            backMessage = backMessage + "连接邮箱接收服务器失败！登录邮箱接收服务器失败！";
        }

        try {
            // 4、获得邮箱内的邮件夹
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            // 获得邮件夹Folder内的所有邮件Message对象
            Message[] messages = folder.getMessages();

            // 5、关闭
            folder.close(false);
            store.close();
            backMessage = backMessage +"检查邮箱信息成功！";
        } catch (MessagingException e) {
            e.printStackTrace();
            backMessage = backMessage +"检查邮箱信息失败！";
        }
        return backMessage;
    }

    /**
     *@Author:ShiYun;
     *@Description;测试邮件发送
     *@Date:2018/2/27 11:08
     */
    public String tetSmtpServer(String smtp,String username,String password,String MAIL_FROM){
        String backMessage = "";
        // 创建一个有具体连接信息的Properties对象
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");// bug
        props.setProperty("mail.host", smtp);// 邮箱发送服务器
        props.setProperty("mail.transport.protocol", "smtp");// 发送器
        props.setProperty("mail.smtp.auth", "true");// 是否认证
        String[] strs = smtp.split("\\.");
        if(strs[1].equals("qq")){
            props.setProperty("mail.smtp.ssl.enable","true");
            props.setProperty("mail.smtp.socketFactory.class","javax.NET.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.port","465");
            props.setProperty("mail.smtp.socketFactory.port","465");
        }

        // 实现Authenticator认证接口
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication () {
                return new PasswordAuthentication(username, password);//用户名和密码
            }
        };

        // 使用Properties对象获得Session对象
        Session session = Session.getInstance(props,auth);
        session.setDebug(true);
        Transport transport = null;

        // 利用Session对象获得Transport对象
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        // 连上邮件服务器
        try {
            transport.connect(smtp, username, password);
            backMessage = backMessage + "连接邮箱发送服务器成功！登录邮箱发送服务器成功！";
        } catch (MessagingException e) {
            e.printStackTrace();
            backMessage = backMessage + "连接邮箱发送服务器失败！登录邮箱发送服务器失败！";
        }

        //创建邮件
        MimeMessage message = new MimeMessage(session);
        try {
            // 邮件消息头
            message.setFrom(new InternetAddress(MAIL_FROM)); // 邮件的发件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_FROM)); // 邮件的收件人
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_FROM)); // 邮件的抄送人
            message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_FROM)); // 邮件的密送人
            message.setSubject("测试邮件发送"); // 邮件的标题
            String htmlContent = "<h1>Hello</h1>" + "<p>显示图片<img src='cid:abc.jpg'>1.jpg</p>";
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(htmlContent, "text/html;charset=UTF-8");
            // 描述数据关系
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text);
            message.setContent(mm);
            message.saveChanges();

            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            backMessage = backMessage + "测试邮件发送成功！";
        } catch (MessagingException e) {
            backMessage = backMessage + "测试邮件发送失败！";
            e.printStackTrace();
            backMessage = backMessage + "测试邮件发送失败！";
        }
        return backMessage;
    }

}

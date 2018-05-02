package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.common.Commons;
import com.elex.oa.entity.entity_shiyun.Email;
import com.elex.oa.entity.entity_shiyun.MailAccount;
import com.elex.oa.util.util_shiyun.HandleMailUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description;发送和接收邮件
 * @Date:Create in 2018/3/1 9:32
 * @Modify By:
 */
public class SendAndReceiveMail {
    /**
     *@Author:ShiYun;
     *@Description;发送邮件
     *@Date:2018/3/2 16:07
     */
    public Boolean sendMail(MailAccount mailAccount, String to, String cc, String bcc, String subject, String bt,
                            File file, HttpServletRequest request){

        Boolean b = true;
        try {
            // 创建一个有具体连接信息的Properties对象
            Properties props = new Properties();
            props.setProperty("mail.debug", "true");// bug
            props.setProperty("mail.host", mailAccount.getSmpt());// 邮箱发送服务器
            props.setProperty("mail.transport.protocol", "smtp");// 发送器
            props.setProperty("mail.smtp.auth", "true");// 是否认证
            String[] strs = mailAccount.getSmpt().split("\\.");
            if(strs[1].equals("qq")){
                props.setProperty("mail.smtp.ssl.enable","true");
                props.setProperty("mail.smtp.socketFactory.class","javax.NET.ssl.SSLSocketFactory");
                props.setProperty("mail.smtp.port","465");
                props.setProperty("mail.smtp.socketFactory.port","465");
            }

            // 实现Authenticator认证接口
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication () {
                    return new PasswordAuthentication(mailAccount.getUsername(), mailAccount.getPassword());//用户名和密码
                }
            };

            // 使用Properties对象获得Session对象
            Session session = Session.getInstance(props,auth);
            session.setDebug(true);
            Transport transport = null;

            // 利用Session对象获得Transport对象
            transport = session.getTransport();

            // 连上邮件服务器
            transport.connect(mailAccount.getSmpt(), mailAccount.getUsername(), mailAccount.getPassword());

            //创建邮件
            MimeMessage message = new MimeMessage(session);
            // 邮件消息头
            message.setFrom(new InternetAddress(mailAccount.getEmailAddress())); // 邮件的发件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 邮件的收件人
            if (cc!=""){
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc)); // 邮件的抄送人
            }
            if(bcc!=""){
                message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc)); // 邮件的密送人
            }
            message.setSubject(subject); // 邮件的标题

            if(file != null){
                MimeBodyPart text = new MimeBodyPart();
                text.setContent(bt, "text/html;charset=UTF-8");
                // 描述数据关系
                MimeMultipart mm = new MimeMultipart();
                mm.addBodyPart(text);
                mm.setSubType("related");
                // 添加邮件附件
                MimeBodyPart attachPart = new MimeBodyPart();

                attachPart.attachFile(file);
                mm.addBodyPart(attachPart);

                message.setContent(mm);
                message.saveChanges();
            }else {
                message.setText(bt,"UTF-8");
            }

            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            request.getServletContext().setAttribute("uid",message.getMessageID());
            transport.close();

        } catch (MessagingException e) {
            b = false;
            e.printStackTrace();
        } catch (IOException e) {
            b = false;
            e.printStackTrace();
        }

        return b;
    }

    /**
     *@Author:ShiYun;
     *@Description;接收邮件
     *@Date:2018/3/2 16:08
     */
    public List<Email> receEmails(MailAccount mailAccount, List<Long> dts){
        List<Email> list = new ArrayList<Email>();

        // 创建一个有具体连接信息的Properties对象
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");//bug
        props.setProperty("mail.store.protocol", "pop3");//接收器
        props.setProperty("mail.pop3.host", mailAccount.getPop3());//邮箱接收服务器
        String[] strs = mailAccount.getPop3().split("\\.");
        if(strs[1].equals("qq")){
            props.setProperty("mail.pop3.ssl.enable","true");
            props.setProperty("mail.pop3.socketFactory.class","javax.NET.ssl.SSLSocketFactory");
            props.setProperty("mail.pop3.port","995");
            props.setProperty("mail.pop3.socketFactory.port","995");
        }

        // 1、创建session
        Session session = Session.getInstance(props);
        Store store = null;

        // 2、通过session得到Store对象
        try {
            store = session.getStore();
        } catch (NoSuchProviderException e) {
            System.out.println("通过session得到Store对象失败！");
            e.printStackTrace();
        }

        // 3、连上邮件服务器
        try {
            store.connect(mailAccount.getPop3(), mailAccount.getUsername(), mailAccount.getPassword());
        } catch (MessagingException e) {
            System.out.println("连接邮箱接收服务器失败！登录邮箱接收服务器失败！");
            e.printStackTrace();
        }

        // 4、获得邮箱内的邮件夹
        Folder folder = null;
        try {
            folder = store.getFolder("inbox");
        } catch (MessagingException e) {
            System.out.println("获得邮箱内的邮件夹失败！");
            e.printStackTrace();
        }

        try {
            folder.open(Folder.READ_ONLY);
        } catch (MessagingException e) {
            System.out.println("打开邮箱内的邮件夹失败！");
            e.printStackTrace();
        }

        // 获得邮件夹Folder内的所有邮件Message对象
            Message[] messages = new Message[0];
            try {
                messages = folder.getMessages();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            Boolean b = false;
            for (Message message:messages
                 ) {
                for (Long dt:dts
                     ) {
                    try {
                        Date sentDate = message.getSentDate();
                        long time = sentDate.getTime();
                        if(time==dt){
                            b = true;
                            break;
                        }
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                if(b){
                    b = false;
                    continue;
                }
                Email email1 = new Email();
                email1.setCfgid(mailAccount.getId());
                try {
                    email1.setFromm(message.getFrom()[0].toString());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                email1.setToo(mailAccount.getEmailAddress());
                try {
                    email1.setSubject(message.getSubject());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                HandleMailUtil handleMailUtil = new HandleMailUtil();
                try {
                    if (message.getContent().getClass().getName().equals("java.lang.String")){
                        email1.setBt(message.getContent().toString());
                    }else{
                        Map<String, String> map = handleMailUtil.handleMultipart(message,mailAccount);
                        email1.setAttachment(map.get("attachment"));
                        email1.setBt(map.get("bt"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Date date = message.getSentDate();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String dt = simpleDateFormat.format(date);
                    email1.setDate(dt);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                email1.setType(Commons.所收邮件_未读);
                email1.setUid(String.valueOf(message.getMessageNumber()));
                list.add(email1);
            }

        // 5、关闭
        try {
            folder.close(false);
        } catch (MessagingException e) {
            System.out.println("关闭邮件夹失败！");
            e.printStackTrace();
        }

        try {
            store.close();
        } catch (MessagingException e) {
            System.out.println("关闭邮件仓库失败！");
            e.printStackTrace();
        }

        return list;
    }
}

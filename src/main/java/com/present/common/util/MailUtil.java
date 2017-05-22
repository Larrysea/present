package com.present.common.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Larry-sea on 2017/5/20.
 * <p>
 * 邮件使用工具类，暂时这个类不支持任何中文形式
 */
public class MailUtil {

    public static void sendAttachment(File excelFile) {


        String host = "smtp.163.com";  //邮件服务器
        String from = "lzh1994610@163.com";
        String to = "lzh1994610@163.com";
        String userName = "lzh1994610";
        String pwd = "sea18835172573";  //客户端授权码（这里用客户端授权码登陆，而不是用密码登陆）

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);


        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
//            String filename = "/home/manisha/file.txt";
            String filename = excelFile.getAbsolutePath();

            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, pwd);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

            // Send message
            //  transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void sendMail() {
        String host = "smtp.163.com";  //邮件服务器
        String from = "lzh1994610@163.com";
        String to = "lzh1994610@163.com";
        String userName = "lzh1994610";
        String pwd = "sea18835172573";  //客户端授权码（这里用客户端授权码登陆，而不是用密码登陆）

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  //send

            String subject = new String("subject ".getBytes(), "UTF-8");
            message.setSubject(subject);
            System.setProperty("mail.mime.charset", "UTF-8");
            message.setContent("中文内容", "text/html;charset=utf-8");
            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, pwd);





        /*    MimeMultipart list = new MimeMultipart();
            MimeBodyPart part1 = new MimeBodyPart();
            part1.setContent("你好，收到请回复！", "text/html;charset=utf-8");
            list.addBodyPart(part1);
            MimeBodyPart part2 = new MimeBodyPart();
            //下面的路径应该对应着是你自己文件所在的路径
            part2.attachFile(new File("C:\\Users\\Larry-sea\\OneDrive\\Desktop\\ic_search.png"));
            //MimeUtility.encodeText()防止文字乱码
            part2.setFileName(MimeUtility.encodeText("eee.jpg"));
            // list.addBodyPart(part2);*/

            //  message.setContent(list);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            System.out.println("Send ok ......");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        sendMail();
    }


}

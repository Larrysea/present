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
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Larry-sea on 2017/5/19.
 * <p>
 * <p>
 * very useful
 */
public class SendMail {

    public static void sendMail() {
        String host = "smtp.163.com";  //邮件服务器
        String from = "norember_speeder@163.com";
        String to = null;
        String userName = "norember_speeder";
        String pwd = "androidANDROID2";  //客户端授权码（这里用客户端授权码登陆，而不是用密码登陆）

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  //send


            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//            message.setSubject( "=?GB2312?B"+Base64.encode("中文")+"?=" );
            String subject = new String("subject ".getBytes(), "UTF-8");
            message.setSubject(subject);
            //  System.setProperty("mail.mime.charset", "UTF-8");
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


    private static String correctDisplay(String temp) {
        try {
            temp = new String(temp.getBytes("ISO-8859-1"), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return temp;
    }


    /**
     * 发送邮件附件，并且可以包含邮件内容和邮件主题
     *
     * @param subject         发送的邮件主题
     * @param mailContent     发送的邮件内容
     * @param filePath        需要发送附件的地址
     * @param fromMailAddress 发送邮箱地址
     * @param password        发送邮箱的smtp的授权码
     * @param toMailAddress   接受邮箱地址
     * @param smtpServer      smtp服务器地址
     * @throws FileNotFoundException
     */
    public static void sendAttachment(String subject, String mailContent,
                                      String filePath, String fromMailAddress,
                                      String password, String toMailAddress,
                                      String smtpServer) throws FileNotFoundException {


        String userName = fromMailAddress.substring(0, (fromMailAddress.indexOf("@")));
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromMailAddress));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toMailAddress));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(mailContent);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();


            DataSource source = new FileDataSource(file.getAbsolutePath());
            System.out.println("SendMail class" + file.getAbsolutePath());
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(file.getName());
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServer, userName, password);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

            // Send message
            //  transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

}
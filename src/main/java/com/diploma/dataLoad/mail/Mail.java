package com.diploma.dataLoad.mail;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.Security;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class Mail {
    public static void SendMailRu(String username, String password, String recipientEmail, String title, String filePath, String message) throws MessagingException {
        Mail.Send(username, password, recipientEmail, title, message, filePath, "465", "smtp.mail.ru");
    }

    public static void SendYandex(String username, String password, String recipientEmail, String title, String filePath, String message) throws MessagingException {
        Mail.Send(username, password, recipientEmail, title, message, filePath, "465", "smtp.yandex.ru");
    }

    public static void SendGMail(String username, String password, String recipientEmail, String title, String filePath, String message) throws MessagingException {
        Mail.Send(username, password, recipientEmail, title, message, filePath, "465", "smtp.gmail.com");
    }

    public static void Send(String username, String password, String recipientEmail, String title, String message, String filePath, String port, String smtp) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtps.host", smtp);
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.socketFactory.port", port);
        properties.setProperty("mail.smtps.auth", "true");

        properties.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(properties, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
        msg.setSubject(title);
        BodyPart messageBodyPart2 = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        messageBodyPart2.setText(message);
        multipart.addBodyPart(messageBodyPart2);

        if (!Objects.equals(filePath, "")) {
            DataSource source = new FileDataSource(filePath);
            BodyPart fileBodyPart = new MimeBodyPart();
            fileBodyPart.setDataHandler(new DataHandler(source));
            fileBodyPart.setFileName(filePath);
            multipart.addBodyPart(fileBodyPart);
        }

        msg.setContent(multipart);
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        try {
            t.connect(smtp, username, password);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        }catch (AuthenticationFailedException a){
            a.printStackTrace();
        }

    }
}


package com.example.project.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.URI;
import java.util.Properties;

public class EmailService {

    private String username;
    private String password;

    private final Properties prop;

    public EmailService(String host, int port, String username, String password) {
        prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        this.username = username;
        this.password = password;
    }

    public EmailService(String host, int port) {
        prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);

    }

    public static void run(String email) {
        try {
            new EmailService("smtp.gmail.com", 587, "les492885@gmail.com", "ytaavdifkgkydzzs")
                    .sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String email) throws Exception {

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("timiteubrotherhood@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";
        MimeBodyPart mimeBodyPartWithStyledText = new MimeBodyPart();
        mimeBodyPartWithStyledText.setContent(msgStyled, "text/html; charset=utf-8");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();

        attachmentBodyPart.attachFile(getFile());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        multipart.addBodyPart(mimeBodyPartWithStyledText);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private File getFile() throws Exception {
        URI uri = this.getClass()
                .getClassLoader()
                .getResource("attachment.txt")
                .toURI();
        return new File(uri);
    }

}
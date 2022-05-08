package com.nahorniak.util.mail;

import com.nahorniak.DAO.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Mailer - send mail messages to user
 *
 * @author Oleh Nahorniak.
 */
public class Mailer {

    /**
     * method to send email
     *
     * @param user
     * @param subject
     * @param messageText
     * @return true if email successfully send
     */
    public boolean sendEmail(User user, String subject, String messageText) {
        boolean flag = false;

        String toEmail = user.getEmail();
        String fromEmail = "xxxx@gmail.com";
        String password = "xxxxxxx";

        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.socketFactory.port", "587");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}

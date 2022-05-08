package com.nahorniak.util.mail;

import com.nahorniak.DAO.entity.User;


/**
 * MailSender - thread to send mail messages to user
 *
 * @author Oleh Nahorniak.
 */
public class MailSender extends Thread {

    private User user;
    private String message;
    private String subject;

    public MailSender(User user, String message, String subject) {
        this.user = user;
        this.message = message;
        this.subject = subject;
    }

    @Override
    public void run() {
        Mailer sendEmail = new Mailer();
        sendEmail.sendEmail(user, subject, message);
    }
}

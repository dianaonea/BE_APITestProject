package com.myproject.accounts.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtils {

    public static void sendEmail(String emailTo, String subject, String emailBody) throws MessagingException {
        String emailFrom = "diana.cazacu288@gmail.com";
        String password = "tjne dopk okfq xkfc"; // generated App password

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, password);
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailFrom));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(emailTo)
        );
        message.setSubject(subject);
        message.setText(emailBody);

        Transport.send(message);
        System.out.println("Email to " + emailTo + " has been sent.");
    }

    public static void sendActivationEmail(String emailTo) throws MessagingException{
        String emailBody = "Please use the below url to activate your account and add a password: <insert url here and add a FE page for it>";
        sendEmail(emailTo, "Account activation", emailBody);
    }

    public static void sendDeletionEmail(String emailTo) throws MessagingException{
        String emailBody = "Bye bye";
        sendEmail(emailTo, "Your account is not deleted", emailBody);
    }
}

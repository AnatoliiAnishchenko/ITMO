package server;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSendler {
    private static final String FROM = "ttaaa.ForApplication@gmail.com";
    private static final String PASSWORD = "ForApplication1093";
    private static Session session;

    public EmailSendler() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });
    }

    public static void sendMail(String TO, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(TO)
            );
            message.setSubject(subject);
            message.setText(text + "\n\nWith best regards,\nMoomin's application development team");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(String TO, String text) {
        sendMail(TO, "Moomin's application", text);
    }
}

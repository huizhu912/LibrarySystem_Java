package sendmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail {

    String host, port, emailid,username, password;
    Properties props = System.getProperties();
    Session session = null;

    public SendMail(String toEmail, String content) {
        host = "smtp.mail.yahoo.com";
        port = "587";
        emailid = "coen275@yahoo.com";
        username = "coen275";
        password = "Summer2013";

        emailSettings();
        createSession();
        sendMessage(emailid, toEmail,"Notice of Available Item You Have Reserved", content);
    }

    public void emailSettings() {
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", port);

    }

    public void createSession() {

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true); 

    }

    public boolean sendMessage(String emailFromUser, String toEmail, String subject, String msg) {
        
        try {
           
            MimeMessage message = new MimeMessage(session);
            emailid = emailFromUser;
            
            message.setFrom(new InternetAddress(this.emailid));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Message Sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
package data;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.Properties;

public class Mail {

    public static void sendMail() {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");

        Session mailSession = Session.getInstance(props, null);
        try {
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom("admin@example.com");
            msg.setRecipients(Message.RecipientType.TO, "editor@findbestopensource.com");
            msg.setSubject("Forget login request");
            msg.setText("We got request that you want to reset your password. Below is the link ... ");
            msg.setSentDate(new Date());

            Transport.send(msg);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void receiveMail() {

        try {
            GreenMail greenMail = new GreenMail(ServerSetup.SMTP_IMAP);
            ServerSetup server = greenMail.getSmtp().getServerSetup();
            System.out.println(server.getPort());
            IMAPStore imapStore = greenMail.getImap().createStore();
            imapStore.connect("editor@findbestopensource.com", "editor@findbestopensource.com");
            Folder inbox = imapStore.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            int totalMsgCount = inbox.getMessageCount();
            for(int i=1; i <= totalMsgCount; i++) {
                Message msgReceived = inbox.getMessage(i);
                System.out.println(msgReceived.getSubject());
                System.out.println(totalMsgCount);
                System.out.println(msgReceived.getSentDate());

            }
            inbox.close();

        }
        catch(Exception exp) {
            exp.printStackTrace();
        }
    }
}

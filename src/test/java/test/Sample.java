package test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import data.Mail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static data.Mail.receiveMail;
import static data.Mail.sendMail;

public class Sample {
    static GreenMail greenMail = new GreenMail(ServerSetup.ALL);

    @BeforeAll
    static void init() {
        greenMail.start();
    }

    @AfterAll
    static void close() {
        greenMail.stop();
    }

    @Test
    void get() {
      sendMail();
      receiveMail();
    }


}

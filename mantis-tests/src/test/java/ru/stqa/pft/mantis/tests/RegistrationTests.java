package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

//    @BeforeMethod
//    public void startMailServer() {
//        app.mail().start();
//    }

    @BeforeMethod
    public void checkIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        int issueId = 2;
        skipIfNotFixed(issueId);
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        UserData user = new UserData()
                .withUsername(String.format("user%s", now)).withPassword("password").withEmail(String.format("user%s@localhost.localdomain", now));
        app.user().registerNewUser(user);
        assertTrue(app.newSession().login(user.getUsername(), user.getPassword()));
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopMailServer() {
//        app.mail().stop();
//    }
}

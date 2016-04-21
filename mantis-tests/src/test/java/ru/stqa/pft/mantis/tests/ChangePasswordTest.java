package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void checkIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        int issueId = 2;
        skipIfNotFixed(issueId);
    }

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        if (app.db().users().size() <= 1) {
            long now = System.currentTimeMillis();
            app.user().registerNewUser(new UserData().withUsername(String.format("user%s", now))
                    .withPassword("password").withEmail(String.format("user%s@localhost.localdomain", now)));
        }
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.mail().start();
        Users allUsers = app.db().users();
        String newPassword = "1234";
        for (UserData user : allUsers)
            if (user.getId() > 1) {
                app.user().uiLogin("administrator", "root");
                app.user().resetPassword(user, newPassword);
                assertTrue(app.newSession().login(user.getUsername(), newPassword));
                break;
            }
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}



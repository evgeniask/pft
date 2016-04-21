package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void checkIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        int issueId = 1;
        skipIfNotFixed(issueId);
    }

    @Test
    public void testLogon() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}

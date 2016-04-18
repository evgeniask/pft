package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void registerNewUser(UserData user) throws IOException, MessagingException {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        app.registration().start(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
    }

    public void initManageUserByName(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement((By.linkText(String.format("&s", username)))).click();
    }
}
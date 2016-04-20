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
        app.james().createUser(username, password);
        app.registration().start(username, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
    }

    public void uiLogin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void initManageUserByName(UserData user) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId()))).click();
    }

    public void resetPassword(UserData user, String newPassword) throws IOException, MessagingException {
        initManageUserByName(user);
        click(By.cssSelector("input[value='Reset Password']"));
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findChangePasswordLink(mailMessages, user.getEmail());
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}
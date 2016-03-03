package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "SPb, Noname street 77-35", "9934578", "9256883444", "ivan.ivanov@ivanov.ivan", "ivan.ivanov@ttt.com"));
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToHomePageLink();
    }

}

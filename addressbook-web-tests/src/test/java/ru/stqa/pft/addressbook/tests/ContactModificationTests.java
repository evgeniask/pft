package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Alex", "Alexandrov", "SPb, Noname street 77-35", "9934578", "1111111111", "ivan.ivanov@ivanov.ivan", "ivan.ivanov@ttt.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageLink();
    }
}

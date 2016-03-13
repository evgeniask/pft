package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "SPb, Noname street 77-35", "9934578", "9256883444", "ivan.ivanov@ivanov.ivan", "ivan.ivanov@ttt.com", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Alex", "Alexandrov", "SPb, Noname street 77-35", "9934578", "1111111111", "ivan.ivanov@ivanov.ivan", "ivan.ivanov@ttt.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageLink();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}

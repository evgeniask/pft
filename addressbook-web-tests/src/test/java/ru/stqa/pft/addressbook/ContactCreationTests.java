package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Ivan", "Ivanov", "SPb, Noname street 77-35", "9934578", "9256883444", "ivan.ivanov@ivanov.ivan", "ivan.ivanov@ttt.com"));
        submitContactForm();
        returnToHomePage();
    }

}

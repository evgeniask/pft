package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan").withLastname("Ivanov").withAddress("SPb, Noname street 77-35").withHomephone("9934578").withMobilephone("9256883444").withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com").withGroup("test1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}

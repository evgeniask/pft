package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withAddress("SPb, Noname street 77-35").withHomephone("9934578").withMobilephone("9256883444").withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Alex").withLastname("Alexandrov").withAddress("SPb, Noname street 77-35").withHomephone("9934578").withMobilephone("1111111111").withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }


}

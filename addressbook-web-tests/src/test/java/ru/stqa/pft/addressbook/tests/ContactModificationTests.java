package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withAddress("SPb, Noname street 77-35")
                    .withHomephone("(993)4578").withMobilephone("925-6883-444").withWorkphone("44 55 77")
                    .withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com")
                    .withEmail3("ivan.ivanov@third.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Alex").withLastname("Alexandrov").withAddress("Msk, Noname street 77-35")
                .withHomephone("(111)2233").withMobilephone("111-222-333").withWorkphone("11 22 33")
                .withEmail("alex.ivanov@ivanov.ivan").withEmail2("alex.ivanov@ttt.com")
                .withEmail3("alex.ivanov@third.com").withGroup("test1");
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUi();
    }
}
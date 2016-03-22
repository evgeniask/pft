package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan").withLastname("Ivanov").withAddress("SPb, Noname street 77-35")
                .withHomephone("(993)4578").withMobilephone("925-6883-444").withWorkphone("44 55 77")
                .withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com")
                .withEmail3("ivan.ivanov@third.com").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Ivan'").withLastname("Ivanov").withAddress("SPb, Noname street 77-35")
                .withHomephone("(993)4578").withMobilephone("925-6883-444").withWorkphone("44 55 77")
                .withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com")
                .withEmail3("ivan.ivanov@third.com").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}

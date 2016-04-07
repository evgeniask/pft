package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

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
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetails = app.contact().infoFromDetails(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contactInfoFromDetails.getAllDetails(), equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    private String mergeDetails(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactInfoTests::cleaned)
                .collect(Collectors.joining(""));
    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withAddress("SPb, Noname street 77-35")
                    .withHomephone("(993)4578").withMobilephone("925-6883-444").withWorkphone("44 55 77")
                    .withEmail("ivan.ivanov@ivanov.ivan").withEmail2("ivan.ivanov@ttt.com")
                    .withEmail3("ivan.ivanov@third.com"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts allContacts = app.db().contacts();
        ContactData contactToAdd = allContacts.iterator().next();
        Groups allGroups = app.db().groups();
        GroupData freeGroup = null;
        Date currentTime = new Date();

        for (ContactData c : allContacts) {
            Groups contactGroups = c.getGroups();

            for (GroupData g : allGroups) {
                if (!contactGroups.contains(g)) {
                    freeGroup = g;
                    contactToAdd = c;
                    break;
                }
            }
            if (freeGroup != null) {
                break;
            }
        }
        if (freeGroup == null) {
            app.goTo().groupPage();
            app.group().create(freeGroup = new GroupData().withName("group " + currentTime));
            System.out.println("id = " + freeGroup.getId());
        }

        app.goTo().homePage();
        app.contact().addContactToGroup(contactToAdd, freeGroup);
        Groups contactGroupsAfter = app.db().getContactById(contactToAdd.getId()).getGroups();
        freeGroup = app.db().getGroupByName(freeGroup.getName());
        System.out.println("Group to add: " + freeGroup);
        System.out.println("Contact groups after adding: " + contactGroupsAfter);

        assertThat(contactGroupsAfter.contains(freeGroup), equalTo(true));
    }
}




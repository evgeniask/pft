package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase {

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
    public void testRemoveContactFromGroup() {
        Contacts allContacts = app.db().contacts();
        ContactData contactToRemove = allContacts.iterator().next();
        GroupData groupToRemove = null;

        for (ContactData c : allContacts) {
            Groups contactGroups = c.getGroups();
            if (contactGroups.size() != 0) {
                contactToRemove = c;
                groupToRemove = contactGroups.iterator().next();
                break;
            }
        }
        if (groupToRemove == null) {
            groupToRemove = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contact().addContactToGroup(contactToRemove, groupToRemove);
        }
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contactToRemove, groupToRemove);
        Groups contactGroupsAfter = app.db().getContactById(contactToRemove.getId()).getGroups();
        System.out.println("Group to remove: " + groupToRemove);
        System.out.println("Contact groups after removing: " + contactGroupsAfter);

        assertThat(contactGroupsAfter.contains(groupToRemove), equalTo(false));
    }
}



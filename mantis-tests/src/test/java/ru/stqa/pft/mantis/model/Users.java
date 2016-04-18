package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();
    }

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

//    public Users withAdded(UserData contact){
//        Contacts contacts = new Contacts(this);
//        contacts.add(contact);
//        return contacts;
//    }
//
//    public Contacts without(ContactData contact){
//        Contacts contacts = new Contacts(this);
//        contacts.remove(contact);
//        return contacts;
//    }
}
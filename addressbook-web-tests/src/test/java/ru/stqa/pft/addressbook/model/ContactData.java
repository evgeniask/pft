package ru.stqa.pft.addressbook.model;

public class ContactData {

    private int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String homephone;
    private final String mobilephone;
    private final String email;
    private final String email2;
    private String group;

    public ContactData(int id, String firstname, String lastname, String address, String homephone, String mobilephone, String email, String email2, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.email = email;
        this.email2 = email2;
        this.group = group;
    }

    public ContactData(String firstname, String lastname, String address, String homephone, String mobilephone, String email, String email2, String group) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.email = email;
        this.email2 = email2;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                '}';
    }

}

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
        this.id = Integer.MAX_VALUE;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return !(lastname != null ? !lastname.equals(that.lastname) : that.lastname != null);

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                '}';
    }

}

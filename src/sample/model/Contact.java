package sample.model;

public class Contact {
    private String contactName;
    private String contactEmail;
    private int contactId;

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public int getContactId() {
        return contactId;
    }

    public Contact(String contactName, String contactEmail, int contactId) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactId = contactId;
    }
}

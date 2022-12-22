package sample.model;

public class Contact {
    private String contactName;
    private String contactEmail;
    private int contactId;

    /**
     *
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     *
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactName
     * @param contactEmail
     * @param contactId
     */
    public Contact(String contactName, String contactEmail, int contactId) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactId = contactId;
    }
}

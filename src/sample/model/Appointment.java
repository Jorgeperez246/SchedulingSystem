package sample.model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId,customerId,userId,contactId;
    private String customerName,userName,contactName;
    private LocalDateTime end;

    /**
     * @return appointmentId
     * */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @return customerId
     * */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @return userId
     * */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @return end
     * */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @return start
     * */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return loc
     */
    public String getLoc() {
        return loc;
    }

    /**
     *
     * @return contactName
     */
    public String getContactName(){
        return contactName;
    }

    /**
     *
     * @return customerName
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     *
     * @return userName
     */
    public String getUserName(){
        return userName;
    }

    private LocalDateTime start;
    private String title;
    private String descr;
    private String type;
    private String loc;

    /**
     *
     * @param appointmentId
     * @param customerId
     * @param userId
     * @param contactId
     * @param start
     * @param end
     * @param title
     * @param descr
     * @param type
     * @param loc
     * @param contactName
     * @param userName
     * @param customerName
     */
    public Appointment(int appointmentId, int customerId, int userId, int contactId, LocalDateTime start, LocalDateTime end, String title, String descr, String type, String loc,String contactName,String userName, String customerName) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.end = end;
        this.start = start;
        this.title = title;
        this.descr = descr;
        this.type = type;
        this.loc = loc;
        this.contactName = contactName;
        this.userName = userName;
        this.customerName = customerName;
    }


}

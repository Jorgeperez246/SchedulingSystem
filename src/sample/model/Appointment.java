package sample.model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId,customerId,userId,contactId;
    private String customerName,userName,contactName;
    private LocalDateTime end;

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }


    public int getUserId() {
        return userId;
    }

    public int getContactId() {
        return contactId;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public String getTitle() {
        return title;
    }

    public String getDescr() {
        return descr;
    }

    public String getType() {
        return type;
    }

    public String getLoc() {
        return loc;
    }

    public String getContactName(){
        return contactName;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getUserName(){
        return userName;
    }

    private LocalDateTime start;
    private String title;
    private String descr;
    private String type;
    private String loc;
    public Appointment(int appointmentId, int customerId, int userId, int contactId, LocalDateTime end, LocalDateTime start, String title, String descr, String type, String loc,String contactName,String userName, String customerName) {
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

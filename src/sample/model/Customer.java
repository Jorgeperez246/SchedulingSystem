package sample.model;

public class Customer {

    private String divisionName;
    private String customerPhone;
    private String customerAddress;
    private int customerId;
    private String customerName;
    private String customerPost;
    private String countryName;



    public Customer(String divisionName, String customerPhone, String customerAddress, int customerId, String customerName, String customerPost, String countryName) {
        this.divisionName = divisionName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPost = customerPost;
        this.countryName = countryName;


    }




    public String getDivisionName() {
        return divisionName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPost() {
        return customerPost;
    }
    public String getCountryName() {
        return countryName;
    }


}

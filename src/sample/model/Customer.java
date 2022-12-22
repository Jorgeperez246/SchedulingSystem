package sample.model;

public class Customer {

    private String divisionName;
    private String customerPhone;
    private String customerAddress;
    private int customerId;
    private String customerName;
    private String customerPost;
    private String countryName;


    /**
     *
     * @param divisionName
     * @param customerPhone
     * @param customerAddress
     * @param customerId
     * @param customerName
     * @param customerPost
     * @param countryName
     */
    public Customer(String divisionName, String customerPhone, String customerAddress, int customerId, String customerName, String customerPost, String countryName) {
        this.divisionName = divisionName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPost = customerPost;
        this.countryName = countryName;


    }


    /**
     *
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     *
     * @return customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @return customerPost
     */
    public String getCustomerPost() {
        return customerPost;
    }

    /**
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }


}

package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDB {
    /**
     * lists customer data along with data on first_level_division
     * @return customersList
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        String sql = "SELECT customers.*, first_level_divisions.Division, countries.Country\n" +
                "FROM customers\n" +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerAdd = rs.getString("Address");
            String customerName = rs.getString("Customer_Name");
            String customerPost = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            String divisionName = rs.getString("Division");
            String countryName = rs.getString("Country");




            Customer customer = new Customer(divisionName, customerPhone, customerAdd, customerId, customerName, customerPost, countryName);
            customersList.add(customer);
        }

        return customersList;
    }
}

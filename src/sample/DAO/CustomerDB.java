package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;
import sample.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerDB {

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();
        String sql = "SELECT customers.*,first_level_divisions.Division from customers INNER JOIN  first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerAdd = rs.getString("Address");
            String customerName = rs.getString("Customer_Name");
            String customerPost = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            String divisionName = rs.getString("Division");


            Customer customer = new Customer(divisionName, customerPhone, customerAdd, customerId, customerName, customerPost);
            customersObservableList.add(customer);
        }

        return customersObservableList;
    }
}

package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DAO.*;
import sample.model.Appointment;
import sample.model.Country;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddCustomerFormController {
    private final ObservableList<Country> countries = FXCollections.observableArrayList();

    public TextField CustomerId;
    public TextField CustomerName;
    public TextField CustomerAddress;
    public TextField PostalCode;
    public TextField PhoneNumber;
    public Button Cancel;
    public Button Save;

    public ComboBox<String> Country;
    public ComboBox<String> State;

    public void cancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/CustomerForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    public void saveButton(ActionEvent event) {
        try {
            Connection connection = JDBC.getConnection();

            if (!CustomerName.getText().isEmpty() || !CustomerAddress.getText().isEmpty() || !PostalCode.getText().isEmpty() || !PhoneNumber.getText().isEmpty() || !Country.getValue().isEmpty() || !State.getValue().isEmpty())
            {

                //create random ID for new customer id
                Integer newCustomerID = (int) (Math.random() * 100);

                int firstLevelDivisionName = 0;
                for (FirstLevelDivisionDB firstLevelDivision : FirstLevelDivisionDB.getAllFirstLevelDivisions()) {
                    if (State.getSelectionModel().getSelectedItem().equals(firstLevelDivision.getDivisionName())) {
                        firstLevelDivisionName = firstLevelDivision.getDivisionId();

                    }
                }

                String insertStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
                JDBC.makePreparedStatement(insertStatement, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, newCustomerID);
                ps.setString(2, CustomerName.getText());
                ps.setString(3, CustomerAddress.getText());
                ps.setString(4, PostalCode.getText());
                ps.setString(5, PhoneNumber.getText());
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(7, "admin");
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "admin");
                ps.setInt(10, firstLevelDivisionName);


                ps.execute();

                CustomerId.clear();
                CustomerName.clear();
                CustomerAddress.clear();
                PostalCode.clear();
                PhoneNumber.clear();

                Parent customerScene = FXMLLoader.load(getClass().getResource("/sample/view/CustomerForm.fxml"));
                Scene scene = new Scene(customerScene);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void initialize() throws SQLException{
        ObservableList<CountryDB> countriesObservableList = CountryDB.getCountries();
        ObservableList<String> allCountryNames = FXCollections.observableArrayList();

        ObservableList<FirstLevelDivisionDB> firstLevelDivisionObservableList = FirstLevelDivisionDB.getAllFirstLevelDivisions();
        ObservableList<String> allFirstLevelDivisionNames = FXCollections.observableArrayList();

        //lambda function to add countries to dropdown
        countriesObservableList.forEach(countries ->allCountryNames.add(countries.getCountryName()) );

        firstLevelDivisionObservableList.forEach(firstLevelDivisionDB -> allFirstLevelDivisionNames.add(firstLevelDivisionDB.getDivisionName()));
        Country.setItems(allCountryNames);
        State.setItems(allFirstLevelDivisionNames);



    }
}

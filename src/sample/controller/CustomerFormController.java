package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.CustomerDB;
import sample.DAO.JDBC;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController {
    public TableView<Customer> CustomerTable;
    public TableColumn<?,?> IdCol;
    public TableColumn<?,?> NameCol;
    public TableColumn<?,?> AddressCol;
    public TableColumn<?,?> CountryCol;
    public TableColumn<?,?> PostalCol;
    public TableColumn<?,?> PhoneNumCol;
    public Button Add;
    public Button Modify;
    public Button Delete;
    public Button Back;

    public void addButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/AddCustomerForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    public void modifyButton(ActionEvent event) {
    }

    public void deleteButton(ActionEvent event) {
    }

    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }


    public void initialize() throws SQLException {
        Connection connection = JDBC.getConnection();


        ObservableList<Customer> allCustomersList = CustomerDB.getAllCustomers();

        IdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        PostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPost"));
        PhoneNumCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        CustomerTable.setItems(allCustomersList);
    }
}

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.CountryDB;
import sample.DAO.CustomerDB;
import sample.DAO.FirstLevelDivisionDB;
import sample.DAO.JDBC;
import sample.model.Country;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController {
    private int index = 0;
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
        try {
            JDBC.makeConnection();
            Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();



            if (selectedCustomer != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/ModifyCustomerForm.fxml"));
                loader.load();

                ModifyCustomerFormController Controller = loader.getController();
                Controller.sendCustomerToModify(CustomerTable.getSelectionModel().getSelectedIndex(),CustomerTable.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFormController {
    public TableView CustomerTable;
    public TableColumn IdCol;
    public TableColumn NameCol;
    public TableColumn AddressCol;
    public TableColumn CountryCol;
    public TableColumn PostalCol;
    public TableColumn PhoneNumCol;
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
}

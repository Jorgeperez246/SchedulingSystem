package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerFormController {
    public TextField CustomerId;
    public TextField CustomerName;
    public TextField CustomerAddress;
    public TextField PostalCode;
    public TextField PhoneNumber;
    public Button Cancel;
    public Button Save;

    public ComboBox Country;
    public ComboBox State;

    public void cancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/CustomerForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    public void saveButton(ActionEvent event) {

    }
}

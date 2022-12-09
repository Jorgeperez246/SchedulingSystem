package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportFormController {
    public TableView AppointmentInfoTable;
    public TableColumn AppMonthCol;
    public TableColumn AppTypeCol;
    public TableColumn TotAppCol;
    public TableColumn DivisionCol;
    public TableColumn TotCustomerCol;
    public ComboBox ContactCombo;
    public TableView ReportTable;
    public TableColumn IdCol;
    public TableColumn TitleCol;
    public TableColumn TypeCol;
    public TableColumn DescriptionCol;
    public TableColumn LocationCol;
    public TableColumn StartCol;
    public TableColumn EndCol;
    public TableColumn CustomerIdCol;
    public Button Back;
    public Button LogOut;

    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    public void logOutButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

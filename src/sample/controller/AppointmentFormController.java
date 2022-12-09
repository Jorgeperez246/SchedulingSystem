package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentFormController {
    public TableView AppointmentTable;
    public TableColumn IdCol;
    public TableColumn TitleCol;
    public TableColumn TypeCol;
    public TableColumn DescriptionCol;
    public TableColumn LocationCol;
    public TableColumn StartCol;
    public TableColumn EndCol;
    public TableColumn ContactCol;
    public TableColumn CustomerCol;
    public TableColumn UserIdCol;
    public RadioButton allAppointments;
    public ToggleGroup appointmentToggle;
    public RadioButton MonthAppointments;
    public RadioButton WeekAppointments;
    public Button Add;
    public Button Modify;
    public Button Delete;

    public Button Back;

    public void addButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/AddAppointmentForm.fxml"));
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

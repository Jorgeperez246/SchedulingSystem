package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.AppointmentDB;
import sample.DAO.JDBC;
import sample.model.Appointment;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentFormController{
    public TableView<Appointment> AppointmentTable;
    public TableColumn<?,?> IdCol;
    public TableColumn<?,?> TitleCol;
    public TableColumn<?,?> TypeCol;
    public TableColumn<?,?> DescriptionCol;
    public TableColumn<?,?> LocationCol;
    public TableColumn<?,?> StartCol;
    public TableColumn<?,?> EndCol;
    public TableColumn<?,?> ContactCol;
    public TableColumn<?,?> CustomerCol;
    public TableColumn<?,?> UserIdCol;
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
        try {
            JDBC.makeConnection();
            Appointment selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();



            if (selectedAppointment != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/ModifyAppointmentForm.fxml"));
                loader.load();

                ModifyAppointmentFormController Controller = loader.getController();
                Controller.sendAppointmentToModify(AppointmentTable.getSelectionModel().getSelectedIndex(),AppointmentTable.getSelectionModel().getSelectedItem());

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
        ObservableList<Appointment> allAppointmentsList = AppointmentDB.getAllAppointments();

        IdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("descr"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        AppointmentTable.setItems(allAppointmentsList);
    }


}


package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.AppointmentDB;
import sample.DAO.JDBC;
import sample.model.Appointment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

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
        try {
            Connection connection = JDBC.getConnection();
            int selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem().getAppointmentId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected appointment with appointment id: " +
                    selectedAppointment);
            Optional<ButtonType> confirm = alert.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                String query = "DELETE FROM appointments WHERE Appointment_ID=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, selectedAppointment);
                ps.execute();

                ObservableList<Appointment> allAppointmentList = AppointmentDB.getAllAppointments();
                AppointmentTable.setItems(allAppointmentList);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
    @FXML
    void displayAllAppointments(ActionEvent event) throws SQLException {
        try {
            ObservableList<Appointment> allAppointmentsList = AppointmentDB.getAllAppointments();

            for (Appointment ignored : allAppointmentsList) {
                AppointmentTable.setItems(allAppointmentsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void displayMonthAppointments(ActionEvent event) throws SQLException {
        try {
            ObservableList<Appointment> allAppointmentsList = AppointmentDB.getAllAppointments();
            ObservableList<Appointment> allAppointmentsForMonth = FXCollections.observableArrayList();

            LocalDateTime startMonth = LocalDateTime.now().minusMonths(1);
            LocalDateTime endMonth = LocalDateTime.now().plusMonths(1);


            allAppointmentsList.forEach(appointment -> {
                if (appointment.getEnd().isAfter(startMonth) && appointment.getEnd().isBefore(endMonth)) {
                    allAppointmentsForMonth.add(appointment);
                }
                AppointmentTable.setItems(allAppointmentsForMonth);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayWeekAppointments(ActionEvent event) throws SQLException {
        try {

            ObservableList<Appointment> allAppointmentsList = AppointmentDB.getAllAppointments();
            ObservableList<Appointment> allAppointmentsForWeek = FXCollections.observableArrayList();

            LocalDateTime startWeek = LocalDateTime.now().minusWeeks(1);
            LocalDateTime endWeek = LocalDateTime.now().plusWeeks(1);

            allAppointmentsList.forEach(appointment -> {
                if (appointment.getEnd().isAfter(startWeek) && appointment.getEnd().isBefore(endWeek)) {
                    allAppointmentsForWeek.add(appointment);
                }
                AppointmentTable.setItems(allAppointmentsForWeek);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


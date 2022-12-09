package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    public Button Reports;
    public Button Appointments;
    public Button Customers;
    public Button Logout;

    public void reportsButton(ActionEvent event) throws IOException {
        Parent reportScene = FXMLLoader.load(getClass().getResource("/sample/view/ReportForm.fxml"));
        Scene scene = new Scene(reportScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void appointmentsButton(ActionEvent event) throws IOException {

        Parent appointmentScene = FXMLLoader.load(getClass().getResource("/sample/view/AppointmentForm.fxml"));
        Scene scene = new Scene(appointmentScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void customersButton(ActionEvent event) throws IOException {
        Parent customerScene = FXMLLoader.load(getClass().getResource("/sample/view/CustomerForm.fxml"));
        Scene scene = new Scene(customerScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void logoutButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

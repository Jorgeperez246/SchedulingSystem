package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAppointmentFormController {
    public Label AppointmentIdLabel;
    public Label AppointmentTitleLabel;
    public Label AppointmentTypeLabel;
    public Label AppointmentDesLabel;
    public Label AppointmentLocLabel;
    public Label AppointmentSDLabel;
    public Label AppointmentEDLabel;
    public Label AppointmentContactLabel;
    public Label AppointmentCustLabel;
    public Label AppointmentUserLabel;
    public TextField AppointmentIdText;
    public TextField AppointmentTitleText;
    public TextArea Description;
    public TextField AppointmentLocationText;
    public Label AppointmentSTLabel;
    public Label AppointmentETLabel;
    public DatePicker AppointmentStartDate;
    public ComboBox AppointmentType;
    public ComboBox AppointmentStartTime;
    public ComboBox AppointmentEndTime;
    public ComboBox AppointmentContact;
    public ComboBox AppointmentCustomer;
    public ComboBox AppointmentUser;
    public DatePicker AppointmentEndDate;
    public Button Cancel;
    public Button Save;

    public void addAppointmentId(ActionEvent event) {
    }

    public void addAppointmentTitle(ActionEvent event) {
    }

    public void addAppointmentLocation(ActionEvent event) {
    }

    public void addAppointmentStartDate(ActionEvent event) {
    }

    public void addAppointmentType(ActionEvent event) {
    }

    public void addAppointmentStartTime(ActionEvent event) {
    }

    public void addAppointmentEndTime(ActionEvent event) {
    }

    public void addAppointmentContact(ActionEvent event) {
    }

    public void addAppointmentCustomer(ActionEvent event) {
    }

    public void addAppointmentUser(ActionEvent event) {
    }

    public void addAppointmentEndDate(ActionEvent event) {
    }

    public void cancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/AppointmentForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    public void saveButton(ActionEvent event) {
    }
}

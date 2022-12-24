package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DAO.AppointmentDB;
import sample.DAO.UserCredentials;
import sample.model.Appointment;
import sample.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * LoginFormController validates user as well as displays upcoming appointments
 * upon successful login
 */
public class LoginFormController implements Initializable {


    public Label Location;
    public Label UserNameLabel;
    public Label PasswordLabel;
    public Label LocationLabel;
    public Label LoginLabel;
    @FXML
    private TextField UserNameText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private Button Login;


    /**
     * Login button which validates user upon click and if successful displays
     * any upcoming appointments
     * @param event
     * @throws IOException
     */
    public void LoginButton(ActionEvent event) throws IOException {
        try {
            ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();
            Locale systemLocale = Locale.getDefault();
            ResourceBundle messages = ResourceBundle.getBundle("sample.languages.LoginForm",systemLocale);
            LocalDateTime start = null;
            boolean upcomingAppointments = false;
            LocalDateTime nowMinus15 =LocalDateTime.now().minusMinutes(15);
            LocalDateTime nowPlus15 = LocalDateTime.now().plusMinutes(15);
            int upcomingApp = 1;

            String usernameInput = UserNameText.getText();
            String passwordInput = PasswordText.getText();
            int userId = UserCredentials.userValidation(usernameInput, passwordInput);

            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (userId > 0) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/MainForm.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) Login.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();



                outputFile.print("user: " + usernameInput + " successfully logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");

                for (Appointment appointment: getAllAppointments){
                    start = appointment.getStart();
                    if((start.isAfter(nowMinus15)||start.isEqual(nowMinus15)&&(start.isBefore(nowPlus15)||start.isEqual(nowPlus15)))){
                        upcomingApp = appointment.getAppointmentId();

                        upcomingAppointments = true;
                    }

                }
                if (upcomingAppointments){

                    appointmentValidations("Appointment is within 15 minute interval: " + upcomingApp + "\n Appointments starts: " + start);


                }
                else {
                    appointmentValidations("No upcoming appointments");
                }
            } else if (userId < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(messages.getString("Error"));
                alert.setContentText(messages.getString("Incorrect"));
                alert.show();


                outputFile.print("user: " + usernameInput + " failed login attempt at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");

            }
            outputFile.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * loads time zone defaults along with language to display
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TimeZone systemTimeZone = TimeZone.getDefault();
        String locationZone = systemTimeZone.getID();
        Location.setText(locationZone);

        Locale systemLocale = Locale.getDefault();

        ResourceBundle messages = ResourceBundle.getBundle("sample.languages.LoginForm",systemLocale);

        UserNameLabel.setText(messages.getString("Username"));
        PasswordLabel.setText(messages.getString("Password"));
        LocationLabel.setText(messages.getString("Location"));
        Login.setText(messages.getString("Login"));
        LoginLabel.setText(messages.getString("Login"));


    }

    /**
     * Confirmation Alert
     * @param error
     */
    public  void appointmentValidations(String error) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, error);
        Optional<ButtonType> confirm = alert.showAndWait();





    }





}


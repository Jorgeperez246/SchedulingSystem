package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DAO.UserCredentials;
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
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;



public class LoginFormController implements Initializable {


    @FXML
    private TextField UserNameText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private Button Login;

    private static final String FILENAME = "loginMonitor.txt";


    public void LoginButton(ActionEvent event) throws IOException {
        try {

            /*gets text from textfields and later compares with username and pass
            * in database, will print successful and unsuccessful attempts to
            * loginMonitor file*/
            String usernameInput = UserNameText.getText();
            String passwordInput = PasswordText.getText();
            int userId = UserCredentials.userValidation(usernameInput, passwordInput);

            FileWriter fileWriter = new FileWriter("loginMonitor.txt", true);
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

            } else if (userId < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Incorrect");
                alert.show();


                outputFile.print("user: " + usernameInput + " failed login attempt at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");

            }
            outputFile.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }





}


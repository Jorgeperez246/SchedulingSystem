package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCredentials extends User {
    public UserCredentials(String userName, int userId, String password) {
        super(userName, userId, password);
    }


    public static int checkForUser(String userName, String password) {


        return 0;
    }
}

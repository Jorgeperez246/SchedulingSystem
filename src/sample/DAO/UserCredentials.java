package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.User;
import sample.DAO.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCredentials extends User {
    public UserCredentials(int userID, String userName, String userPassword) {
        super(userID,userName,userPassword);
    }

    /**
     * validates user entered in login form
     * @param Username
     * @param Password
     * @return
     */
    public static int userValidation(String Username, String Password) {
        try
        {
            String sqlQuery = "SELECT * FROM users WHERE user_name = '" + Username + "' AND password = '" + Password +"'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("User_Name").equals(Username))
            {
                if (rs.getString("Password").equals(Password))
                {
                    return rs.getInt("User_ID");

                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * lists all users in database
     * @return usersList
     * @throws SQLException
     */
    public static ObservableList<UserCredentials> getAllUsers() throws SQLException {
        ObservableList<UserCredentials> usersList = FXCollections.observableArrayList();
        String sql = "SELECT * from users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");
            UserCredentials user = new UserCredentials(userID, userName, userPassword);
            usersList.add(user);
        }
        return usersList;
    }
}





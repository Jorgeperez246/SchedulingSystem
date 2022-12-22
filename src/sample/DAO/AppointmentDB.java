package sample.DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDB {
    /**
     * Lists all appointments within the database
     * @return appointmentsList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        String sql = "SELECT appointments.*, customers.Customer_Name, users.User_Name, contacts.Contact_Name\n" +
                "FROM appointments\n" +
                "INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID\n" +
                "INNER JOIN users ON appointments.User_ID = users.User_ID\n" +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID\n" +
                "Order by Appointment_ID asc";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String descr = rs.getString("Description");
            String loc = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String userName = rs.getString("User_Name");
            String customerName = rs.getString("Customer_Name");
            Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, start, end, title, descr, type, loc,contactName,userName,customerName);
            appointmentsList.add(appointment);
        }

        return appointmentsList;
    }

}

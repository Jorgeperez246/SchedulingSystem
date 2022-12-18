package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DAO.*;
import sample.model.Appointment;
import sample.model.Contact;
import sample.model.Customer;
import sample.model.User;

import java.io.IOException;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

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
    
    public ComboBox<String> AppointmentStartTime;
    public ComboBox<String> AppointmentEndTime;
    public ComboBox<String> AppointmentContact;
    public ComboBox<String> AppointmentCustomer;
    public ComboBox<String> AppointmentUser;
    public DatePicker AppointmentEndDate;
    public Button Cancel;
    public Button Save;
    public TextField AppointmentType;

    public void addAppointmentId(ActionEvent event) {
    }

    public void addAppointmentTitle(ActionEvent event) {
    }

    public void addAppointmentLocation(ActionEvent event) {
    }

    public void addAppointmentStartDate(ActionEvent event) {
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
        try {

            Connection connection = JDBC.getConnection();

            if (!AppointmentTitleText.getText().isEmpty() && !Description.getText().isEmpty() && !AppointmentLocationText.getText().isEmpty() && !AppointmentType.getText().isEmpty() && AppointmentStartDate.getValue() != null && AppointmentEndDate.getValue() != null && !AppointmentStartTime.getValue().isEmpty() && !AppointmentEndTime.getValue().isEmpty() && !AppointmentCustomer.getValue().isEmpty()) {

                ObservableList<Customer> getAllCustomers = CustomerDB.getAllCustomers();
                ObservableList<Integer> storeCustomerIDs = FXCollections.observableArrayList();
                ObservableList<UserCredentials> getAllUsers = UserCredentials.getAllUsers();
                ObservableList<Integer> storeUserIDs = FXCollections.observableArrayList();
                ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();


                getAllCustomers.stream().map(Customer::getCustomerId).forEach(storeCustomerIDs::add);
                getAllUsers.stream().map(User::getUserID).forEach(storeUserIDs::add);

                LocalDate localDateEnd = AppointmentEndDate.getValue();
                LocalDate localDateStart = AppointmentStartDate.getValue();

                DateTimeFormatter minHourFormat = DateTimeFormatter.ofPattern("HH:mm");
                String appointmentStartDate = AppointmentStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String appointmentStartTime = AppointmentStartTime.getValue();

                String endDate = AppointmentEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String endTime = AppointmentEndTime.getValue();

                System.out.println("thisDate + thisStart " + appointmentStartDate + " " + appointmentStartTime + ":00");
                String startUTC = convertTimeDateUTC(appointmentStartDate + " " + appointmentStartTime + ":00");
                String endUTC = convertTimeDateUTC(endDate + " " + endTime + ":00");

                LocalTime localTimeStart = LocalTime.parse(AppointmentStartTime.getValue(), minHourFormat);
                LocalTime LocalTimeEnd = LocalTime.parse(AppointmentEndTime.getValue(), minHourFormat);

                LocalDateTime dateTimeStart = LocalDateTime.of(localDateStart, localTimeStart);
                LocalDateTime dateTimeEnd = LocalDateTime.of(localDateEnd, LocalTimeEnd);

                ZonedDateTime zoneDtStart = ZonedDateTime.of(dateTimeStart, ZoneId.systemDefault());
                ZonedDateTime zoneDtEnd = ZonedDateTime.of(dateTimeEnd, ZoneId.systemDefault());

                ZonedDateTime convertStartEST = zoneDtStart.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime convertEndEST = zoneDtEnd.withZoneSameInstant(ZoneId.of("America/New_York"));

                LocalTime startAppointmentTimeToCheck = convertStartEST.toLocalTime();
                LocalTime endAppointmentTimeToCheck = convertEndEST.toLocalTime();

                DayOfWeek startAppointmentDayToCheck = convertStartEST.toLocalDate().getDayOfWeek();
                DayOfWeek endAppointmentDayToCheck = convertEndEST.toLocalDate().getDayOfWeek();

                int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
                int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();

                int workWeekStart = DayOfWeek.MONDAY.getValue();
                int workWeekEnd = DayOfWeek.FRIDAY.getValue();

                LocalTime estBusinessStart = LocalTime.of(8, 0, 0);
                LocalTime estBusinessEnd = LocalTime.of(22, 0, 0);

                int appointmentId = Integer.parseInt(String.valueOf((int) (Math.random() * 100)));

                String insertStatement = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                JDBC.makePreparedStatement(insertStatement,JDBC.getConnection() );
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, appointmentId);
                ps.setString(2, AppointmentTitleText.getText());
                ps.setString(3, Description.getText());
                ps.setString(4, AppointmentLocationText.getText());
                ps.setString(5, AppointmentType.getText());
                //ps.setTimestamp(6, Timestamp.valueOf(startLocalDateTimeToAdd));
                ps.setTimestamp(6, Timestamp.valueOf(startUTC));
                ps.setTimestamp(7, Timestamp.valueOf(endUTC));
                //need to verify this is correct
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "admin");
                ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(11, 1);
                ps.setInt(12, Integer.parseInt(findCustomerID(AppointmentCustomer.getValue())));
                ps.setInt(13, Integer.parseInt(findContactID(AppointmentContact.getValue())));
                ps.setInt(14, Integer.parseInt(findUserID(AppointmentUser.getValue())));


                ps.execute();


                Parent root = FXMLLoader.load(getClass().getResource("/sample/view/AppointmentForm.fxml"));
                Scene scene = new Scene(root);
                Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MainScreenReturn.setScene(scene);
                MainScreenReturn.show();

            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws SQLException {

        ObservableList<Contact> contactsObservableList = ContactDB.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        ObservableList<UserCredentials> userCredentialsObservableList = UserCredentials.getAllUsers();
        ObservableList<String>  allUserNames = FXCollections.observableArrayList();
        ObservableList<Customer> customerObservableList = CustomerDB.getAllCustomers();
        ObservableList<String> allCustomerNames = FXCollections.observableArrayList();


        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));

        ObservableList<String> appointmentTimes = FXCollections.observableArrayList();

        userCredentialsObservableList.forEach(users ->allUserNames.add(users.getUserName()));

        customerObservableList.forEach(customer -> allCustomerNames.add(customer.getCustomerName()));

        LocalTime firstAppointment = LocalTime.MIN.plusHours(8);
        LocalTime lastAppointment = LocalTime.MAX.minusHours(1).minusMinutes(45);

        
        if (!firstAppointment.equals(0) || !lastAppointment.equals(0)) {
            while (firstAppointment.isBefore(lastAppointment)) {
                appointmentTimes.add(String.valueOf(firstAppointment));
                firstAppointment = firstAppointment.plusMinutes(15);
            }
        }
        AppointmentStartTime.setItems(appointmentTimes);
        AppointmentEndTime.setItems(appointmentTimes);
        AppointmentContact.setItems(allContactsNames);
        AppointmentUser.setItems(allUserNames);
        AppointmentCustomer.setItems(allCustomerNames);

        System.out.println(allUserNames);

    }
    public static String convertTimeDateUTC(String dateTime) {
        Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(dateTime));
        LocalDateTime LocalDT = currentTimeStamp.toLocalDateTime();
        ZonedDateTime zoneDT = LocalDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utcDT = zoneDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime localOUT = utcDT.toLocalDateTime();
        return localOUT.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
    }

    public static String findContactID(String contactID) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM contacts WHERE Contact_Name = ?");
        ps.setString(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactID = rs.getString("Contact_ID");
        }
        return contactID;
    }
    public static String findCustomerID(String customerID) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM customers WHERE Customer_Name = ?");
        ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            customerID = rs.getString("Customer_ID");
        }
        return customerID;
    }
    public static String findUserID(String userID) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM users WHERE User_Name = ?");
        ps.setString(1, userID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            userID = rs.getString("User_ID");
        }
        return userID;
    }

}

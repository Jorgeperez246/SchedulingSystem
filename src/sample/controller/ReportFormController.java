package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.*;
import sample.DAO.ReportCountriesDB;
import sample.model.Appointment;
import sample.model.Contact;
import sample.model.ReportCountries;
import sample.model.ReportMonth;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Month;

/**
 * displays monthly reports, contact reports, as well as country report
 */
public class ReportFormController {
    public TableView<ReportMonth> AppointmentInfoTable;
    public TableColumn<?,?> AppMonthCol;
    public TableColumn<?,?> AppTypeCol;
    public TableColumn<?,?> MonthTotalAppCol;
    public ComboBox<String> ContactCombo;
    public TableView<Appointment> ReportTable;
    public TableColumn<?,?> IdCol;
    public TableColumn<?,?> TitleCol;
    public TableColumn<?,?> TypeCol;
    public TableColumn<?,?> DescriptionCol;
    public TableColumn<?,?> LocationCol;
    public TableColumn<?,?> StartCol;
    public TableColumn<?,?> EndCol;
    public TableColumn<?,?> CustomerIdCol;
    public Button Back;
    public Button LogOut;



    public TableView<ReportCountries> CountryAppointments;
    public TableColumn<?,?> CustomersPerCountry;
    public TableColumn<?,?> CountryCol;

    /**
     * returns you to the MainForm
     * @param event
     * @throws IOException
     */
    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    /**
     * closes application
     * @param event
     */
    public void logOutButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * fills tables with needed report data from database as well as
     * the combobox customer selection
     * Lambda used to fill contact names into ContactCombo combo box
     * @throws SQLException
     */
    public void initialize() throws SQLException {



        IdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("descr"));
        LocationCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));


        ObservableList<ReportMonth> allMonthReportList = ReportMonthDB.getAllMonths();
        AppMonthCol.setCellValueFactory(new PropertyValueFactory<>("monthName"));
        AppTypeCol.setCellValueFactory(new PropertyValueFactory<>("types"));
        MonthTotalAppCol.setCellValueFactory(new PropertyValueFactory<>("appPerMonth"));

        AppointmentInfoTable.setItems(allMonthReportList);


        ObservableList<ReportCountries> allCountryReportList = ReportCountriesDB.getCountries();
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        CustomersPerCountry.setCellValueFactory(new PropertyValueFactory<>("numOfAppPerCountry"));

        CountryAppointments.setItems(allCountryReportList);



        ObservableList<Contact> contactsObservableList = ContactDB.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        // lambda used here
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        ContactCombo.setItems(allContactsNames);

    }

    /**
     * changes ReportTable depending on which contact was chosen
     */
    @FXML
    public void getAppointmentInfo() {
        try {

            int contactID = 0;

            ObservableList<Appointment> getAllAppointmentData = AppointmentDB.getAllAppointments();
            ObservableList<Appointment> appointmentInfo = FXCollections.observableArrayList();
            ObservableList<Contact> getAllContacts = ContactDB.getAllContacts();

            Appointment contactAppointmentInfo;

            String contactName = ContactCombo.getSelectionModel().getSelectedItem();

            for (Contact contact: getAllContacts) {
                if (contactName.equals(contact.getContactName())) {
                    contactID = contact.getContactId();
                }
            }

            for (Appointment appointment: getAllAppointmentData) {
                if (appointment.getContactId() == contactID) {
                    contactAppointmentInfo = appointment;
                    appointmentInfo.add(contactAppointmentInfo);
                }
            }
            ReportTable.setItems(appointmentInfo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

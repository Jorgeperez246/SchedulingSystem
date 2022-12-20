package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;
import sample.model.ReportCountries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportCountriesDB extends Appointment {
    public ReportCountriesDB(int appointmentId, int customerId, int userId, int contactId, LocalDateTime end, LocalDateTime start, String title, String descr, String type, String loc, String contactName, String userName, String customerName) {
        super(appointmentId, customerId, userId, contactId, end, start, title, descr, type, loc, contactName, userName, customerName);
    }


    public static ObservableList<ReportCountries> getCountries() throws SQLException {
        ObservableList<ReportCountries> countriesObservableList = FXCollections.observableArrayList();
        String sql = "select countries.Country, count(*) as countryCount from customers inner join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID inner join countries on countries.Country_ID = first_level_divisions.Country_ID where  customers.Division_ID = first_level_divisions.Division_ID group by first_level_divisions.Country_ID";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String countryName = rs.getString("Country");
            int numOfAppPerCountry = rs.getInt("countryCount");
            ReportCountries report = new ReportCountries(numOfAppPerCountry, countryName);
            countriesObservableList.add(report);
        }

        return countriesObservableList;
    }
}

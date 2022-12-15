package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDB extends Country {
    public CountryDB(String countryName, int countryId) {
        super(countryName, countryId);
    }

    public static ObservableList<CountryDB> getCountries() throws SQLException {
        ObservableList<CountryDB> countriesObservableList = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Country from countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            CountryDB country = new CountryDB(countryName, countryId);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }
}

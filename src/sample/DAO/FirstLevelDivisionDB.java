package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionDB extends FirstLevelDivision {
    public FirstLevelDivisionDB(String divisionName, int divisionID, int country_ID) {
        super(divisionName,divisionID, country_ID);
    }

    /**
     * Lists first_level_division data
     * @return firstLevelDivisionsList
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivisionDB> getAllFirstLevelDivisions() throws SQLException {
        ObservableList<FirstLevelDivisionDB> firstLevelDivisionsList = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            int country_ID = rs.getInt("COUNTRY_ID");
            String divisionName = rs.getString("Division");

            FirstLevelDivisionDB firstLevelDivision = new FirstLevelDivisionDB( divisionName,divisionID, country_ID);
            firstLevelDivisionsList.add(firstLevelDivision);
        }
        return firstLevelDivisionsList;
    }
}

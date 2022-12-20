package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Contact;
import sample.model.ReportMonth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMonthDB {
    public static ObservableList<ReportMonth> getAllMonths() throws SQLException {
        ObservableList<ReportMonth> reportMonthObservableList = FXCollections.observableArrayList();
        String sql = "SELECT MONTHNAME(Start) AS month_name,\n" +
                "\t   REPLACE(GROUP_CONCAT(appointments.Type), ',', '\\n') AS types,\n" +
                "       COUNT(*) AS total_appointments\n" +
                "       \n" +
                "FROM appointments\n" +
                "GROUP BY month_name\n" +
                "ORDER BY extract(MONTH from Start);\n";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appPerMonth = rs.getInt("total_appointments");
            String monthName = rs.getString("month_name");
            String types = rs.getString("types");
            ReportMonth reportMonth = new ReportMonth(monthName, types, appPerMonth);
            reportMonthObservableList.add(reportMonth);
        }
        return reportMonthObservableList;
    }
}


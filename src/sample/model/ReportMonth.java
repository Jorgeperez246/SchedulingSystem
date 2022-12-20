package sample.model;

public class ReportMonth {

    private String monthName,types;
    public int appPerMonth;
    public ReportMonth(String monthName, String types, int appPerMonth) {
        this.monthName = monthName;
        this.types = types;
        this.appPerMonth = appPerMonth;
    }



    public String getMonthName() {
        return monthName;
    }

    public String getTypes() {
        return types;
    }

    public int getAppPerMonth() {
        return appPerMonth;
    }
}

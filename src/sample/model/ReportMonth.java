package sample.model;

public class ReportMonth {

    private String monthName,types;
    public int appPerMonth;

    /**
     *
     * @param monthName
     * @param types
     * @param appPerMonth
     */
    public ReportMonth(String monthName, String types, int appPerMonth) {
        this.monthName = monthName;
        this.types = types;
        this.appPerMonth = appPerMonth;
    }


    /**
     *
     * @return monthName
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     *
     * @return types
     */
    public String getTypes() {
        return types;
    }

    /**
     *
     * @return appPerMonth
     */
    public int getAppPerMonth() {
        return appPerMonth;
    }
}

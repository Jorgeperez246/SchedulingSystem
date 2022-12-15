package sample.model;

public class FirstLevelDivision {
    private String divisionName;
    private int divisionId,countryId;

    public FirstLevelDivision(String divisionName, int divisionId, int countryId) {
        this.divisionName = divisionName;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public int getCountryId() {
        return countryId;
    }
}

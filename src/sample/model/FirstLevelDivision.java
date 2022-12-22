package sample.model;

public class FirstLevelDivision {
    private String divisionName;
    private int divisionId,countryId;

    /**
     *
     * @param divisionName
     * @param divisionId
     * @param countryId
     */
    public FirstLevelDivision(String divisionName, int divisionId, int countryId) {
        this.divisionName = divisionName;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    /**
     *
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     *
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }
}

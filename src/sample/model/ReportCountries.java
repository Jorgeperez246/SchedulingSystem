package sample.model;

public class ReportCountries {

    /**
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @return NumOfAppPerCountry
     */
    public int getNumOfAppPerCountry() {
        return NumOfAppPerCountry;
    }

    private String countryName;
    private int NumOfAppPerCountry;


    /**
     *
     * @param NumOfAppPerCountry
     * @param countryName
     */
    public ReportCountries(int NumOfAppPerCountry, String countryName) {
        this.NumOfAppPerCountry = NumOfAppPerCountry;
        this.countryName = countryName;

    }
}

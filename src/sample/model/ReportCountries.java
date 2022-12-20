package sample.model;

public class ReportCountries {


    public String getCountryName() {
        return countryName;
    }

    public int getNumOfAppPerCountry() {
        return NumOfAppPerCountry;
    }

    private String countryName;
    private int NumOfAppPerCountry;



    public ReportCountries(int NumOfAppPerCountry, String countryName) {
        this.NumOfAppPerCountry = NumOfAppPerCountry;
        this.countryName = countryName;

    }
}

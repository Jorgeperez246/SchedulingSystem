package sample.model;

public class Country {
    private String countryName;
    private int countryId;

    public Country(String countryName, int countryId) {
        this.countryName = countryName;
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryId() {
        return countryId;
    }
}

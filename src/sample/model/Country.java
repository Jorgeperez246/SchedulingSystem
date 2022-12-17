package sample.model;
import javafx.scene.control.TableView;

public class Country{
    public  String countryName;
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

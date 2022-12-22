package sample.model;
import javafx.scene.control.TableView;

public class Country{
    public  String countryName;
    private int countryId;

    /**
     *
     * @param countryName
     * @param countryId
     */
    public Country(String countryName, int countryId) {
        this.countryName = countryName;
        this.countryId = countryId;
    }


    /**
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @return countryName
     */
    public int getCountryId() {
        return countryId;
    }
}

package ro.mta.facc.selab.meteoapp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *Clasa care face parte din arhitectura MVC.
 * Are rolul de a reprezenta entitatea meteo a aplicatiei.
 *
 * @author VinceDT
 * */

public class MeteoModel {
    StringProperty city_id;
    StringProperty country_code;
    StringProperty latitude;
    StringProperty longitude;
    StringProperty city_name;

    public MeteoModel(String city_id, String city_name, String latitude, String longitude, String country_code) {
        this.city_id = new SimpleStringProperty(city_id);
        this.city_name = new SimpleStringProperty(city_name);
        this.country_code = new SimpleStringProperty(country_code);
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);
    }

    //##### country #####
    public String get_country_code() { return country_code.get(); }
    public StringProperty get_country_code_property(){ return country_code; }
    public void setCountry_code(String country_code){ this.country_code.set(country_code); }


    //##### city #####
    public String get_city_id() { return city_id.get(); }
    public StringProperty get_city_id_property(){ return city_id; }
    public void setCity_id(String city_id) { this.city_id.set(city_id); }

    public String getCity_name(){ return city_name.get(); }
    public StringProperty city_name_property() { return city_name; }
    public void setCity_name(String city_name){ this.city_name.set(city_name); }


    //##### longitude #####
    public String get_longitude(){ return longitude.get(); }
    public StringProperty longitude_property() { return longitude; }
    public void set_longitude(String longitude){ this.longitude.set(longitude); }


    //##### latitude #####
    public String get_latitude(){ return latitude.get(); }
    public StringProperty latitude_property() { return latitude; }
    public void set_latitude(String latitude){ this.latitude.set(latitude); }


}

package ro.mta.facc.selab.meteoapp.model;

import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MeteoModelTest {

    String city_id;
    String country_code;
    String latitude;
    String longitude;
    String city_name;

    private MeteoModel test_meteo;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        city_id="683843";
        country_code="RO";
        latitude="45.75";
        longitude="25.3333";
        city_name="Brasov";

        File input_test = new File("src/main/resources/input.txt");
        if(input_test.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(input_test));
            String line_test = in.readLine();

            while(line_test != null){

                String delimitator = "#";
                String[] columns = line_test.split(delimitator);
                test_meteo = new MeteoModel(columns[0], columns[1], columns[2], columns[3], columns[4]);
                line_test = in.readLine();
            }
        }

        //test_meteo = new MeteoModel(city_id, city_name, latitude, longitude, country_code);
    }

    @org.junit.jupiter.api.Test
    void get_country_code() {
        assertEquals(test_meteo.get_country_code(), this.country_code);
    }

    @org.junit.jupiter.api.Test
    void getCity_name() {
        assertEquals(test_meteo.getCity_name(), this.city_name);
    }

    @org.junit.jupiter.api.Test
    void get_city_id() {
        assertEquals(test_meteo.get_city_id(), this.city_id);
    }

    @org.junit.jupiter.api.Test
    void get_latitude() {
        assertEquals(test_meteo.get_latitude(), this.latitude);
    }

    @org.junit.jupiter.api.Test
    void get_longitude() {
        assertEquals(test_meteo.get_longitude(), this.longitude);
    }
}
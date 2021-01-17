package ro.mta.facc.selab.meteoapp.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class MeteoModelTestMocking {
    String city_id1;
    String country_code1;
    String latitude1;
    String longitude1;
    String city_name1;

    private MeteoModel test_meteo_mocking;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {

        BufferedReader buff = mock(BufferedReader.class);
         //.thenReturn("683843");
        //when(buff.readLine()); //;
        //when(buff.readLine()); //;
        //when(buff.readLine()); //;
        //when(buff.readLine()); //;
        //this.city_id1 = "683843";

        when(buff.readLine()).thenReturn("683843").thenReturn("Brasov").thenReturn("45.75").thenReturn("25.3333").thenReturn("RO");
        this.city_id1=buff.readLine();
        this.city_name1=buff.readLine();
        this.latitude1=buff.readLine();
        this.longitude1=buff.readLine();
        this.country_code1=buff.readLine();


        File input_test = new File("src/main/resources/input.txt");
        if(input_test.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(input_test));

            String line_test = in.readLine();

            while(line_test != null){

                String delimitator = "#";
                String[] columns = line_test.split(delimitator);
                test_meteo_mocking = new MeteoModel(columns[0], columns[1], columns[2], columns[3], columns[4]);
                line_test = in.readLine();
            }
        }
    }

    @org.junit.jupiter.api.Test
    void get_city_id() {
        assertEquals(test_meteo_mocking.get_city_id(), this.city_id1);
        //assertEquals("683843", this.city_id1);
    }

    @org.junit.jupiter.api.Test
    void getCity_name() {
        assertEquals(test_meteo_mocking.getCity_name(), this.city_name1);
        //assertEquals("Brasov", this.city_name1);
    }

    @org.junit.jupiter.api.Test
    void get_latitude() {
        assertEquals(test_meteo_mocking.get_latitude(), this.latitude1);
        //assertEquals("45.75", this.latitude1);
    }

    @org.junit.jupiter.api.Test
    void get_longitude() {
        //assertEquals("25.3333", this.longitude1);
        assertEquals(test_meteo_mocking.get_longitude(), this.longitude1);
    }

    @org.junit.jupiter.api.Test
    void get_country_code() {
        assertEquals(test_meteo_mocking.get_country_code(), this.country_code1);
    }
}
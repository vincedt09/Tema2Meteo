package ro.mta.facc.selab.meteoapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import org.apache.commons.io.IOUtils;

import ro.mta.facc.selab.meteoapp.model.CityCode;
import ro.mta.facc.selab.meteoapp.model.MeteoModel;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.*;
import java.lang.*;

/**
 *Clasa care face parte din arhitectura MVC.
 * Are rolul de a controla obiectele din interfata grafica precum: ComboBox, Label, ImageView
 *
 * @author VinceDT
 * */

public class MeteoController {

    @FXML
    private Label info_tara;
    @FXML
    private Label info_oras;
    @FXML
    private Label info_temp;
    @FXML
    private Label info_umiditate;
    @FXML
    private Label info_stare;
    @FXML
    private Label info_data;
    @FXML
    private Label info_time;
    @FXML
    private Label info_wind;
    @FXML
    private Label info_press;
    @FXML
    private ImageView id_stare;
    @FXML
    private ComboBox combo_box_tara;
    @FXML
    private ComboBox combo_box_oras;


    private ObservableList<MeteoModel> weather_data = FXCollections.observableArrayList();
    private ObservableList<CityCode> city_code = FXCollections.observableArrayList();



    /**
     *  Se realizeaza citirea fisierului de intrare
     */
    private void citire_fisier_input() throws IOException {
        File input = new File("src/main/resources/input.txt");
        if(input.exists()){

            BufferedReader in = new BufferedReader(new FileReader(input));
            String line = in.readLine();

            while(line != null){

                String delimitator = "#";
                String[] columns = line.split(delimitator);
/*
                if(columns[4].equals("RO")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "ROMANIA"));
                }
                if(columns[4].equals("GB")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "ENGLAND"));
                }
                if(columns[4].equals("RU")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "RUSSIA"));
                }
                if(columns[4].equals("FR")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "FRANCE"));
                }
                if(columns[4].equals("CH")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "SWITZERLAND"));
                }
                if(columns[4].equals("IT")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "ITALIA"));
                }
                if(columns[4].equals("PL")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "POLAND"));
                }
                if(columns[4].equals("ESP")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "SPAIN"));
                }
                if(columns[4].equals("US")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "UNITED STATES"));
                }
                if(columns[4].equals("DE")) {
                    meteoModel.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], "GERMANY"));
                }

*/
                weather_data.add(new MeteoModel(columns[0], columns[1], columns[2], columns[3], columns[4]));
                line = in.readLine();
            }

        }
        if(!input.exists()){
            throw new IOException("Eroare la deschiderea fisierului");
        }
    }


    /**
     *  Se realizeaza citirea fisierului care contine numele tarilor si codul acestora (ex: Romania#RO)
     */
    private void citire_fisier_code() throws IOException{
        File input1 = new File("src/main/resources/citycode.txt");
        if(input1.exists()) {

            BufferedReader in1 = new BufferedReader(new FileReader(input1));
            String line1 = in1.readLine();

            while(line1 != null){
                String delimitator1 = "#";
                String[] columns1 = line1.split(delimitator1);

                city_code.add(new CityCode(columns1[1], columns1[0]));
                line1 = in1.readLine();
            }
        }
        if(!input1.exists()){
            throw new IOException("Eroare la deschiderea fisierului");
        }

    }


    @FXML
    private void initialize() throws IOException {
        citire_fisier_input();
        citire_fisier_code();
    }


    /**
     *  Aceasta functie are rolul de a elimina multiplele aparitii ale codurilor de tara
     *  si de a introduce numelor tarilor in ComboBox-ul destinat acestora
     */
    @FXML
    public void combobox_tari(MouseEvent mouseEvent) {
        Set<String> uniqueContries = new HashSet<String>();
        ArrayList<String> array_country = new ArrayList<String>();


        for (int i=0; i<weather_data.size(); i++){
            for(int j=0; j<city_code.size(); j++){
                if(city_code.get(j).get_cod_tara().equals(weather_data.get(i).get_country_code())){
                    uniqueContries.add(city_code.get(j).get_nume_tara());
                }
            }

        }

        if(combo_box_tara.getValue() == null) {
            TreeSet<String> sortedCo = new TreeSet<String>(uniqueContries);
            combo_box_tara.getItems().addAll(sortedCo);

        }
        combo_box_oras.getItems().clear();
    }


    /**
     *  Aceasta functie are rolul de a filtra lista de orase apartinand
     *  tarii selectate si de a le introduce in ComboBox-ul destinat oraselor
     */
    @FXML
    public void combobox_oras(MouseEvent mouseEvent){

        ArrayList<String>array_city = new ArrayList<String>();
        String city = combo_box_tara.getValue().toString();


        for(int i=0; i<city_code.size(); i++){
            if(city_code.get(i).get_nume_tara().equals(city)){
                //System.out.println(city);
                for(int j=0; j<weather_data.size(); j++){
                    if(city_code.get(i).get_cod_tara().equals(weather_data.get(j).get_country_code())){
                        array_city.add(weather_data.get(j).getCity_name());
                    }
                }
            }
        }

        combo_box_oras.getItems().clear();
        TreeSet<String> sortedCi = new TreeSet<String>(array_city);
        combo_box_oras.getItems().addAll(sortedCi);

    }


    /**
     *  In aceasta functie se realizaeaza conexiunea la URL-ul de unde
     *  vor fi extrase informatiile meteorologice aferente optiunilor selectate.
     *
     */
    @FXML
    public void parsare(MouseEvent mouseEvent) throws IOException, ParseException, org.json.simple.parser.ParseException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + combo_box_oras.getValue() + "," + combo_box_tara.getValue().toString() + "&appid=663338f34913b9d80589a604c15354b2&lang=ro&units=metric");
        URLConnection connection = url.openConnection();

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String obj1 = IOUtils.toString(reader1);
        //Object object = new JSONParser().parse(obj1);
        //JSONObject aux = (JSONObject) object;


        /**
         *  Se extrag detalii legate de temepratura
         *  si se introduc in Label-ul destinat temeraturii
         */
        JsonObject main_json = Json.parse(obj1).asObject().get("main").asObject();
        float temp1 = main_json.getFloat("temp", 0);
        info_temp.setText(String.valueOf(temp1 + "°C"));


        /**
         *  Se extrag detalii legate de umiditate
         *  si se introduc in Label-ul destinat umiditatii
         */
        int hum1 = main_json.getInt("humidity", 0);
        info_umiditate.setText(String.valueOf(hum1 + "%"));

        /**
         *  Se extrag detalii legate de umiditate
         *  si se introduc in Label-ul destinat umiditatii
         */
        int press1 = main_json.getInt("pressure", 0);
        info_press.setText(String.valueOf(press1 + "hPa"));

        /**
         *  Se extrag detalii legate de viteza vantului
         *  si se introduc in Label-ul destinat vitezei vantului
         */
        JsonObject wind_json = Json.parse(obj1).asObject().get("wind").asObject();
        double speed = wind_json.getDouble("speed", 0);
        info_wind.setText(String.valueOf(speed + "m/s"));

        /**
         *  In acest Label va fi afisat numele tarii si orasului selectat
         */
        info_tara.setText(combo_box_tara.getValue().toString() + "\n" + combo_box_oras.getValue().toString());

        //##### date and time #####

        //String data11 = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        //String time11 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        //info_data.setText(data11);
        //info_time.setText(time11);

        //ZoneId z = ZoneId.of(combo_box_oras.getValue().toString());



        /**
         *  Se obtine iconita specifica vremii si este afisata cu
         *  ajutorul ImageView.
         */
        JsonArray weather_json = Json.parse(obj1).asObject().get("weather").asArray();
        String icon1 = weather_json.get(0).asObject().getString("icon", "Unknown Item");
        String icon11 = "http://openweathermap.org/img/wn/" + icon1 + "@2x.png";
        Image icon_img = new Image(icon11, true);
        id_stare.setImage(icon_img);
        id_stare.setFitHeight(100);
        id_stare.setFitWidth(100);

        /**
         *  Se extrag informatii cu privire la descrierea meteorologica
         *  si se introduc in Label-ul destinat acestora.
         */
        String descriere1 = weather_json.get(0).asObject().getString("description", "Unknown Item");
        info_stare.setText(descriere1);

        /**
         *  Dupa ce informatiile au fost incarcate pe ecran
         *  se va face clear pe cele 2 ComboBox-uri.
         */
        combo_box_tara.getItems().clear();
        combo_box_oras.getItems().clear();
    }

}

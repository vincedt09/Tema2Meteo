package ro.mta.facc.selab.meteoapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 *Acesta clasa reprezinta principala clasa a aplicatiei
 * Ne propunem sa realizam o aplicatie ce va oferi detalii despre vreme
 * folosind un fisier de intrare de forma ID#Nume_Oras#Latitudine#Longitudine#Cod_Tara.
 *
 * @author VinceDT
 * */

public class MainApp extends Application {
    public static void main(String[] args)
    {
        //System.out.println("Hello World!!");

        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(this.getClass().getResource("/view/MeteoView.fxml"));
            primaryStage.setScene(new Scene((Parent) loader.load()));
            primaryStage.show();
        } //catch (IOException e) {
        catch (IOException e) {
            e.printStackTrace();
        }
        //e.printStackTrace();
        //}
    }
}
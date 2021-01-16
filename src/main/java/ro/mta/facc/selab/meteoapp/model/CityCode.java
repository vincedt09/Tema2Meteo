package ro.mta.facc.selab.meteoapp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *Acesta clasa reprezinta entitatea citycode din aplicatie pe care dorim sa o folosim
 *
 * @author VinceDT
 * */

public class CityCode {
    StringProperty cod_tara;
    StringProperty nume_tara;

    public CityCode(String cod_tara, String nume_tara) {
        this.cod_tara = new SimpleStringProperty(cod_tara);
        this.nume_tara = new SimpleStringProperty(nume_tara);
    }

    //##### cod_tara #####
    public String get_cod_tara() { return cod_tara.get(); }

    //##### nume_tara #####
    public String get_nume_tara() { return nume_tara.get(); }


}

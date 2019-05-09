package villagepeoplecottages;

import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.service.AbstractMainFXMLService;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palveluvaraus.PalveluVarausDao;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.lasku.LaskuDao;
import villagepeoplecottages.toimipiste.ToimipisteFXMLSearchFilters;

/**
 * MainFXMLService sisältää metodit MainFXMLController:le
 * 
 * 
 * 
 * 18.4.2019 Lassi Puurunen
 * 24.4.2019 Perii AbstractFXMLServicen
 *  8.5.2019 Toteutettu suodattimet. Lassi Puurunen
 * 
 */

public class MainFXMLService extends AbstractMainFXMLService {

    private ObservableList<Toimipiste> toimipisteet;
    private FilteredList<Toimipiste> toimipisteetFiltered;
    private SortedList<Toimipiste> toimipisteetSorted;
    
    private ObservableList<Palvelu> palvelut;
    private FilteredList<Palvelu> palvelutFiltered;
    private SortedList<Palvelu> palvelutSorted;
    
    private ObservableList<Asiakas> asiakkaat;
    private FilteredList<Asiakas> asiakkaatFiltered;
    private SortedList<Asiakas> asiakkaatSorted;
    
    private ObservableList<PalveluVaraus> palveluvaraukset;
    private FilteredList<PalveluVaraus> palveluvarauksetFiltered;
    private SortedList<PalveluVaraus> palveluvarauksetSorted;
    
    private ObservableList<Lasku> laskut;
    private FilteredList<Lasku> laskutFiltered;
    private SortedList<Lasku> laskutSorted;
    
    public void paivitaNakyma(Object object, TableView tv) throws SQLException {
        
        if (object instanceof Toimipiste) {
            toimipisteet = new ToimipisteDao().list();
            toimipisteetFiltered = new FilteredList<>(toimipisteet, p -> true);
            toimipisteetSorted = new SortedList<>(toimipisteetFiltered);
            toimipisteetSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(toimipisteetSorted);
            
        }
        
        if (object instanceof Palvelu) {
            palvelut = new PalveluDao().list();
            palvelutFiltered = new FilteredList<>(palvelut, p -> true);
            palvelutSorted = new SortedList<>(palvelutFiltered);
            palvelutSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(palvelutSorted);
            
        }
        
        if (object instanceof Asiakas) {
            asiakkaat = new AsiakasDao().list();
            asiakkaatFiltered = new FilteredList<>(asiakkaat, p -> true);
            asiakkaatSorted = new SortedList<>(asiakkaatFiltered);
            asiakkaatSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(asiakkaatSorted);
            
        }
        
        if (object instanceof PalveluVaraus) {
            palveluvaraukset = new PalveluVarausDao().list();
            palveluvarauksetFiltered = new FilteredList<>(palveluvaraukset, p -> true);
            palveluvarauksetSorted = new SortedList<>(palveluvarauksetFiltered);
            palveluvarauksetSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(palveluvarauksetSorted);
            
        }
        
        if (object instanceof Lasku) {
            laskut = new LaskuDao().list();
            laskutFiltered = new FilteredList<>(laskut, p -> true);
            laskutSorted = new SortedList<>(laskutFiltered);
            laskutSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(laskutSorted);
            
        }
    }
    
    
    public void suodataNakyma(Object object, TableView tv, ComboBox<String> toimipisteComboBox, ComboBox<String> palvelutyyppiComboBox, String hakusana) {
        if (object instanceof Toimipiste) {
            new MainFXMLSearchFilters().toimipisteHakuFilter(getToimipisteetFiltered(), hakusana);
            tv.setItems(toimipisteetSorted);
            
        }
        
        if (object instanceof Palvelu) {
            
            new MainFXMLSearchFilters().palveluFilter(getPalvelutFiltered(), toimipisteComboBox.getSelectionModel().getSelectedItem(), palvelutyyppiComboBox.getSelectionModel().getSelectedItem(), hakusana);
            tv.setItems(palvelutSorted);
            
        }
        
        if (object instanceof Asiakas) {
            new MainFXMLSearchFilters().asiakasHakuFilter(getAsiakkaatFiltered(), hakusana);
            tv.setItems(asiakkaatSorted);
            
        }
    }
    
    public void suodataNakyma(Object object, TableView tv, ComboBox<String> toimipisteComboBox, ComboBox<String> palvelutyyppiComboBox, DatePicker mistaDatePicker, DatePicker mihinDatePicker, String hakusana) {
        Date mista = null;
        Date mihin = null;
        
        if (mistaDatePicker.getValue() != null) {
            mista = Date.valueOf(mistaDatePicker.getValue());
        }
        if (mihinDatePicker.getValue() != null) {
            mihin = Date.valueOf(mihinDatePicker.getValue());
        }
        
        
        if (object instanceof PalveluVaraus) {

            new MainFXMLSearchFilters().palveluVarausHakuFilter(getPalveluvarauksetFiltered(), toimipisteComboBox.getSelectionModel().getSelectedItem(), palvelutyyppiComboBox.getSelectionModel().getSelectedItem(), mista, mihin, hakusana);
            tv.setItems(palveluvarauksetSorted);
            
        }
        
    }

    public void suodataNakyma(Lasku lasku, TableView tv, ComboBox<String> laskutToimipisteComboBox, DatePicker laskutAlkaenDatePicker, DatePicker laskutPaattyenDatePicker, ComboBox<String> laskutTilaComboBox, String hakusana) {
        Date mista = null;
        Date mihin = null;
        
        if (laskutAlkaenDatePicker.getValue() != null) {
            mista = Date.valueOf(laskutAlkaenDatePicker.getValue());
        }
        if (laskutPaattyenDatePicker.getValue() != null) {
            mihin = Date.valueOf(laskutPaattyenDatePicker.getValue());
        }
        
        
        new MainFXMLSearchFilters().laskuHakuFilter(getLaskutFiltered(), laskutToimipisteComboBox.getSelectionModel().getSelectedItem(), mista, mihin, laskutTilaComboBox.getSelectionModel().getSelectedItem(), hakusana);
        tv.setItems(laskutSorted);
    }

    public FilteredList<Toimipiste> getToimipisteetFiltered() {
        return toimipisteetFiltered;
    }

    public FilteredList<Palvelu> getPalvelutFiltered() {
        return palvelutFiltered;
    }

    public FilteredList<Asiakas> getAsiakkaatFiltered() {
        return asiakkaatFiltered;
    }

    public FilteredList<PalveluVaraus> getPalveluvarauksetFiltered() {
        return palveluvarauksetFiltered;
    }

    public FilteredList<Lasku> getLaskutFiltered() {
        return laskutFiltered;
    }


       
}

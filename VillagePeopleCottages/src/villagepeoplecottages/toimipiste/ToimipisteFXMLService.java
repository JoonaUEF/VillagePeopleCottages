package villagepeoplecottages.toimipiste;

import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import villagepeoplecottages.MainFXMLSearchFilters;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.lasku.LaskuDao;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palveluvaraus.PalveluVarausDao;
import villagepeoplecottages.service.AbstractSubFXMLService;

/**
 * ToimipisteFXMLService
 * 
 * Toimintoja ToimipisteFXMLController:lle
 * 
 * 
 * 18.4.2019 Lassi Puurunen
 * 
 */



public class ToimipisteFXMLService extends AbstractSubFXMLService {

    
    // Määritetään palvelunäkymän tiedot
    private ObservableList<Palvelu> palvelut;
    private FilteredList<Palvelu> palvelutFiltered;
    private SortedList<Palvelu> palvelutSorted;
    
    // Määritetään varausnäkymän tiedot
    private ObservableList<PalveluVaraus> palveluvaraukset;
    private FilteredList<PalveluVaraus> palveluvarauksetFiltered;
    private SortedList<PalveluVaraus> palveluvarauksetSorted;

    
   
    public void paivitaNakyma(Toimipiste selectedObject, Object object, TableView tv) throws SQLException {
        
        if (object instanceof Palvelu) {
            palvelut = new PalveluDao().listByToimipisteId(selectedObject.getToimipisteId());
            palvelutFiltered = new FilteredList<>(palvelut, p -> true);
            palvelutSorted = new SortedList<>(palvelutFiltered);
            palvelutSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(palvelutSorted);
            
        }
              
        if (object instanceof PalveluVaraus) {
            palveluvaraukset = new PalveluVarausDao().listByToimipisteId(selectedObject.getToimipisteId());
            palveluvarauksetFiltered = new FilteredList<>(palveluvaraukset, p -> true);
            palveluvarauksetSorted = new SortedList<>(palveluvarauksetFiltered);
            palveluvarauksetSorted.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(palveluvarauksetSorted);
            
        }

    }
    
    public void suodataNakyma(Object object, TableView tv, ComboBox<String> toimipisteComboBox, ComboBox<String> palvelutyyppiComboBox, String hakusana) {
        
        if (object instanceof Palvelu) {
            String toimipiste = null;
            if (toimipisteComboBox != null) {
                toimipiste = toimipisteComboBox.getSelectionModel().getSelectedItem();
            } 
            
            new MainFXMLSearchFilters().palveluFilter(getPalvelutFiltered(), toimipiste, palvelutyyppiComboBox.getSelectionModel().getSelectedItem(), hakusana);
            tv.setItems(palvelutSorted);
            
        }
        
        if (object instanceof PalveluVaraus) {
           
            tv.setItems(palveluvarauksetSorted);
            
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
        
        String toimipiste = null;
        if (toimipisteComboBox != null) {
            toimipiste = toimipisteComboBox.getSelectionModel().getSelectedItem();
        } 
        
        if (object instanceof PalveluVaraus) {

            new MainFXMLSearchFilters().palveluVarausHakuFilter(getPalveluvarauksetFiltered(), toimipiste, palvelutyyppiComboBox.getSelectionModel().getSelectedItem(), mista, mihin, hakusana);
            tv.setItems(palveluvarauksetSorted);
            
        }
        
    }



    public FilteredList<Palvelu> getPalvelutFiltered() {
        return palvelutFiltered;
    }


    public FilteredList<PalveluVaraus> getPalveluvarauksetFiltered() {
        return palveluvarauksetFiltered;
    }


    public FilteredList<Palvelu> getPalveluFilteredData() {
        return palvelutFiltered;
    }

    public FilteredList<PalveluVaraus> getPvFilteredData() {
        return palveluvarauksetFiltered;
    }

}

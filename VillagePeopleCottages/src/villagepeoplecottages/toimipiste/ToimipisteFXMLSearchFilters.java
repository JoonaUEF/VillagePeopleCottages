/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.toimipiste;

import javafx.collections.transformation.FilteredList;
import villagepeoplecottages.MainFXMLSearchFilters;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;

/**
 *
 * @author User
 */
public class ToimipisteFXMLSearchFilters extends MainFXMLSearchFilters {

    
    public void palveluHakuFilter(FilteredList<Palvelu> palveluFilteredData, String newValue) {
        palveluFilteredData.setPredicate(palvelu -> {
                
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            // Suodattimen ehdot
            if (palvelu.getKuvaus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palvelu.getNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palvelu.getTyyppiString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
    }

    public void palveluVarausHakuFilter(FilteredList<PalveluVaraus> palveluVarausFilteredData, String newValue) {
        palveluVarausFilteredData.setPredicate(palvelu -> {
                
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            // Suodattimen ehdot
            if (palvelu.getPalveluTyyppiString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palvelu.getPalvelunNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } 
            
            return false;
        });
    }

    public void palveluTyyppiComboBoxFilter(FilteredList<Palvelu> palveluFilteredData, String newValue) {
        palveluFilteredData.setPredicate(palvelu -> {
             
            if (newValue.isEmpty() || newValue.equals(null)) {
                return true;
            }
            
            
            // Suodattimen ehdot
            if (palvelu.getTyyppiString().equals(newValue)) {
                return true;
            } 

            return false;
        });
    }
    
    public void palveluVarausTyyppiComboBoxFilter(FilteredList<PalveluVaraus> palveluFilteredData, String newValue) {
        palveluFilteredData.setPredicate(palvelu -> {
                
            if (newValue.isEmpty() || newValue.equals(null)) {
                return true;
            }
            
            
            // Suodattimen ehdot
            if (palvelu.getPalveluTyyppiString().equals(newValue)) {
                return true;
            } 

            return false;
        });
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.toimipiste;

import javafx.collections.transformation.FilteredList;
import villagepeoplecottages.ListenerMethods;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;

/**
 *
 * @author User
 */
public class ToimipisteFXMLListeners extends ListenerMethods {

    @Override
    public void palveluHakuListener(FilteredList<Palvelu> palveluFilteredData, String newValue) {
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

    public void palveluVarausHakuListener(FilteredList<PalveluVaraus> palveluVarausFilteredData, String newValue) {
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

    public void palveluTyyppiComboBox(FilteredList<Palvelu> palveluFilteredData, String newValue) {
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
    
    public void palveluVarausTyyppiComboBox(FilteredList<PalveluVaraus> palveluFilteredData, String newValue) {
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

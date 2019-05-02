/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.toimipiste;

import javafx.collections.transformation.FilteredList;
import villagepeoplecottages.ListenerMethods;
import villagepeoplecottages.palvelu.Palvelu;

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
    
    
}

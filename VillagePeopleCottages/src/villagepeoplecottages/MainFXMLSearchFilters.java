package villagepeoplecottages;

import javafx.collections.transformation.FilteredList;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.toimipiste.Toimipiste;

/**
 * Kuuntelijoiden toiminnot ohjelmiston käyttöön
 * 
 */

/**
 *
 * @author Lassi Puurunen
 */
public class MainFXMLSearchFilters {
    
    public void toimipisteHakuFilter(FilteredList<Toimipiste> toimipisteFilteredData, String newValue) {
        toimipisteFilteredData.setPredicate(toimipiste -> {
                
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            // Suodattimen ehdot
            if (toimipiste.getNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (toimipiste.getLahiosoite().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (toimipiste.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (toimipiste.getPostinro().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (toimipiste.getPostitoimipaikka().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (toimipiste.getPuhelinnro().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
    }

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
            } else if (palvelu.getToimipisteNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palvelu.getTyyppiString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
    }
}

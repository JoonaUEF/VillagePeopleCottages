package villagepeoplecottages;

import javafx.collections.transformation.FilteredList;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
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
    
    public void toimipisteHakuFilter(FilteredList<Toimipiste> toimipisteFiltered, String newValue) {
        toimipisteFiltered.setPredicate(toimipiste -> {
                
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

    
    public void palveluFilter(FilteredList<Palvelu> palveluFiltered, String toimipiste, String palvelutyyppi, String hakusana) {
         palveluFiltered.setPredicate(palvelu -> {
             
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if ((hakusana == null || hakusana.isEmpty()) && (toimipiste == null || toimipiste.isEmpty()) && (palvelutyyppi == null || palvelutyyppi.isEmpty()))  {
                return true;
            }
            
            String lowerCaseFilter = "";
            if (hakusana != null) {
                lowerCaseFilter = hakusana.toLowerCase();
                
            }
            
            // Tarkistetaan onko valittu toimipiste oikein
            boolean toimipisteoikein = false;
            
            if (toimipiste == null || toimipiste.isEmpty() || toimipiste.equals("Kaikki toimipisteet") || toimipiste.equals(palvelu.getToimipisteNimi())) {
                toimipisteoikein = true;
            }
                        
            //tarkistetaan onko valittu palvelun tyyppi oikein
            boolean tyyppiOikein = false;
            
            if (palvelutyyppi == null || palvelutyyppi.isEmpty() || palvelutyyppi.equals(new Palvelu().getTyypit().get(0)) || palvelu.getTyyppiString().equals(palvelutyyppi)) {
                tyyppiOikein = true;
            }
            
            
            // Mikäli palvelun valittu toimipiste ja tyyppi ovat oikein mennään hakusana suodatukseen
            if (toimipisteoikein && tyyppiOikein) {
                if (hakusana == null || hakusana.isEmpty()) {
                    return true;
                }
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
                
            }

           return false;


        });
    }

    public void asiakasHakuFilter(FilteredList<Asiakas> asiakkaatFiltered, String newValue) {
        asiakkaatFiltered.setPredicate(asiakas -> {
                
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            // Suodattimen ehdot
            if (asiakas.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (asiakas.getEtunimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (asiakas.getLahiosoite().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (asiakas.getPostinro().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (asiakas.getPuhelinnro().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (asiakas.getSukunimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            return false;
        });
    }

    public void palveluVarausHakuFilter(FilteredList<PalveluVaraus> palveluvarauksetFiltered, String newValue) {
        palveluvarauksetFiltered.setPredicate(palveluvaraus -> {
                
            // Jos suodattimessa ei ole mitään, palautetaan kaikki;
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            // Suodattimen ehdot
            if (palveluvaraus.getPalveluTyyppiString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palveluvaraus.getPalvelunNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (palveluvaraus.getToimipiste().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } 

            return false;
        });
    }
    
    

}

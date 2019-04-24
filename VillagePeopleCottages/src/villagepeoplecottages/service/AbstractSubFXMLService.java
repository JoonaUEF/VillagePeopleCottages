package villagepeoplecottages.service;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.lasku.LaskuDao;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palveluvaraus.PalveluVarausDao;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.varaus.VarausDao;

/**
 * Abstrakti luokka entiteettien näkymien käyttöön
 * 
 *
 * @author Lassi Puurunen
 * 
 * 24.4.2019
 * 
 */
public abstract class AbstractSubFXMLService extends AbstractMainFXMLService {
    
    
    //Yleiset toiminnot Entiteetteihin liittyville näkymille
    
    public void tallennaButton(Object selected, Object uusi) throws SQLException {
        
        // Tekee uuden rivin tietokantaan, jos vanha on tyhjä.
        // Muutoin päivittää selected-objektin tietoja
        if (selected == null) {
            teeUusiObjekti(uusi);
        }
        
        else {
            tallennaObjekti(selected, uusi);
        }
      
    }
    
    
    @Override
    public void lisaaUusiButton(Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        //ladataan syötetyn objektin mukainen FXML-tiedosto
        FXMLLoader fxmlLoader = lataaObjektilleFxml(object);
        
        //Asetukset stagelle
        Stage stage = setAndGetStage(object, fxmlLoader, mainPane);
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        stage.setTitle(uusiObjectTitleString(object));
        
        sendObjectToController(object, fxmlLoader);
        
        //Näytetään stage ja laitetaan nykyinen säie odotustilaan
        stage.showAndWait();
        
        //Aktivoidaan mainPane stagen sulkeuduttua
        mainPane.setDisable(false);
        
        //Päivitetään objektin mukainen näkymä
        paivitaNakyma(object, tv);    

      }
      
    private void teeUusiObjekti(Object object) throws SQLException {
        
        if (object instanceof Toimipiste)
            new ToimipisteDao().create((Toimipiste) object);
        
        if (object instanceof Asiakas)
            new AsiakasDao().create((Asiakas) object);
        
        if (object instanceof Lasku)
            new LaskuDao().create((Lasku) object);
        
        if (object instanceof Palvelu)
            new PalveluDao().create((Palvelu) object);
        
        if (object instanceof PalveluVaraus)
            new PalveluVarausDao().create((PalveluVaraus) object);
        
        if (object instanceof Varaus)
            new VarausDao().create((Varaus) object);
    
    }

    private void tallennaObjekti(Object object, Object uusi) throws SQLException {
        
        if (object instanceof Toimipiste) {
            
            Toimipiste paivitettava = new ToimipisteDao().read(((Toimipiste) object).getToimipisteId());
            Toimipiste uudet = (Toimipiste) uusi;
            
            paivitettava.setNimi(uudet.getNimi());
            paivitettava.setLahiosoite(uudet.getLahiosoite());
            paivitettava.setPostinro(uudet.getPostinro());
            paivitettava.setPostitoimipaikka(uudet.getPostitoimipaikka());
            paivitettava.setPuhelinnro(uudet.getPuhelinnro());
            paivitettava.setEmail(uudet.getEmail());
            
            new ToimipisteDao().update(paivitettava);
            
        }
            
        
        if (object instanceof Asiakas) {
            
        }
            
        
        if (object instanceof Lasku) {
            
        }
            
        
        if (object instanceof Palvelu) {
            
        }
            
        
        if (object instanceof PalveluVaraus) {
            
        }
            
        
        if (object instanceof Varaus) {
            
        }
            
            
    }

    /**
     * Ladataan objektin luokan mukainen FXML
     *
     * 
     * 24.4.2019 Lassi Puurunen
     * 
     * @param object
     * @return 
     */
    
    @Override
    public FXMLLoader lataaObjektilleFxml(Object object) {
        FXMLLoader fxmlLoader = null;
            
        if (object instanceof Toimipiste) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("../toimipiste/ToimipisteFXML.fxml"));
        }
        
        else if (object instanceof Palvelu) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("../palvelu/PalveluFXML.fxml"));
        }
        else if (object instanceof Asiakas) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("../asiakas/AsiakasFXML.fxml"));
        }
        
        else if (object instanceof Lasku) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("../lasku/LaskuFXML.fxml"));
        }
        
        else if (object instanceof PalveluVaraus) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("../palveluvaraus/PalveluVarausFXML.fxml"));
        }
        
        else if (object instanceof Varaus) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("../varaus/VarausFXML.fxml"));
        }
        
        return fxmlLoader;
        
    }
}

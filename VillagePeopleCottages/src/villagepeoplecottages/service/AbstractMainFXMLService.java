package villagepeoplecottages.service;


import villagepeoplecottages.lasku.LaskuDao;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.asiakas.AsiakasFXMLController;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.lasku.LaskuFXMLController;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.palvelu.PalveluFXMLController;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palveluvaraus.PalveluVarausFXMLController;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.toimipiste.ToimipisteFXMLController;
import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.varaus.VarausDao;
import villagepeoplecottages.varaus.VarausFXMLController;

/**
 * Abstrakti-luokka sisältää yhteiset toiminnallisuudet FXML-service luokille.
 * AbstractMainFXMLService-luokkaan toteutetaan näkymien toiminnallisuudet.
 *
 * AbstractSubFXMLService on entiteetteihin liittyvien FXMLService-luokkien abstraksio.
 
 * 
 * 
 
 ****TODO Haku- ja rajaustoiminnallisuudet*****
 
 
 * 
 * @author Lassi Puurunen
 * 
 * 24.4.2019 v1 Lassi Puurunen
 */
public abstract class AbstractMainFXMLService {
    
    
    /**
     * Lisää uusi - napin toiminto
     * 
     * 
     * 18.4.2019 Lassi Puurunen
     * 24.4.2019 purettu useampaan metodiin
     * 
     * @param object syötetään uusi halutun luokan objekti.
     * @param tv TableView, johon uusi objekti tulee
     * 
     */      
    public void lisaaUusiButton(Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        //ladataan syötetyn objektin mukainen FXML-tiedosto
        FXMLLoader fxmlLoader = this.lataaObjektilleFxml(object);
        
        //Asetukset stagelle
        Stage stage = setAndGetStage(object, fxmlLoader, mainPane);
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        stage.setTitle(uusiObjectTitleString(object));
        
        //Näytetään stage ja laitetaan nykyinen säie odotustilaan
        stage.showAndWait();
        
        //Aktivoidaan mainPane stagen sulkeuduttua
        mainPane.setDisable(false);
        
        
    }
    
    /**
     * Muokkaa - napin toiminto
     * 
     * 
     * 18.4.2019 Lassi Puurunen
     * 24.4.2019 Purettu käyttämään yhteisiä metodeja
     * 
     * @param event 
     */    
    public void muokkaaButton(TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        // Haetaan tableviewissa valittu objekti.
        Object object = tv.getSelectionModel().getSelectedItem();
        
        //ladataan syötetyn objektin mukainen FXML-tiedosto
        FXMLLoader fxmlLoader = this.lataaObjektilleFxml(object);
        
        //Asetukset stagelle
        Stage stage = setAndGetStage(object, fxmlLoader, mainPane);
        
        //Lähetä muokattava objekti uudem näkymän Controllerille
        //Lähetään objekti controllerille
        sendObjectToController(object, fxmlLoader);  
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        stage.setTitle(muokkaaObjectTitleString(object));
        
        
        
        //Näytetään stage ja laitetaan nykyinen säie odotustilaan
        stage.showAndWait();
        
        //Aktivoidaan mainPane stagen sulkeuduttua
        mainPane.setDisable(false);
        
//        //Päivitetään objektin mukainen näkymä
//        paivitaNakyma(object, tv);
        
    }

    
    
    /**
     * Poista-napin toiminto
     * 
     * Metodi poistaa tietokannasta TableViewissä valitun rivin
     * 
     * 16.4.2019 Lassi Puurunen
     * 
     * 
     * @param tableview
     */
    
    public void poistaButton(TableView tv) throws SQLException {
        
        // Haetaan tableviewissa valittu objekti.
        Object object = tv.getSelectionModel().getSelectedItem();
        
        delete(object);
        
//        paivitaNakyma(object, tv);
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
    public FXMLLoader lataaObjektilleFxml(Object object) {
            
        FXMLLoader fxmlLoader = null;
            
        if (object instanceof Toimipiste) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("toimipiste/ToimipisteFXML.fxml"));
        }
        
        else if (object instanceof Palvelu) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("palvelu/PalveluFXML.fxml"));
        }
        else if (object instanceof Asiakas) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("asiakas/AsiakasFXML.fxml"));
        }
        
        else if (object instanceof Lasku) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("lasku/LaskuFXML.fxml"));
        }
        
        else if (object instanceof PalveluVaraus) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("palveluvaraus/PalveluVarausFXML.fxml"));
        }
        
        else if (object instanceof Varaus) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("varaus/VarausFXML.fxml"));
        }
        
        return fxmlLoader;
        
    }
    
    
    
    
    // Asetetaan Stageen liittyvät asetukset
    public Stage setAndGetStage(Object object, FXMLLoader fxmlLoader, AnchorPane mainPane) throws IOException {
        //Asetetaan parent ja stage
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
                  
        //Muita asetuksia
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

        stage.initStyle(StageStyle.UTILITY);

        //Laitetaan päänäkymä pois toiminnosta lisäyksen ajaksi
        mainPane.setDisable(true);
        
        return stage;
    }
    
    //Ladataan objektin luokan mukainen nimi ikkunalle
    public String uusiObjectTitleString(Object object) {
        
        
        if (object instanceof Toimipiste) {
            
            return "Lisää toimipiste";
        }
        
        else if (object instanceof Palvelu) {
          
            return "Lisää palvelu";
        }
        else if (object instanceof Asiakas) {
            
            return "Lisää asiakas";
        }
        
        else if (object instanceof Lasku) {
          
            return "Lisää lasku";
        }
        
        else if (object instanceof PalveluVaraus) {
            
            return "Lisää palveluvaraus";
        }
        
        else if (object instanceof Varaus) {
            
            return "Lisää varaus";
        }
        
        return null;
    }
    
    public String muokkaaObjectTitleString(Object object) {
        if (object instanceof Toimipiste) {
            
            return "Muokkaa toimipistettä";
        }
        
        else if (object instanceof Palvelu) {
          
            return "Muokkaa palvelua";
        }
        else if (object instanceof Asiakas) {
            
            return "Muokkaa asiakasta";
        }
        
        else if (object instanceof Lasku) {
          
            return "Muokkaa laskua";
        }
        
        else if (object instanceof PalveluVaraus) {
            
            return "Muokkaa palveluvarausta";
        }
        
        else if (object instanceof Varaus) {
            
            return "Muokkaa varausta";
        }
        
        return null;
    }

    //Päivitetään objektin mukainen näkymä
//    public void paivitaNakyma(Object object, TableView tv) throws SQLException {
//        
//        if (object instanceof Toimipiste) {
// 
//            mtc.initializeTable(object, tv);
//        }
//        
//        else if (object instanceof Palvelu) {
//          
//            tv.setItems(new PalveluDao().list());
//        }
//        
//        else if (object instanceof Asiakas) {
//            
//            tv.setItems(new AsiakasDao().list());
//        }
//        
//        else if (object instanceof Lasku) {
//          
//            tv.setItems(new LaskuDao().list());
//        }
//        
//        else if (object instanceof PalveluVaraus) {
//            
//            tv.setItems(new PalveluVarausDao().list());
//        }
//        
//        else if (object instanceof Varaus) {
//            
//            tv.setItems(new VarausDao().list());
//        }
//    }

    // Poistetaan tietokannasta objektin mukainen tietue.
    public void delete(Object object) throws SQLException {
        
        
        if (object instanceof Toimipiste) {
            
            new ToimipisteDao().delete(((Toimipiste) object).getToimipisteId());
        }
        
        else if (object instanceof Palvelu) {
          
            new PalveluDao().delete(((Palvelu) object).getPalveluId());
          
        }
        
        else if (object instanceof Asiakas) {
            
            new AsiakasDao().delete(((Asiakas) object).getAsiakasId());
            
        }
        
        else if (object instanceof Lasku) {
            
            new LaskuDao().delete(((Lasku) object).getLaskuId());
           
        }
        
        else if (object instanceof PalveluVaraus) {
            
//            new PalveluVarausDao().delete();
            
        }
        
        else if (object instanceof Varaus) {
            
            new VarausDao().delete(((Varaus) object).getVarausId());
        }
    }

    public void sendObjectToController(Object object, FXMLLoader fxmlLoader) {
            
        if (object instanceof Toimipiste) {
            
            ToimipisteFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
            
        }
        
        else if (object instanceof Palvelu) {
          
            PalveluFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
          
        }
        
        else if (object instanceof Asiakas) {
            
            AsiakasFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
            
        }
        
        else if (object instanceof Lasku) {
            
            LaskuFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
           
        }
        
        else if (object instanceof PalveluVaraus) {
            
            PalveluVarausFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
            
        }
        
        else if (object instanceof Varaus) {
            
            VarausFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
            
        }
    }


    
}

package villagepeoplecottages;

import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.toimipiste.ToimipisteFXMLController;
import villagepeoplecottages.palvelu.PalveluFXMLController;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.asiakas.AsiakasFXMLController;
import villagepeoplecottages.asiakas.Asiakas;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * MainFXMLService sisältää metodit MainFXMLController:le
 * 
 * 
 * 
 * 18.4. Lassi Puurunen
 * 
 */

public class MainFXMLService {

       
    /**
     * Lisää uusi - napin toiminto
     * 
     * 
     * 18.4. 2019 Lassi Puurunen
     * 
     * @param event 
     */
    
    public void lisaaUusiButton(Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        FXMLLoader fxmlLoader = null;
        
        // Ladataan objektin luokan mukainen FXML
        //
        // TODO: muille luokille
        
        if (object instanceof Toimipiste) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("ToimipisteFXML.fxml"));
        }
        
        else if (object instanceof Palvelu) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("PalveluFXML.fxml"));
        }
        else if (object instanceof Asiakas) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("AsiakasFXML.fxml"));
          }
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
                
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        //
        // TODO: muille luokille
        
        if (object instanceof Toimipiste) {
            
            stage.setTitle("Lisää toimipiste");
        }
        else if (object instanceof Palvelu) {
          
          stage.setTitle("Lisää palvelu");
        }
        else if (object instanceof Asiakas) {
            
            stage.setTitle("Lisää asiakas");
          }
        
        //Muita asetuksia        
        stage.setScene(new Scene(root1));
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

        stage.initStyle(StageStyle.UTILITY);

        //Laitetaan päänäkymä pois toiminnosta lisäyksen ajaksi
        mainPane.setDisable(true);
        
        //Seuraava komento laittaa säikeen odotustilaan, kunnes lisäysikkuna suljetaan
        stage.showAndWait();
        
        mainPane.setDisable(false);
        
        
        //Päivitetään objektin mukainen näkymä
        //
        // TODO: muille luokille
        if (object instanceof Toimipiste) {
 
            tv.setItems(new ToimipisteDao().list());
        }
        
        else if (object instanceof Palvelu) {
          
          tv.setItems(new PalveluDao().list());
        }
        else if (object instanceof Asiakas) {
            
            tv.setItems(new AsiakasDao().list());
        }
    }
    
    
    
    /**
     * Muokkaa - napin toiminto
     * 
     * 
     * 18.4. 2019 Lassi Puurunen
     * 
     * @param event 
     */
    
    public void muokkaaButton(Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        
        FXMLLoader fxmlLoader = null;
        
        // Ladataan objektin luokan mukainen FXML
        //
        // TODO: muille luokille
        
        if (object instanceof Toimipiste) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("ToimipisteFXML.fxml"));
        }
        
        else if (object instanceof Palvelu) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("PalveluFXML.fxml"));
        }
        
        else if (object instanceof Asiakas) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("AsiakasFXML.fxml"));
          }
        
        Parent root1 = (Parent) fxmlLoader.load();
        
        // Ladataan objektin luokan mukainen nimi stagelle
        //
        // Lähetetään objekti Controllerille
        //
        // TODO: muille luokille
        
        Stage stage = new Stage();
        
        if (object instanceof Toimipiste) {

            stage.setTitle("Muokkaa toimipistettä");
            
            //Lähetään objekti controllerille
            ToimipisteFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
   
        }
        
        else if (object instanceof Palvelu) {

          stage.setTitle("Muokkaa palvelua");
          
          //Lähetään objekti controllerille
          PalveluFXMLController controller = fxmlLoader.getController();
          controller.initData(object);
          
        }
        
        else if (object instanceof Asiakas) {

            stage.setTitle("Muokkaa asiakasta");
            
            //Lähetään objekti controllerille
            AsiakasFXMLController controller = fxmlLoader.getController();
            controller.initData(object);
            
          }

        //Muita asetuksia   
        
        stage.setScene(new Scene(root1));
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

        stage.initStyle(StageStyle.UTILITY);

        //Laitetaan päänäkymä pois toiminnosta lisäyksen ajaksi
        mainPane.setDisable(true);
        
        
        //Seuraava komento laittaa säikeen odotustilaan, kunnes lisäysikkuna suljetaan
        stage.showAndWait();
        
        mainPane.setDisable(false);
        
        
        //Päivitetään muokkaus-ikkunasta poistuttaessa objektin mukainen näkymä
        //
        // TODO: muille luokille
        if (object instanceof Toimipiste) {
            
            tv.setItems(new ToimipisteDao().list());
        }
        
        else if (object instanceof Palvelu) {
          
          tv.setItems(new PalveluDao().list());
        }
        else if (object instanceof Asiakas) {
            
            tv.setItems(new AsiakasDao().list());
          }
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
        
        // Poistetaan tietokannasta objektin mukainen tietue.
        //
        // Päivitetään näkymä.
        //
        // TODO: muille luokille
        if (object instanceof Toimipiste) {
            
            new ToimipisteDao().delete(((Toimipiste) object).getToimipisteId());
            tv.setItems(new ToimipisteDao().list());
        }
        
        if (object instanceof Palvelu) {
          
          new PalveluDao().delete(((Palvelu) object).getPalveluId());
          tv.setItems(new PalveluDao().list());
        }
        if (object instanceof Asiakas) {
            
            new AsiakasDao().delete(((Asiakas) object).getAsiakasId());
            tv.setItems(new AsiakasDao().list());
          }
     
    }
    
    
    
}

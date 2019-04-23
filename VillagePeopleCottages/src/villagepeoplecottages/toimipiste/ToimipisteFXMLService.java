package villagepeoplecottages.toimipiste;

import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palvelu.PalveluFXMLController;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * ToimipisteFXMLService
 * 
 * Toimintoja ToimipisteFXMLController:lle
 * 
 * 
 * 18.4.2019 Lassi Puurunen
 * 
 */



public class ToimipisteFXMLService {
    
    
    public void tallennaButton(Toimipiste vanha, Toimipiste uusi) throws SQLException {
        // Tekee uuden toimipisteen tietokantaan, jos vanha on tyhjä.
        // Muutoin päivittää objektin tietoja
        
        if (vanha == null) {
            new ToimipisteDao().create(uusi);
        } else {
            Toimipiste paivitettava = new ToimipisteDao().read(vanha.getToimipisteId());
            paivitettava.setNimi(uusi.getNimi());
            paivitettava.setLahiosoite(uusi.getLahiosoite());
            paivitettava.setPostinro(uusi.getPostinro());
            paivitettava.setPostitoimipaikka(uusi.getPostitoimipaikka());
            paivitettava.setPuhelinnro(uusi.getPuhelinnro());
            paivitettava.setEmail(uusi.getEmail());
            
            new ToimipisteDao().update(paivitettava);
        }
        
        
    }
    
    public void lisaaUusiButton(Toimipiste vanhaToimipiste, Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException {
        
        FXMLLoader fxmlLoader = null;
        
        // Ladataan objektin luokan mukainen FXML
        //
        // TODO: muille luokille
        
        if (object instanceof Palvelu) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("PalveluFXML.fxml"));
            

        }
        
        else if (object instanceof PalveluVaraus) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("VarausFXML.fxml"));
        }
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
                
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        //
        // TODO: muille luokille
        
        if (object instanceof Palvelu) {
            
            stage.setTitle("Lisää palvelu");
            
            //Lähetään objekti controllerille
            PalveluFXMLController controller = fxmlLoader.getController();
            controller.initData(vanhaToimipiste);
            
        }
        else if (object instanceof PalveluVaraus) {
          
          stage.setTitle("Lisää varaus");
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
        if (object instanceof PalveluVaraus) {
            
//            Daoa ei vielä toteutettu
//            tv.setItems(new PalveluVarausDao().list());
        }
        
        else if (object instanceof Palvelu) {
          
          tv.setItems(new PalveluDao().listByToimipisteId(vanhaToimipiste.getToimipisteId()));
      }
    }

    public void muokkaaButton(Toimipiste vanhaToimipiste, Object object, TableView tv, AnchorPane mainPane) throws SQLException, IOException{
        // Muokataan valittua objektia
        
        FXMLLoader fxmlLoader = null;
        
        // Ladataan objektin luokan mukainen FXML
        //
        // TODO: muille luokille
        
       if (object instanceof Palvelu) {
            
            fxmlLoader = new FXMLLoader(getClass().getResource("PalveluFXML.fxml"));
        }
        
        else if (object instanceof PalveluVaraus) {
          
          fxmlLoader = new FXMLLoader(getClass().getResource("VarausFXML.fxml"));
        }
        
        Parent root1 = (Parent) fxmlLoader.load();
        
        // Ladataan objektin luokan mukainen nimi stagelle
        //
        // Lähetetään objekti Controllerille
        //
        // TODO: muille luokille
        
        Stage stage = new Stage();
        
        if (object instanceof PalveluVaraus) {

            stage.setTitle("Muokkaa varausta");
            
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
        if (object instanceof PalveluVaraus) {
            
            //tv.setItems(new PalveluVarausDao().list());
        }
        
        else if (object instanceof Palvelu) {
          
          tv.setItems(new PalveluDao().listByToimipisteId(vanhaToimipiste.getToimipisteId()));
        }
    }


}

package villagepeoplecottages;

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
        
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
                
        
        //Ladataan objektin luokan mukainen nimi ikkunalle
        //
        // TODO: muille luokille
        
        if (object instanceof Toimipiste) {
            
            stage.setTitle("Lisää toimipiste");
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
            
            new ToimipisteDao().delete(((Toimipiste) object).getToimipisteId());
            tv.setItems(new ToimipisteDao().list());
        }
        
        
        tv.refresh();
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
        
        // Poistetaan tietokannasta objektin mukainen tietue
        //
        // TODO: muille luokille
        if (object instanceof Toimipiste) {
            
            new ToimipisteDao().delete(((Toimipiste) object).getToimipisteId());
            tv.setItems(new ToimipisteDao().list());
        }
        
        tv.refresh();
     
    }
    
    
    
}

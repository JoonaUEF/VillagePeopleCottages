package villagepeoplecottages;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Lassi Puurunen
 * 
 * Versiohistoria
 * 
 * 15.4.2019 Tiedosto luotu. Lassi Puurunen
 * 15.4.2019 Toimipisteen TableView-tietokantayhteys toteutettu. Lassi Puurunen
 * 16.4.2019 Toimipisteen lisäys-toiminto lisätty. Lassi Puurunen
 * 16.4.2019 Toimipisteen poisto-toiminto lisätty, poisto ja muokkaus -nappien aktivointi ja deaktivointi. Lassi Puurunen
 * 
 * 
 */

public class MainFXMLController implements Initializable {

    // Määritetään MainFXMLService käyttöön
    private MainFXMLService mfxmls = new MainFXMLService();
        
    // Mainpanen avulla voidaan päänäkymä aktivoida tai deaktivoida muita ikkunoita käsitellessä
    @FXML private AnchorPane mainPane;
    
    // Määritetään toimipistenäkymän tiedot
    @FXML private TableView<Toimipiste> toimipisteetTableView;
    @FXML private TableColumn<Toimipiste, String> toimipisteNimiColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteLahiosoiteColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostinroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostitoimipaikkaColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePuhelinnumeroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteEmailColumn;
    @FXML private Button toimipisteLisaaUusiButton;
    @FXML private Button toimipisteMuokkaaButton;
    @FXML private Button toimipistePoistaButton;

    
    
    

    
    /**
     * Initializes the controller class.
     * 
     * 
     * 16.4.2019 Lassi Puurunen
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Luodaan kuuntelija, joka aktivoi tai deaktivoi muokkaus ja poistonapit, kun taulusta valitaan rivi
        //
        // TODO kaikille päänäkymän tauluille ja napeille
        toimipisteetTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                toimipisteMuokkaaButton.setDisable(false);
                toimipistePoistaButton.setDisable(false);
            }
            if (newSelection == null) {
                toimipisteMuokkaaButton.setDisable(true);
                toimipistePoistaButton.setDisable(true);
            }
        });
        
        
        // Tehdään toimipiste-näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.
        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("nimi"));
        toimipisteLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("lahiosoite"));
        toimipistePostinroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postinro"));
        toimipistePostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postitoimipaikka"));
        toimipistePuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("puhelinnro"));
        toimipisteEmailColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("email"));
        
        // TODO Tehdään sama muille näkymille
        
    }    
    
     
    
    
    
    /**
     * Toimipiste-näkymän metodit alkavat tästä
     * 
     */
    
    
    /**
     * Siirryttäessä toimipiste-välilehdelle, ladataan tietokannasta toimipisteiden tiedot
     * 
     * 
     * 15.4. 2019 Lassi Puurunen
     * 
     * @param event
     * @throws SQLException 
     */
    
    @FXML
    private void toimipisteOnSelectionChanged(Event event) {

        try {
            toimipisteetTableView.setItems(new ToimipisteDao().list());
            toimipisteetTableView.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    /**
     * Toimipistenäkymän
     * Lisää uusi - napin toiminto
     * 
     * 
     * 16.4. 2019 Lassi Puurunen
     * 18.4. 2019 Päivitetty käyttämään Service-luokkaa
     * 
     * @param event 
     */
    
    @FXML
    private void toimipisteLisaaUusiButtonOnAction(ActionEvent event) {

        try {
            mfxmls.lisaaUusiButton(new Toimipiste(), toimipisteetTableView, mainPane);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    /**
     * Toimipistenäkymän
     * Muokkaa-napin toiminto
     * 
     * 
     * 18.4. Lassi Puurunen
     * 
     * @param event 
     */
    
    @FXML
    private void toimipisteMuokkaaButtonOnAction(ActionEvent event) {
        
    }
    


    
    /**
     * Toimipisteen näkymän
     * Poista-napin toiminto
     * 
     * 
     * 16.4.2019 Lassi Puurunen
     * 
     * @param event 
     */
    
    @FXML
    private void toimipistePoistaButtonOnAction(ActionEvent event) {
   
        try {
            mfxmls.poistaButton(toimipisteetTableView);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

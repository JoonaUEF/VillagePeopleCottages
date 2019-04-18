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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


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
 * 18.4.2019 Päivitetty käyttämään MainFXMLService -luokkaa. Lassi Puurunen
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
        
        // Tehdään kuuntelijat
        this.listeners();
        
        // Tehdään PropertyValueFactory:t, joiden avulla oliot yhdistetään
        // tableView:hin
        this.propertyValueFactories();
        
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

        // Haetaan näkymään tiedot tietokannasta
        
        try {
            toimipisteetTableView.setItems(new ToimipisteDao().list());
            
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

        // Lisätään uusi toimipiste
        
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
        
        // Muokataan valittua toimipistettä
        
        try {
            mfxmls.muokkaaButton(toimipisteetTableView.getSelectionModel().getSelectedItem(), toimipisteetTableView, mainPane);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
   
        // Poistetaan valittu toimipiste
        
        try {
            mfxmls.poistaButton(toimipisteetTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    // Yleismetodeja
    
    
    /**
     * Täällä käynnistetään kuuntelijat
     * 
     * 18.4.2019 Lassi Puurunen
     */
    
    private void listeners() {
        
        // Luodaan kuuntelija, joka aktivoi tai deaktivoi muokkaus ja poistonapit, 
        // kun taulusta valitaan rivi
        //
        // TODO kaikille päänäkymän tauluille
        
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
        
        //TODO haku- ja rajaustoimintojen kuuntelijat
        
    }

    private void propertyValueFactories() {
        
        // Tehdään toimipiste-näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.
        
        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("nimi"));
        toimipisteLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("lahiosoite"));
        toimipistePostinroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postinro"));
        toimipistePostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postitoimipaikka"));
        toimipistePuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("puhelinnro"));
        toimipisteEmailColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("email"));
        
        // TODO Tehdään sama muille näkymille
    }

    
}

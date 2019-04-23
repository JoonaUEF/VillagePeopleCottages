package villagepeoplecottages.toimipiste;

import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palvelu.Palvelu;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import villagepeoplecottages.MainFXMLController;

/**
 * FXML Controller class
 *
 * 18.4.2019 Lassi Puurunen
 * 23.4.2019 Lisätty alavalikko ja taulukot. Lassi Puurunen
 */
public class ToimipisteFXMLController implements Initializable {

    // Ladataan Service käyttöön
    private ToimipisteFXMLService tfxmls = new ToimipisteFXMLService();
    
    // Controllerille tuleva olio initData:ssa
    private Toimipiste muokattavaToimipiste;

    
    //yläosion toiminnot
    @FXML private TextField nimiTextField;
    @FXML private TextField lahiosoiteTextField;
    @FXML private TextField postinumeroTextField;
    @FXML private TextField postitoimipaikkaTextField;
    @FXML private TextField puhelinnumeroTextField;
    @FXML private TextField emailTextField;
    @FXML private Button tallennaButton;
    @FXML private Button peruutaButton;
    
    
    // PalveluTabin toiminnot
    
    @FXML private AnchorPane palvelutVarauksetAnchorPane;
    @FXML private Button palveluMuokkaaButton;
    @FXML private Button palveluPoistaButton;
    @FXML private TableView<Palvelu> palvelutTableView;
    @FXML private TableColumn<Palvelu, String> palveluTyyppiColumn;
    @FXML private TableColumn<Palvelu, String> palveluNimiColumn;
    @FXML private TableColumn<Palvelu, String> palveluKuvausColumn;
    @FXML private TableColumn<Palvelu, Double> palveluHintaColumn;
    @FXML private TableColumn<Palvelu, Double> palveluAlvColumn;
    @FXML private TableColumn<Palvelu, Double> palveluHintaAlvColumn;
    
    
    //VarausTabin toiminnot
    
    @FXML private Tab varauksetTabOnSelectionChanged;
    @FXML private TableView<PalveluVaraus> varauksetTableView;
    @FXML private TableColumn<PalveluVaraus, Integer> varausVarausIdColumn;
    @FXML private TableColumn<PalveluVaraus, String> varausPalvelutyyppiColumn;
    @FXML private TableColumn<PalveluVaraus, String> varausPalvelunNimiColumn;
    @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunAlkuColumn;
    @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunLoppuColumn;
    @FXML private TableColumn<PalveluVaraus, Date> varausVarattuColumn;
    @FXML private TableColumn<PalveluVaraus, Date> varausVahvistettuColumn;
    @FXML private Button varausMuokkaaButton;
    @FXML private Button varausPoistaButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button palveluLisaaUusiButton;

    
     /** 
      * Initializes the controller class.
      * 
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
     * initData
     * 
     * Metodin avulla voidaan ottaa vastaan muokattava objekti.
     * 
     * 18.4. Lassi Puurunen
     * 
     * @param object 
     */
    public void initData(Object object) {
        this.muokattavaToimipiste = (Toimipiste)object;
        nimiTextField.textProperty().set(muokattavaToimipiste.getNimi());
        lahiosoiteTextField.textProperty().set(muokattavaToimipiste.getLahiosoite());
        postinumeroTextField.textProperty().set(muokattavaToimipiste.getPostinro());
        postitoimipaikkaTextField.textProperty().set(muokattavaToimipiste.getPostitoimipaikka());
        puhelinnumeroTextField.textProperty().set(muokattavaToimipiste.getPuhelinnro());
        emailTextField.textProperty().set(muokattavaToimipiste.getEmail());
        
        //Jos Controlleri saa syötteenä vanhan toimipisteen, aktivoidaan alapaneeli.
        //Lisäksi ladataan tiedot tauluihin
        
        if (!muokattavaToimipiste.getNimi().isEmpty()) {
            palvelutVarauksetAnchorPane.setDisable(false);
            
        }
        
        palvelutTableView.setItems(muokattavaToimipiste.getPalvelut());
        
    }
    
    
    
    /**
     * haeTietoLomakkeelta
     * 
     * Metodi palauttaa olion, jossa on lomakkeelle taytetyt tiedot
     * 
     * 18.4.2019 Lassi Puurunen
     * 
     * @return toimipiste
     */
    
    private Toimipiste haeTietoLomakkeelta() {
        return new Toimipiste(nimiTextField.getText(), 
                lahiosoiteTextField.getText(), postitoimipaikkaTextField.getText(), 
                postinumeroTextField.getText(), emailTextField.getText(), puhelinnumeroTextField.getText());
    }
    
    /**
     * tallennaButtonOnAction
     * 
     * Hakee tiedot oliolle lomakkeesta.
     * 
     * Syöttää lomakkeen tiedot, sekä mahdollisesti muokattavan olion Servicen 
     * tallennustoiminnolle
     * 
     * 
     * 18.4. Lassi Puurunen
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void tallennaButtonOnAction(ActionEvent event) throws SQLException {
        
        tfxmls.tallennaButton(muokattavaToimipiste, haeTietoLomakkeelta());
        
        //sulje ikkuna
        this.peruutaButtonOnAction(event);
    }
    
    
    
    /**
     * peruutaButtonOnAction
     * 
     * Sulkee ikkunan
     * 
     * 18.4. Lassi Puurunen
     * 
     * @param event 
     */
    @FXML
    private void peruutaButtonOnAction(ActionEvent event) {
        
        //sulkee ikkunan
        
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void palveluLisaaUusiButtonOnAction(ActionEvent event) {
        // Lisätään uusi palvelu toimipisteelle
        
        try {
            if (muokattavaToimipiste != null) {
                tfxmls.lisaaUusiButton(muokattavaToimipiste, new Palvelu(), palvelutTableView, mainPane);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void palveluMuokkaaButtonOnAction(ActionEvent event) {
        
        // Muokataan valittua palvelua
        
        try {
            tfxmls.muokkaaButton(muokattavaToimipiste, palvelutTableView.getSelectionModel().getSelectedItem(), palvelutTableView, mainPane);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void palveluPoistaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluTabOnSelectionChanged(Event event) {
 
    }



    private void listeners() {
        
        // Luodaan kuuntelija, joka aktivoi tai deaktivoi muokkaus ja poistonapit, 
        // kun taulusta valitaan rivi
        //
        // TODO kaikille päänäkymän tauluille
        
        varauksetTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                varausMuokkaaButton.setDisable(false);
                varausPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
                varausMuokkaaButton.setDisable(true);
                varausPoistaButton.setDisable(true);
            }
        });
        
        
        palvelutTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                palveluMuokkaaButton.setDisable(false);
                palveluPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
                palveluMuokkaaButton.setDisable(true);
                palveluPoistaButton.setDisable(true);
            }
        });
        
        //TODO haku- ja rajaustoimintojen kuuntelijat
        
    }

    private void propertyValueFactories() {
        
        // Tehdään näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.
        
        
        palveluNimiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("nimi"));
        palveluTyyppiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("tyyppiString"));
        palveluKuvausColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("kuvaus"));
        palveluHintaColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hinta"));
        palveluAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("alv"));
        palveluHintaAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hintaAlv"));

    }
    
}

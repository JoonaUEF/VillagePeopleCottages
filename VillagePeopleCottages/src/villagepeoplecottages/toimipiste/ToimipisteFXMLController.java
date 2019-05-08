package villagepeoplecottages.toimipiste;

import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palvelu.Palvelu;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import villagepeoplecottages.MainFXMLSearchFilters;
import villagepeoplecottages.MainFXMLController;
import villagepeoplecottages.interfaces.FXMLControllerInterface;

/**
 * FXML Controller class
 *
 * 18.4.2019 Lassi Puurunen
 * 23.4.2019 Lisätty alavalikko ja taulukot. Lassi Puurunen
 */
public class ToimipisteFXMLController implements Initializable, FXMLControllerInterface<Toimipiste> {

    // Ladataan Service käyttöön
    private ToimipisteFXMLService tfxmls = new ToimipisteFXMLService();
    
    // Controllerille tuleva olio initData:ssa
    private Toimipiste selectedToimipiste;
    
    //Mainpane ikkunan aktivointia ja deaktivointia varten
    @FXML private AnchorPane mainPane;
    
    //yläosion toiminnot
    @FXML private TextField nimiTextField;
    @FXML private TextField lahiosoiteTextField;
    @FXML private TextField postinumeroTextField;
    @FXML private TextField postitoimipaikkaTextField;
    @FXML private TextField puhelinnumeroTextField;
    @FXML private TextField emailTextField;
    
    @FXML private Button tallennaButton;
    @FXML private Button peruutaButton;
    
    
    //Alaosion ankkuripane
    @FXML private AnchorPane palvelutVarauksetAnchorPane;
    
    
    // PalveluTabin toiminnot 
        
    @FXML private TextField palveluHakuTextField;
    @FXML private ComboBox<String> palveluTyyppiComboBox;
    
    @FXML private Button palveluLisaaUusiButton;
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
    
    
    @FXML private TextField varausHakuTextField;
    @FXML private ComboBox<String> varausPalvelutyyppiComboBox;
    @FXML private DatePicker varausMistaDatePicker;
    @FXML private DatePicker varausMihinDatePicker;
    
    @FXML private Button varausLisaaUusiButton;
    @FXML private Button varausMuokkaaButton;
    @FXML private Button varausPoistaButton;
    
    @FXML private TableView<PalveluVaraus> varauksetTableView;
        @FXML private TableColumn<PalveluVaraus, Integer> varausIdColumn;
        @FXML private TableColumn<PalveluVaraus, Integer> varausAsiakasIdColumn;
        @FXML private TableColumn<PalveluVaraus, String> varausPalveluTyyppiColumn;

        @FXML private TableColumn<PalveluVaraus, String> varausPalvelunNimiColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunAlkuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunLoppuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausVarattuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausVahvistettuColumn;
    
    
    //tilapalkki
    @FXML private Label tilaPalkkiLabel;

   
     /** 
      * Initializes the controller class.
      * 
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // Tehdään PropertyValueFactory:t, joiden avulla oliot yhdistetään
        // tableView:hin
        this.propertyValueFactories();
        
        // Tehdään kuuntelijat
        this.listeners();  
        
        
        //Tuodaan palvelutyypit valikoihin
        ObservableList<String> palvelutyypit = FXCollections.observableArrayList();
        for (String string : new Palvelu().getTyypit()) {
            palvelutyypit.add(string);
        }
        
        palveluTyyppiComboBox.setItems(palvelutyypit);
        varausPalvelutyyppiComboBox.setItems(palvelutyypit);
        
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
        
        this.selectedToimipiste = (Toimipiste)object;
        nimiTextField.textProperty().set(selectedToimipiste.getNimi());
        lahiosoiteTextField.textProperty().set(selectedToimipiste.getLahiosoite());
        postinumeroTextField.textProperty().set(selectedToimipiste.getPostinro());
        postitoimipaikkaTextField.textProperty().set(selectedToimipiste.getPostitoimipaikka());
        puhelinnumeroTextField.textProperty().set(selectedToimipiste.getPuhelinnro());
        emailTextField.textProperty().set(selectedToimipiste.getEmail());
        
        //aktivoidaan näkymän alaosa.
        //Lisäksi ladataan tiedot tauluihin
        
        palvelutVarauksetAnchorPane.setDisable(false);

        // Laitetaan palvelut TableViewiin valitun toimipisteen palvelut
        try {
            tfxmls.paivitaNakyma(selectedToimipiste, new Palvelu(), palvelutTableView);
        } catch (SQLException ex) {
            Logger.getLogger(ToimipisteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
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
        
        tfxmls.tallennaButton(selectedToimipiste, haeTietoLomakkeelta());
        
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

    
    
    //Palvelunäkymän metodit
    
    @FXML
    private void palveluTabOnSelectionChanged(Event event) {
        try {
            tfxmls.paivitaNakyma(selectedToimipiste, new Palvelu(), palvelutTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void palveluTyyppiComboBoxOnAction(ActionEvent event) {

      
        
    }
    
    @FXML
    private void palveluLisaaUusiButtonOnAction(ActionEvent event) {
        try {
            // Lisätään uusi palvelu toimipisteelle

            tfxmls.lisaaUusiButton(new Palvelu(), palvelutTableView, mainPane);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ToimipisteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    @FXML
    private void palveluMuokkaaButtonOnAction(ActionEvent event) {
        
        try {
            // Muokataan valittua palvelua
            
            tfxmls.muokkaaButton(selectedToimipiste, palvelutTableView, mainPane);
        } catch (SQLException ex) {
            Logger.getLogger(ToimipisteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToimipisteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    @FXML
    private void palveluPoistaButtonOnAction(ActionEvent event) {
        try {
            tfxmls.poistaButton(palvelutTableView);
        } catch (SQLException ex) {
            Logger.getLogger(ToimipisteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    // Varaus-näkymän metodit
    
    @FXML
    private void varauksetTabOnSelectionChanged(Event event) {
        try {
            tfxmls.paivitaNakyma(selectedToimipiste, new PalveluVaraus(), varauksetTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void varausPalvelutyyppiComboBoxOnAction(ActionEvent event) {
        

        
    }

    @FXML
    private void varausMistaDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void varausMihinDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void varausLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void varausMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void varausPoistaButtonOnAction(ActionEvent event) {
    }

    
 
    
    
    //Privaatteja metodeja
    
    /**
     * haeTietoLomakkeelta
     * 
     * Metodi palauttaa olion, jossa on lomakkeelle taytetyt tiedot
     * 
     * 18.4.2019 Lassi Puurunen
     * 
     * @return toimipiste
     */
    
    public Toimipiste haeTietoLomakkeelta() {
        return new Toimipiste(nimiTextField.getText(), 
                lahiosoiteTextField.getText(), postitoimipaikkaTextField.getText(), 
                postinumeroTextField.getText(), emailTextField.getText(), puhelinnumeroTextField.getText());
    }
    
    
    private void listeners() {
        
        // Luodaan kuuntelija, joka aktivoi tai deaktivoi muokkaus ja poistonapit, 
        // kun taulusta valitaan rivi
        
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
        
       
        
        //Palvelu Hakutoiminnon kuuntelija
        palveluHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            
            
        });
        
        
        //PalveluVaraus hakutoiminnon kuuntelija
        varausHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            
            
            
        });
    }

    private void propertyValueFactories() {
        
        // Tehdään näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.
        
        
        palveluNimiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("nimi"));
        palveluTyyppiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("tyyppiString"));
        palveluKuvausColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("kuvaus"));
        palveluHintaColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hinta"));
        palveluAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("alv"));
        palveluHintaAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hintaAlv"));
        
        varausIdColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Integer>("varausId"));
        varausAsiakasIdColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Integer>("asiakasId"));
        varausPalveluTyyppiColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, String>("palveluTyyppiString"));
        varausPalvelunNimiColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, String>("palvelunNimi"));
        varausPalvelunAlkuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("palvelunVarausAlku"));
        varausPalvelunLoppuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("palvelunVarausLoppu"));
        varausVarattuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("varausVarattu"));
        varausVahvistettuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("varausVahvistettu"));


    }
}

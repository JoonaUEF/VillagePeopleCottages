package villagepeoplecottages;
        
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.asiakas.Asiakas;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Lassi Puurunen, Joona Honkanen
 
 Versiohistoria
 
 15.4.2019 Tiedosto luotu. Lassi Puurunen
 15.4.2019 Toimipisteen TableView-tietokantayhteys toteutettu. Lassi Puurunen
 16.4.2019 Toimipisteen lisäys-toiminto lisätty. Lassi Puurunen
 16.4.2019 Toimipisteen poisto-toiminto lisätty, poisto ja muokkaus -nappien aktivointi ja deaktivointi. Lassi Puurunen
 18.4.2019 Päivitetty käyttämään MainFXMLService -luokkaa. Lassi Puurunen
 20.4.2019 Palvelun TableView, lisäys ja poisto-toiminnot lisätty. Joona Honkanen
 25.4.2019 Täydennetty kaikki FXML:n toiminnot.
 2.5.2019  MainTableController otettu käyttöön.
 * 
 */

public class MainFXMLController implements Initializable {

    // Määritetään MainFXMLService käyttöön
    private MainFXMLService mfxmls = new MainFXMLService();
    
    // Määritetään MainTableController käyttöön
    private MainTableController tableController = new MainTableController();
      
    // Mainpanen avulla voidaan päänäkymä aktivoida tai deaktivoida muita ikkunoita käsitellessä
    @FXML private AnchorPane mainPane;
    
    
    
    @FXML private Tab toimipisteetTab;
    
    @FXML private TextField toimipisteetHakuTextField;
    @FXML private Button toimipisteetLisaaUusiButton;
    @FXML private Button toimipisteetMuokkaaButton;
    @FXML private Button toimipisteetPoistaButton;
    
    @FXML private TableView<Toimipiste> toimipisteetTableView;
        @FXML private TableColumn<Toimipiste, String> toimipisteNimiColumn;
        @FXML private TableColumn<Toimipiste, String> toimipisteLahiosoiteColumn;
        @FXML private TableColumn<Toimipiste, String> toimipistePostinroColumn;
        @FXML private TableColumn<Toimipiste, String> toimipistePostitoimipaikkaColumn;
        @FXML private TableColumn<Toimipiste, String> toimipistePuhelinnumeroColumn;
        @FXML private TableColumn<Toimipiste, String> toimipisteEmailColumn;
    
    // Määritetään palvelunäkymän tiedot
    @FXML private Tab palveluttTab;
    
    @FXML private TextField palvelutHakuTextField;
    @FXML private ComboBox<?> palvelutToimipisteComboBox;
    @FXML private ComboBox<?> palvelutPalvelutyyppiComboBox;
    
    @FXML private Button palveluLisaaUusiButton;
    @FXML private Button palveluMuokkaaButton;
    @FXML private Button palveluPoistaButton;
    
    @FXML private TableView<Palvelu> palvelutTableView;
        @FXML private TableColumn<Palvelu, String> palveluToimipisteNimiColumn;
        @FXML private TableColumn<Palvelu, String> palveluNimiColumn;
        @FXML private TableColumn<Palvelu, String> palveluTyyppiColumn;
        @FXML private TableColumn<Palvelu, String> palveluKuvausColumn;
        @FXML private TableColumn<Palvelu, Double> palveluHintaColumn;
        @FXML private TableColumn<Palvelu, Double> palveluAlvColumn;
        @FXML private TableColumn<Palvelu, Double> palveluHintaAlvColumn;
    
    // Määritetään asiakasnäkymän tiedot
    @FXML private Tab asiakkaatTab;
    
    @FXML private TextField asiakasHakuTextField;
    
    @FXML private Button asiakasLisaaUusiButton;
    @FXML private Button asiakasMuokkaaButton;
    @FXML private Button asiakasPoistaButton;
    
    @FXML private TableView<Asiakas> asiakkaatTableView;
        @FXML private TableColumn<Asiakas, Integer> asiakasIdColumn;
        @FXML private TableColumn<Asiakas, String> asiakasEtunimiColumn;
        @FXML private TableColumn<Asiakas, String> asiakasSukunimiColumn;
        @FXML private TableColumn<Asiakas, String> asiakasLahiosoiteColumn;
        @FXML private TableColumn<Asiakas, String> asiakasPostinroColumn;
        @FXML private TableColumn<Asiakas, String> asiakasPostitoimipaikkaColumn;
        @FXML private TableColumn<Asiakas, String> asiakasPuhelinnumeroColumn;
        @FXML private TableColumn<Asiakas, String> asiakasEmailColumn;
    
    
    // Määritetään varaukset-näkymän tiedot
    @FXML private Tab varauksetTab;
    
    @FXML private TextField varauksetHakuTextField;
    @FXML private ComboBox<?> varauksetToimipisteComboBox;
    @FXML private ComboBox<?> varauksetPalvelutyyppiComboBox;
    @FXML private DatePicker varauksetMistaDatePicker;
    @FXML private DatePicker varauksetMihinDatePicker;
    
    @FXML private Button varauksetLisaaUusiButton;
    @FXML private Button varauksetMuokkaaButton;
    @FXML private Button varauksetPoistaButton;
    
    @FXML private TableView<?> varauksetTableView;
        @FXML private TableColumn<?, ?> varausIdColumn;
        @FXML private TableColumn<?, ?> varausAsiakasIdColumn;
        @FXML private TableColumn<?, ?> varausToimipisteColumn;
        @FXML private TableColumn<?, ?> varausPalveluTyyppiColumn;
        @FXML private TableColumn<?, ?> varausPalvelunNimiColumn;
        @FXML private TableColumn<?, ?> varausPalvelunAlkuColumn;
        @FXML private TableColumn<?, ?> varausPalvelunLoppuColumn;
        @FXML private TableColumn<?, ?> varausVarattuColumn;
        @FXML private TableColumn<?, ?> varausVahvistettuColumn;
    
    
    // Määritetään laskut-näkymän tiedot
    @FXML private Tab laskutTab;
    
    @FXML private TextField laskutHakuTextField;
    @FXML private ComboBox<?> laskutToimipisteComboBox;
    @FXML private DatePicker laskutAlkaenDatePicker;
    @FXML private DatePicker laskutPaattyenDatePicker;
    @FXML private ComboBox<?> laskutTilaComboBox;
    
    @FXML private Button laskutLisaaUusiButton;
    @FXML private Button laskutMuokkaaButton;
    @FXML private Button laskutPoistaButton;
    
    @FXML private TableView<?> laskutTableView;
        @FXML private TableColumn<?, ?> laskutToimipisteColumn;
        @FXML private TableColumn<?, ?> laskutLaskuIdColumn;
        @FXML private TableColumn<?, ?> laskutVarausIdColumn;
        @FXML private TableColumn<?, ?> laskutAsiakasIdColumn;
        @FXML private TableColumn<?, ?> laskutTilaColumn;
        @FXML private TableColumn<?, ?> laskutPaivaysColumn;
        @FXML private TableColumn<?, ?> laskutNimiColumn;
        @FXML private TableColumn<?, ?> laskutLahiosoiteColumn;
        @FXML private TableColumn<?, ?> laskutPostinumeroColumn;
        @FXML private TableColumn<?, ?> laskutPostitoimipaikkaColumn;
        @FXML private TableColumn<?, ?> laskutSummaColumn;
        @FXML private TableColumn<?, ?> laskutAlvColumn;
        @FXML private TableColumn<?, ?> laskutSummaAvlColumn;
    
    //Tilapalkki
    @FXML private Label tilapalkkiLabel;

    
    
    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Tehdään PropertyValueFactory:t, joiden avulla oliot yhdistetään
        // tableView:hin
        this.propertyValueFactories();
        
        // Alustetaan tableController ensimmäiseen näkymään
        try {
            tableController.initializeTable(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Tehdään kuuntelijat
        this.listeners();
        
    }    
    
     
    
    
    /**
     * Toimipiste-näkymän metodit
     * 
     */
    
    //Tabin aktivoituminen
    @FXML private void toimipisteetTabOnSelectionChanged(Event event) {
        try {
            // Haetaan näkymään tiedot tietokannasta
            tableController.initializeTable(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }
    
    //Lisää uusi napin painallus
    @FXML private void toimipisteetLisaaUusiButtonOnAction(ActionEvent event) {
        try {
            mfxmls.lisaaUusiButton(new Toimipiste(), toimipisteetTableView, mainPane);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Muokkaa-napin painallus
    @FXML private void toimipisteetMuokkaaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.muokkaaButton(toimipisteetTableView, mainPane);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Poista-napin painallus
    @FXML
    private void toimipisteetPoistaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.poistaButton(toimipisteetTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
    /**
     * Palvelu-näkymän metodit alkavat tästä
     * 
     */
    
    @FXML private void palvelutTabOnSelectionChanged(Event event) {
        try {
            tableController.initializeTable(new Palvelu(), palvelutTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML private void palvelutToimipisteComboBoxOnAction(ActionEvent event) {
    }

    @FXML private void palvelutPalvelutyyppiComboBoxOnAction(ActionEvent event) {
    }
    
    @FXML private void palveluLisaaUusiButtonOnAction(ActionEvent event) {

        // Lisätään uusi toimipiste
        
        try {
            mfxmls.lisaaUusiButton(new Palvelu(), palvelutTableView, mainPane);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML private void palveluMuokkaaButtonOnAction(ActionEvent event) {
        
        // Muokataan valittua palvelua
        
        try {
            mfxmls.muokkaaButton(palvelutTableView, mainPane);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML private void palveluPoistaButtonOnAction(ActionEvent event) {
   
        // Poistetaan valittu palvelu
        
        try {
            mfxmls.poistaButton(palvelutTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Asiakas-näkymän metodit alkavat tästä
     * 
     */
    
    @FXML private void asiakkaatTabOnSelectionChanged(Event event) {
        // Haetaan näkymään tiedot tietokannasta
        
        try {
            asiakkaatTableView.setItems(new AsiakasDao().list());
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    @FXML private void asiakasLisaaUusiButtonOnAction(ActionEvent event) {

        // Lisätään uusi asiakas
        
        try {
            mfxmls.lisaaUusiButton(new Asiakas(), asiakkaatTableView, mainPane);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML private void asiakasMuokkaaButtonOnAction(ActionEvent event) {
        
        // Muokataan valittua asiakasttä
        
        try {
            mfxmls.muokkaaButton(asiakkaatTableView, mainPane);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML private void asiakasPoistaButtonOnAction(ActionEvent event) {
   
        // Poistetaan valittu asiakas
        
        try {
            mfxmls.poistaButton(asiakkaatTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Varaukset -näkymän toiminnot
     * 
     */
    
    @FXML private void varauksetTabOnSelectionChanged(Event event) {
    }
    
    @FXML private void varauksetToimipisteComboBoxOnAction(ActionEvent event) {
    }

    @FXML private void varauksetPalvelutyyppiComboBoxOnAction(ActionEvent event) {
    }

    @FXML private void varauksetMistaDatePickerOnAction(ActionEvent event) {
    }

    @FXML private void varauksetMihinDatePickerOnAction(ActionEvent event) {
    }

    @FXML private void varauksetLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML private void varauksetMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML private void varauksetPoistaButtonOnAction(ActionEvent event) {
    }

    
    /**
     * Laskut -näkymän toiminnot
     * 
     */
    
    @FXML private void laskutTabOnSelectionChanged(Event event) {
    }
    
    @FXML private void laskutToimipisteComboBoxOnAction(ActionEvent event) {
    } 
    
    @FXML private void laskutAlkaenDatePickerOnAction(ActionEvent event) {
    }

    @FXML private void laskutPaattyenDatePickerOnAction(ActionEvent event) {
    }

    @FXML private void laskutTilaComboBoxOnAction(ActionEvent event) {
    }

    @FXML private void laskutLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML private void laskutMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML private void laskutPoistaButtonOnAction(ActionEvent event) {
    }

    

    
    
    // Yleismetodeja
    
    
    /**
     * Täällä käynnistetään kuuntelijat
     * 
     * 18.4.2019 Lassi Puurunen
     * 20.4.2019 Palvelut lisätty. Joona Honkanen
     */
    
    private void listeners() {
        
        // Luodaan kuuntelija, joka aktivoi tai deaktivoi muokkaus ja poistonapit, 
        // kun taulusta valitaan rivi
        //
        // TODO kaikille päänäkymän tauluille
        
        toimipisteetTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                toimipisteetMuokkaaButton.setDisable(false);
                toimipisteetPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
                toimipisteetMuokkaaButton.setDisable(true);
                toimipisteetPoistaButton.setDisable(true);
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

        asiakkaatTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                asiakasMuokkaaButton.setDisable(false);
                asiakasPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
            	asiakasMuokkaaButton.setDisable(true);
            	asiakasPoistaButton.setDisable(true);
            }
        });
        
        
        //TODO haku- ja rajaustoimintojen kuuntelijat
        
        //Toimipisteen Hakutoiminnon kuuntelija
        toimipisteetHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            new ListenerMethods().toimipisteHakuListener(tableController.getToimipisteFilteredData(), newValue);
        });
        
        //Palvelu Hakutoiminnon kuuntelija
        palvelutHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            new ListenerMethods().palveluHakuListener(tableController.getPalveluFilteredData(), newValue);
        });
        
    }

    private void propertyValueFactories() {
    
        // Tehdään toimipiste-näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.

        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("nimi"));
        toimipisteLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("lahiosoite"));
        toimipistePostinroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postinro"));
        toimipistePostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postitoimipaikka"));
        toimipistePuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("puhelinnro"));
        toimipisteEmailColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("email"));
    
        palveluNimiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("nimi"));
        palveluToimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("toimipisteNimi"));
        palveluTyyppiColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("tyyppiString"));
        palveluKuvausColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, String>("kuvaus"));
        palveluHintaColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hinta"));
        palveluAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("alv"));
        palveluHintaAlvColumn.setCellValueFactory(new PropertyValueFactory<Palvelu, Double>("hintaAlv"));

        asiakasIdColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, Integer>("asiakasId"));
        asiakasEtunimiColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("etunimi"));
        asiakasSukunimiColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("sukunimi"));
        asiakasLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("lahiosoite"));
        asiakasPostinroColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("postinro"));
        asiakasPostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("postitoimipaikka"));
        asiakasPuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("puhelinnro"));
        asiakasEmailColumn.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("email"));

        // TODO Tehdään sama muille näkymille
    }
    
    
    
}

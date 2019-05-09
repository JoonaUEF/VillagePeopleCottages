package villagepeoplecottages;
        
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.varaus.Varaus;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import villagepeoplecottages.toimipiste.ToimipisteFXMLSearchFilters;



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
 25.4.2019 Täydennetty kaikki FXML:n toiminnot. Lassi Puurunen
  2.5.2019 MainTableController otettu käyttöön. Lassi Puurunen
  8.5.2019 Kaikkien näkymien listaukset. Lassi Puurunen
 * 
 */

public class MainFXMLController implements Initializable {

    // Määritetään MainFXMLService käyttöön
    private MainFXMLService mfxmls = new MainFXMLService();
    
      
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
    @FXML private ComboBox<String> palvelutToimipisteComboBox;
    @FXML private ComboBox<String> palvelutPalvelutyyppiComboBox;
    
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
    @FXML private ComboBox<String> varauksetToimipisteComboBox;
    @FXML private ComboBox<String> varauksetPalvelutyyppiComboBox;
    @FXML private DatePicker varauksetMistaDatePicker;
    @FXML private DatePicker varauksetMihinDatePicker;
    
    @FXML private Button varauksetLisaaUusiButton;
    @FXML private Button varauksetMuokkaaButton;
    @FXML private Button varauksetPoistaButton;
    
    @FXML private TableView<PalveluVaraus> varauksetTableView;
        @FXML private TableColumn<PalveluVaraus, Integer> varausIdColumn;
        @FXML private TableColumn<PalveluVaraus, Integer> varausAsiakasIdColumn;
        @FXML private TableColumn<PalveluVaraus, String> varausToimipisteColumn;
        @FXML private TableColumn<PalveluVaraus, String> varausPalveluTyyppiColumn;
        @FXML private TableColumn<PalveluVaraus, String> varausPalvelunNimiColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunAlkuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausPalvelunLoppuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausVarattuColumn;
        @FXML private TableColumn<PalveluVaraus, Date> varausVahvistettuColumn;
    
    
    // Määritetään laskut-näkymän tiedot
    @FXML private Tab laskutTab;
    
    @FXML private TextField laskutHakuTextField;
    @FXML private ComboBox<String> laskutToimipisteComboBox;
    @FXML private DatePicker laskutAlkaenDatePicker;
    @FXML private DatePicker laskutPaattyenDatePicker;
    @FXML private ComboBox<String> laskutTilaComboBox;
    
    @FXML private Button laskutLisaaUusiButton;
    @FXML private Button laskutMuokkaaButton;
    @FXML private Button laskutPoistaButton;
    
    @FXML private TableView<Lasku> laskutTableView;
        @FXML private TableColumn<Lasku, String> laskutToimipisteColumn;
        @FXML private TableColumn<Lasku, Integer> laskutLaskuIdColumn;
        @FXML private TableColumn<Lasku, Integer> laskutVarausIdColumn;
        @FXML private TableColumn<Lasku, Integer> laskutAsiakasIdColumn;
        @FXML private TableColumn<Lasku, String> laskutTilaColumn;
        @FXML private TableColumn<Lasku, Date> laskutPaivaysColumn;
        @FXML private TableColumn<Lasku, String> laskutNimiColumn;
        @FXML private TableColumn<Lasku, String> laskutLahiosoiteColumn;
        @FXML private TableColumn<Lasku, String> laskutPostinumeroColumn;
        @FXML private TableColumn<Lasku, String> laskutPostitoimipaikkaColumn;
        @FXML private TableColumn<Lasku, Double> laskutSummaColumn;
        @FXML private TableColumn<Lasku, Double> laskutAlvColumn;
        @FXML private TableColumn<Lasku, Double> laskutSummaAvlColumn;
    
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
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Tehdään kuuntelijat
        this.listeners();
        
        //Lisätään näkymiin ComboBoxien sisällöt
        this.comboBoxit();
        
    }    
    
     
    
    
    /**
     * Toimipiste-näkymän metodit
     * 
     */
    
    //Tabin aktivoituminen
    @FXML private void toimipisteetTabOnSelectionChanged(Event event) {
        try {
            // Haetaan näkymään tiedot tietokannasta
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mfxmls.suodataNakyma(new Toimipiste(), toimipisteetTableView, null, null, toimipisteetHakuTextField.getText() );
        
        
            
    }
    
    //Lisää uusi napin painallus
    @FXML private void toimipisteetLisaaUusiButtonOnAction(ActionEvent event) {
        try {
            mfxmls.lisaaUusiButton(new Toimipiste(), toimipisteetTableView, mainPane);
            //päivitetään näkymä
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.comboBoxit();
        mfxmls.suodataNakyma(new Toimipiste(), toimipisteetTableView, null, null, toimipisteetHakuTextField.getText() );
    }

    //Muokkaa-napin painallus
    @FXML private void toimipisteetMuokkaaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.muokkaaButton(toimipisteetTableView, mainPane);
            //päivitetään näkymä
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.comboBoxit();
        mfxmls.suodataNakyma(new Toimipiste(), toimipisteetTableView, null, null, toimipisteetHakuTextField.getText() );
    }

    //Poista-napin painallus
    @FXML
    private void toimipisteetPoistaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.poistaButton(toimipisteetTableView);
            //päivitetään näkymä
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.comboBoxit();
        mfxmls.suodataNakyma(new Toimipiste(), toimipisteetTableView, null, null, toimipisteetHakuTextField.getText() );
    }


   
    /**
     * Palvelu-näkymän metodit alkavat tästä
     * 
     */
    
    @FXML private void palvelutTabOnSelectionChanged(Event event) {
        try {
            mfxmls.paivitaNakyma(new Palvelu(), palvelutTableView);
            tilapalkkiLabel.setText("Palvelut ladattu tietokannasta");
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            tilapalkkiLabel.setText("Virhe palveluiden latauksessa tietokannasta");
        }
        
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());
    }
    
    @FXML private void palvelutToimipisteComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());
    }

    @FXML private void palvelutPalvelutyyppiComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());
    }
    
    @FXML private void palveluLisaaUusiButtonOnAction(ActionEvent event) {

        // Lisätään uusi toimipiste
        
        try {
            mfxmls.lisaaUusiButton(new Palvelu(), palvelutTableView, mainPane);
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());

    }
    
    @FXML private void palveluMuokkaaButtonOnAction(ActionEvent event) {
        
        // Muokataan valittua palvelua
        
        try {
            mfxmls.muokkaaButton(palvelutTableView, mainPane);
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());
    }
    
    @FXML private void palveluPoistaButtonOnAction(ActionEvent event) {
   
        // Poistetaan valittu palvelu
        
        try {
            mfxmls.poistaButton(palvelutTableView);
            mfxmls.paivitaNakyma(new Toimipiste(), toimipisteetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, palvelutHakuTextField.getText());
    }

    /**
     * Asiakas-näkymän metodit alkavat tästä
     * 
     */
    
    @FXML private void asiakkaatTabOnSelectionChanged(Event event) {
        // Haetaan näkymään tiedot tietokannasta
        
        try {
            mfxmls.paivitaNakyma(new Asiakas(), asiakkaatTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mfxmls.suodataNakyma(new Asiakas(), asiakkaatTableView, null, null, asiakasHakuTextField.getText());
    }
       
    @FXML private void asiakasLisaaUusiButtonOnAction(ActionEvent event) {

        // Lisätään uusi asiakas
        
        try {
            mfxmls.lisaaUusiButton(new Asiakas(), asiakkaatTableView, mainPane);
            mfxmls.paivitaNakyma(new Asiakas(), asiakkaatTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Asiakas(), asiakkaatTableView, null, null, asiakasHakuTextField.getText());

    }

    @FXML private void asiakasMuokkaaButtonOnAction(ActionEvent event) {
        
        // Muokataan valittua asiakasttä
        
        try {
            mfxmls.muokkaaButton(asiakkaatTableView, mainPane);
            mfxmls.paivitaNakyma(new Asiakas(), asiakkaatTableView);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Asiakas(), asiakkaatTableView, null, null, asiakasHakuTextField.getText());
    }
   
    @FXML private void asiakasPoistaButtonOnAction(ActionEvent event) {
   
        // Poistetaan valittu asiakas
        
        try {
            mfxmls.poistaButton(asiakkaatTableView);
            mfxmls.paivitaNakyma(new Asiakas(), asiakkaatTableView);
 
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Asiakas(), asiakkaatTableView, null, null, asiakasHakuTextField.getText());
    }


    /**
     * Varaukset -näkymän toiminnot
     * 
     */
    
    @FXML private void varauksetTabOnSelectionChanged(Event event) {
        try {
            mfxmls.paivitaNakyma(new PalveluVaraus(), varauksetTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }
    
    @FXML private void varauksetToimipisteComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetPalvelutyyppiComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetMistaDatePickerOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetMihinDatePickerOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetLisaaUusiButtonOnAction(ActionEvent event) {
        try {
            mfxmls.lisaaUusiButton(new Varaus(), varauksetTableView, mainPane);
            mfxmls.paivitaNakyma(new Varaus(), varauksetTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetMuokkaaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.muokkaaButton(varauksetTableView, mainPane);
            mfxmls.paivitaNakyma(new Varaus(), varauksetTableView);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    @FXML private void varauksetPoistaButtonOnAction(ActionEvent event) {
        
        mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, varauksetHakuTextField.getText());
    }

    
    /**
     * Laskut -näkymän toiminnot
     * 
     */
    
    @FXML private void laskutTabOnSelectionChanged(Event event) {
        try {
            mfxmls.paivitaNakyma(new Lasku(), laskutTableView);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }
    
    @FXML private void laskutToimipisteComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    } 
    
    @FXML private void laskutAlkaenDatePickerOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }

    @FXML private void laskutPaattyenDatePickerOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }

    @FXML private void laskutTilaComboBoxOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }

    @FXML private void laskutLisaaUusiButtonOnAction(ActionEvent event) {
        try {
            mfxmls.lisaaUusiButton(new Lasku(), laskutTableView, mainPane);
            mfxmls.paivitaNakyma(new Lasku(), laskutTableView);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }

    @FXML private void laskutMuokkaaButtonOnAction(ActionEvent event) {
        try {
            mfxmls.muokkaaButton(laskutTableView, mainPane);
            mfxmls.paivitaNakyma(new Lasku(), laskutTableView);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
    }

    @FXML private void laskutPoistaButtonOnAction(ActionEvent event) {
        mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, laskutHakuTextField.getText());
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
        
        varauksetTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                varauksetMuokkaaButton.setDisable(false);
                varauksetPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
            	varauksetMuokkaaButton.setDisable(true);
            	varauksetPoistaButton.setDisable(true);
            }
        });
        
        laskutTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                laskutMuokkaaButton.setDisable(false);
                laskutPoistaButton.setDisable(false);
            }
            if (newSelection == null) {
            	laskutMuokkaaButton.setDisable(true);
            	laskutPoistaButton.setDisable(true);
            }
        });
        
        
        
        
        //Toimipisteen Hakutoiminnon kuuntelija
        toimipisteetHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            mfxmls.suodataNakyma(new Toimipiste(), toimipisteetTableView, null, null, newValue );
        });
        
        //Palvelu Hakutoiminnon kuuntelija
        palvelutHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {   
            mfxmls.suodataNakyma(new Palvelu(), palvelutTableView, palvelutToimipisteComboBox, palvelutPalvelutyyppiComboBox, newValue);
        });
        
        //Asiakas Hakutoiminnon kuuntelija
        asiakasHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            mfxmls.suodataNakyma(new Asiakas(), asiakkaatTableView, null, null, newValue);
        });
        
        //Varaukset Hakutoiminnon kuuntelija
        varauksetHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            mfxmls.suodataNakyma(new PalveluVaraus(), varauksetTableView, varauksetToimipisteComboBox, varauksetPalvelutyyppiComboBox, varauksetMistaDatePicker, varauksetMihinDatePicker, newValue);
        });
        
        //Laskut hakutoiminnon kuuntelija
        laskutHakuTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            mfxmls.suodataNakyma(new Lasku(), laskutTableView, laskutToimipisteComboBox, laskutAlkaenDatePicker, laskutPaattyenDatePicker, laskutTilaComboBox, newValue);
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

        varausIdColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Integer>("varausId"));
        varausAsiakasIdColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Integer>("asiakasId"));
        varausToimipisteColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, String>("toimipiste"));
        varausPalveluTyyppiColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, String>("palveluTyyppiString"));
        varausPalvelunNimiColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, String>("palvelunNimi"));
        varausPalvelunAlkuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("palvelunVarausAlku"));
        varausPalvelunLoppuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("palvelunVarausLoppu"));
        varausVarattuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("varausVarattu"));
        varausVahvistettuColumn.setCellValueFactory(new PropertyValueFactory<PalveluVaraus, Date>("varausVahvistettu"));
        
        laskutLaskuIdColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Integer>("laskuId"));
        laskutToimipisteColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("toimipiste"));
        laskutVarausIdColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Integer>("varausId"));
        laskutAsiakasIdColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Integer>("asiakasId"));
        laskutTilaColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("tilaString"));
        laskutPaivaysColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Date>("paivays"));
        laskutNimiColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("nimi"));
        laskutLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("lahiosoite"));
        laskutPostinumeroColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("postinro"));
        laskutPostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Lasku, String>("postitoimipaikka"));
        laskutSummaColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Double>("summa"));
        laskutAlvColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Double>("alv"));
        laskutSummaAvlColumn.setCellValueFactory(new PropertyValueFactory<Lasku, Double>("summaAlv"));
        
        
        // Muunnetaan päiväykset kotimaiseen muotoon
        paivayksienMuunnin();
        
    }

    private void comboBoxit() {
        ObservableList<String> toimipisteet = FXCollections.observableArrayList();
        toimipisteet.add("Kaikki toimipisteet");
        try {
            for (Toimipiste toimipiste : new ToimipisteDao().list()) {
                toimipisteet.add(toimipiste.getNimi());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList<String> palvelutyypit = FXCollections.observableArrayList();
        for (String string : new Palvelu().getTyypit()) {
            palvelutyypit.add(string);
        }
        
        ObservableList<String> laskunTilat = FXCollections.observableArrayList();
        laskunTilat.add("Kaikki");
        for (String string : new Lasku().getLaskunTila()) {
            laskunTilat.add(string);
        }
        
        palvelutToimipisteComboBox.setItems(toimipisteet);
        varauksetToimipisteComboBox.setItems(toimipisteet);
        laskutToimipisteComboBox.setItems(toimipisteet);
        
        palvelutPalvelutyyppiComboBox.setItems(palvelutyypit);
        varauksetPalvelutyyppiComboBox.setItems(palvelutyypit);
        
        laskutTilaComboBox.setItems(laskunTilat);
    }

    // Muunnetaan päiväyksien muoto kotimaiseksi
    private void paivayksienMuunnin() {
        varausPalvelunAlkuColumn.setCellFactory(column -> {  
            TableCell<PalveluVaraus, Date> cell = new TableCell<PalveluVaraus, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
        
        varausPalvelunLoppuColumn.setCellFactory(column -> {  
            TableCell<PalveluVaraus, Date> cell = new TableCell<PalveluVaraus, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
        
        varausVarattuColumn.setCellFactory(column -> {  
            TableCell<PalveluVaraus, Date> cell = new TableCell<PalveluVaraus, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
        
        varausVahvistettuColumn.setCellFactory(column -> {  
            TableCell<PalveluVaraus, Date> cell = new TableCell<PalveluVaraus, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
        
        laskutPaivaysColumn.setCellFactory(column -> {  
            TableCell<Lasku, Date> cell = new TableCell<Lasku, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
    }
    
    
    
}

package villagepeoplecottages.asiakas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import villagepeoplecottages.interfaces.FXMLControllerInterface;

/**
 * FXML Controller class
 *
 * 18.4.2019 Lassi Puurunen
 */
public class AsiakasFXMLController implements Initializable, FXMLControllerInterface<Asiakas> {

    // Ladataan Service käyttöön
    private AsiakasFXMLService afxmls = new AsiakasFXMLService();
    
    // Controllerille tuleva olio initData:ssa
    private Asiakas vanhaAsiakas;

    @FXML private TextField etunimiTextField;
    @FXML private TextField sukunimiTextField;
    @FXML private TextField lahiosoiteTextField;
    @FXML private TextField postinumeroTextField;
    @FXML private TextField postitoimipaikkaTextField;
    @FXML private TextField puhelinnumeroTextField;
    @FXML private TextField emailTextField;
    @FXML private Button tallennaButton;
    @FXML private Button peruutaButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label asiakasIdLabel;
    @FXML
    private AnchorPane palvelutVarauksetAnchorPane;
    @FXML
    private Tab varauksetTab;
    @FXML
    private TextField varausHakuTextField;
    @FXML
    private DatePicker varausMistaDatePicker;
    @FXML
    private DatePicker varausMihinDatePicker;
    @FXML
    private Button varausLisaaUusiButton;
    @FXML
    private Button varausMuokkaaButton;
    @FXML
    private Button varausPoistaButton;
    @FXML
    private TableView<?> varauksetTableView;
    @FXML
    private TableColumn<?, ?> varausIdColumn;
    @FXML
    private TableColumn<?, ?> varausToimipisteColumn;
    @FXML
    private TableColumn<?, ?> varausVarattuColumn;
    @FXML
    private TableColumn<?, ?> varausVahvistettuColumn;
    @FXML
    private Tab laskutTab;
    @FXML
    private TextField laskutHakuTextField;
    @FXML
    private DatePicker laskutAlkaenDatePicker;
    @FXML
    private DatePicker laskutPaattyenDatePicker;
    @FXML
    private ComboBox<?> laskutTilaComboBox;
    @FXML
    private Button laskutLisaaUusiButton;
    @FXML
    private Button laskutMuokkaaButton;
    @FXML
    private Button laskutPoistaButton;
    @FXML
    private TableView<?> laskutTableView;
    @FXML
    private TableColumn<?, ?> laskutLaskuIdColumn;
    @FXML
    private TableColumn<?, ?> laskutToimipisteColumn;
    @FXML
    private TableColumn<?, ?> laskutVarausIdColumn;
    @FXML
    private TableColumn<?, ?> laskutTilaColumn;
    @FXML
    private TableColumn<?, ?> laskutPaivaysColumn;
    @FXML
    private TableColumn<?, ?> laskutSummaColumn;
    @FXML
    private TableColumn<?, ?> laskutAlvColumn;
    @FXML
    private TableColumn<?, ?> laskutSummaAvlColumn;
    @FXML
    private Label tilaPalkkiLabel;

    
     /** 
      * Initializes the controller class.
      * 
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    @Override
    public void initData(Object object) {
        this.vanhaAsiakas = (Asiakas)object;
        etunimiTextField.textProperty().set(vanhaAsiakas.getEtunimi());
        sukunimiTextField.textProperty().set(vanhaAsiakas.getSukunimi());
        lahiosoiteTextField.textProperty().set(vanhaAsiakas.getLahiosoite());
        postinumeroTextField.textProperty().set(vanhaAsiakas.getPostinro());
        postitoimipaikkaTextField.textProperty().set(vanhaAsiakas.getPostitoimipaikka());
        puhelinnumeroTextField.textProperty().set(vanhaAsiakas.getPuhelinnro());
        emailTextField.textProperty().set(vanhaAsiakas.getEmail());
        
    }
    
    
    
    /**
     * haeTietoLomakkeelta
     * 
     * Metodi palauttaa olion, jossa on lomakkeelle taytetyt tiedot
     * 
     * 18.4.2019 Lassi Puurunen
     * 
     * @return asiakas
     */
    @Override
    public Asiakas haeTietoLomakkeelta() {
        return new Asiakas(etunimiTextField.getText(), sukunimiTextField.getText(), 
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
        
        afxmls.tallennaButton(vanhaAsiakas, haeTietoLomakkeelta());
        
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

    @FXML
    private void varauksetTabOnSelectionChanged(Event event) {
    }

    @FXML
    private void laskutAlkaenDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutPaattyenDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutTilaComboBoxOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutPoistaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void laskutTabOnSelectionChanged(Event event) {
    }

    
}

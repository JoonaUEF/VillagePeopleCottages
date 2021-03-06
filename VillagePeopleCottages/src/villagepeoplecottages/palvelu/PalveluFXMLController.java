package villagepeoplecottages.palvelu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.interfaces.FXMLControllerInterface;

/**
 * FXML Controller class
 *
 * 20.4.2019 Joona Honkanen muokkasi ToimipisteFXMLControllerista.
 */
public class PalveluFXMLController implements Initializable, FXMLControllerInterface<Palvelu> {

  // Ladataan Service käyttöön
  private PalveluFXMLService pfxmls = new PalveluFXMLService();

  // Controllerille tuleva olio initData:ssa
  private Palvelu selectedPalvelu;

  @FXML
  private TextField nimiTextField;
  @FXML
  private TextField kuvausTextField;
  @FXML
  private TextField hintaTextField;
  @FXML
  private TextField alvTextField;
  @FXML
  private Button peruutaButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ComboBox<?> toimipisteComboBox;
    @FXML
    private ComboBox<?> tyyppiComboBox;
    @FXML
    private Button tallennaButton1;
    @FXML
    private AnchorPane palvelutVarauksetAnchorPane;
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
    private TableColumn<?, ?> varausAsiakasIdColumn;
    @FXML
    private TableColumn<?, ?> varausPalvelunAlkuColumn;
    @FXML
    private TableColumn<?, ?> varausPalvelunLoppuColumn;
    @FXML
    private TableColumn<?, ?> varausVarattuColumn;
    @FXML
    private TableColumn<?, ?> varausVahvistettuColumn;
    @FXML
    private Label tilaPalkkiLabel;


  /**
   * Initializes the controller class.
   * 
   */

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    UnaryOperator<Change> intFilter = change -> {
      String text = change.getText();

      if (text.matches("[0-9]*")) {
        return change;
      }

      return null;
    };

    TextFormatter<String> tyyppiTextFormatter = new TextFormatter<>(intFilter);
    UnaryOperator<Change> doubleFilter = change -> {
      String text = change.getText();

      if (text.matches("[[0-9]\\.]*")) {
        return change;
      }

      return null;
    };
    TextFormatter<String> hintaTextFormatter = new TextFormatter<>(doubleFilter);
    TextFormatter<String> alvTextFormatter = new TextFormatter<>(doubleFilter);


    hintaTextField.setTextFormatter(hintaTextFormatter);
    alvTextField.setTextFormatter(alvTextFormatter);
  }


  /**
   * initData
   * 
   * Metodin avulla voidaan ottaa vastaan muokattava objekti.
   * 
   * 20.4.2019 Joona Honkanen
   * 
   * @param object
   */
  public void initData(Object object) {
        
        this.selectedPalvelu = (Palvelu) object;
        nimiTextField.textProperty().set(selectedPalvelu.getNimi());

        kuvausTextField.textProperty().set(selectedPalvelu.getKuvaus());
        hintaTextField.textProperty().set(String.valueOf(selectedPalvelu.getHinta()));
        alvTextField.textProperty().set(String.valueOf(selectedPalvelu.getAlv()));
    
  }



  /**
   * haeTietoLomakkeelta
   * 
   * Metodi palauttaa olion, jossa on lomakkeelle taytetyt tiedot
   * 
   * 20.4.2019 Joona Honkanen
   * 
   * @return palvelu
   * @throws NumberFormatException
   */

  public Palvelu haeTietoLomakkeelta() {
      return null;
  }

  /**
   * tallennaButtonOnAction
   * 
   * Hakee tiedot oliolle lomakkeesta.
   * 
   * Syöttää lomakkeen tiedot, sekä mahdollisesti muokattavan olion Servicen tallennustoiminnolle
   * 
   * 
   * 20.4. Joona Honkanen
   * 
   * @param event
   * @throws SQLException
   */
  @FXML
  private void tallennaButtonOnAction(ActionEvent event) throws SQLException {

    pfxmls.tallennaButton(selectedPalvelu, haeTietoLomakkeelta());

    // sulje ikkuna
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
  public void peruutaButtonOnAction(ActionEvent event) {

    // sulkee ikkunan

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



}

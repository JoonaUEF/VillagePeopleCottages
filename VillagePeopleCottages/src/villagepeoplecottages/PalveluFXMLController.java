package villagepeoplecottages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 20.4.2019 Joona Honkanen muokkasi ToimipisteFXMLControllerista.
 */
public class PalveluFXMLController implements Initializable {

  // Ladataan Service käyttöön
  private PalveluFXMLService pfxmls = new PalveluFXMLService();

  // Controllerille tuleva olio initData:ssa
  private Palvelu vanhaPalvelu;

  @FXML
  private TextField toimipisteTextField;
  @FXML
  private TextField nimiTextField;
  @FXML
  private TextField tyyppiTextField;
  @FXML
  private TextField kuvausTextField;
  @FXML
  private TextField hintaTextField;
  @FXML
  private TextField alvTextField;
  @FXML
  private Button tallennaButton;
  @FXML
  private Button peruutaButton;


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


    tyyppiTextField.setTextFormatter(tyyppiTextFormatter);
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
    
    if (object instanceof Palvelu) {
        
        this.vanhaPalvelu = (Palvelu) object;
        toimipisteTextField.textProperty().set(String.valueOf(vanhaPalvelu.getToimipisteId()));
        nimiTextField.textProperty().set(vanhaPalvelu.getNimi());
        tyyppiTextField.textProperty().set(vanhaPalvelu.getTyyppiString());
        kuvausTextField.textProperty().set(vanhaPalvelu.getKuvaus());
        hintaTextField.textProperty().set(String.valueOf(vanhaPalvelu.getHinta()));
        alvTextField.textProperty().set(String.valueOf(vanhaPalvelu.getAlv()));
    }  
    
    if (object instanceof Toimipiste) {
        try {
            toimipisteTextField.setText(new ToimipisteDao().read(((Toimipiste) object).getToimipisteId()).getNimi());
        } catch (SQLException ex) {
            Logger.getLogger(PalveluFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

  private Palvelu haeTietoLomakkeelta() throws NumberFormatException {
    try {
      return new Palvelu(Integer.parseInt(toimipisteTextField.getText()), nimiTextField.getText(),
          Integer.parseInt(tyyppiTextField.getText()), kuvausTextField.getText(),
          Double.parseDouble(hintaTextField.getText()), Double.parseDouble(alvTextField.getText()));
    } catch (NumberFormatException e) {
      throw(e);
    }
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

    pfxmls.tallennaButton(vanhaPalvelu, haeTietoLomakkeelta());

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
  private void peruutaButtonOnAction(ActionEvent event) {

    // sulkee ikkunan

    final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }



}

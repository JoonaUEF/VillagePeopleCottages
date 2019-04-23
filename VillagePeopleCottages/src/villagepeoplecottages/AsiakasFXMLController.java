package villagepeoplecottages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 18.4.2019 Lassi Puurunen
 */
public class AsiakasFXMLController implements Initializable {

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
    
    private Asiakas haeTietoLomakkeelta() {
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




    
}

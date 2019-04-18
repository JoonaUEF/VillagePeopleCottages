package villagepeoplecottages;

import com.sun.org.apache.xerces.internal.dom.ParentNode;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ToimipisteFXMLController implements Initializable {

    private ToimipisteFXMLService tfxmls = new ToimipisteFXMLService();
    
    // Controllerille tuleva olio initData:ssa
    private Toimipiste vanhaToimipiste;

    @FXML private TextField nimiTextField;
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
        this.vanhaToimipiste = (Toimipiste)object;
        nimiTextField.textProperty().set(vanhaToimipiste.getNimi());
        lahiosoiteTextField.textProperty().set(vanhaToimipiste.getLahiosoite());
        postinumeroTextField.textProperty().set(vanhaToimipiste.getPostinro());
        postitoimipaikkaTextField.textProperty().set(vanhaToimipiste.getPostitoimipaikka());
        puhelinnumeroTextField.textProperty().set(vanhaToimipiste.getPuhelinnro());
        emailTextField.textProperty().set(vanhaToimipiste.getEmail());
        
    }
    
    
    
    /**
     * haeTietoLomakkeelta
     * 
     * Metodi palauttaa olion, jossa on lomakkeen tiedot
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
        
        tfxmls.tallennaButton(vanhaToimipiste, haeTietoLomakkeelta());
        
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

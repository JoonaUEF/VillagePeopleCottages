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

    @FXML
    private TextField nimiTextField;
    @FXML
    private TextField lahiosoiteTextField;
    @FXML
    private TextField postinumeroTextField;
    @FXML
    private TextField postitoimipaikkaTextField;
    @FXML
    private TextField puhelinnumeroTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button peruutaButton;
    @FXML
    private Button LisaaButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SuljeIkkuna(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void LisaaUusiToimipiste(ActionEvent event) throws SQLException {
        new ToimipisteDao().create(new Toimipiste(nimiTextField.getText(), lahiosoiteTextField.getText(), 
                postitoimipaikkaTextField.getText(), postinumeroTextField.getText(), 
                emailTextField.getText(), puhelinnumeroTextField.getText()));
        
        
        this.SuljeIkkuna(event);
        
    }
    
}

package villagepeoplecottages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;


/**
 * FXML Controller class
 *
 * @author Lassi Puurunen
 * 
 * Versiohistoria
 * 
 * 15.4.2019 Tiedosto luotu, Lassi Puurunen
 */
public class MainFXMLController implements Initializable {
    
    @FXML
    private TableView<Toimipiste> toimipisteetLista;
    @FXML
    private TitledPane toimipisteidenHallinta;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }    
    
}

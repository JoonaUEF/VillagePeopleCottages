package villagepeoplecottages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;


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

    //Määritetään toimipistetablen tiedot
    @FXML private TableView<Toimipiste> toimipisteetTableView;
    @FXML private TableColumn<Toimipiste, String> toimipisteNimiColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteLahiosoiteColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostinroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostitoimipaikkaColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePuhelinnumeroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteEmailColumn;
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Tehdään toimipiste-näkymälle PropertyValueFactoryt
        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("nimi"));
        toimipisteLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("lahiosoite"));
        toimipistePostinroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postinro"));
        toimipistePostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postitoimipaikka"));
        toimipistePuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("puhelinnro"));
        toimipisteEmailColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("email"));
        
        // TODO Tehdään sama muille näkymille
        
    }    

    /**
     * Siirryttäessä toimipiste-välilehdelle, ladataan tietokannasta toimipisteiden tiedot
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void lataaToimipisteet(Event event) {
        
        try {
            toimipisteetTableView.setItems(new ToimipisteDao().list());
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

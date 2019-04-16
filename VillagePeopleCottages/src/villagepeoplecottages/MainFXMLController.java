package villagepeoplecottages;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Lassi Puurunen
 * 
 * Versiohistoria
 * 
 * 15.4.2019 Tiedosto luotu. Lassi Puurunen
 * 15.4.2019 Toimipisteen TableView-tietokantayhteys toteutettu. Lassi Puurunen
 * 16.4.2019 Toimipisteen lisäys-toiminto lisätty. Lassi Puurunen
 * 16.4.2019 Toimipisteen poisto-toiminto lisätty, poisto ja muokkaus -nappien aktivointi ja deaktivointi. Lassi Puurunen
 * 
 */
public class MainFXMLController implements Initializable {

    //Määritetään toimipistenäkymän tiedot
    @FXML private TableView<Toimipiste> toimipisteetTableView;
    @FXML private TableColumn<Toimipiste, String> toimipisteNimiColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteLahiosoiteColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostinroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePostitoimipaikkaColumn;
    @FXML private TableColumn<Toimipiste, String> toimipistePuhelinnumeroColumn;
    @FXML private TableColumn<Toimipiste, String> toimipisteEmailColumn;
    @FXML private Button toimipisteLisaaUusiNappi;
    @FXML private Button toimipisteMuokkaaButton;
    @FXML private Button toimipistePoistaButton;
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Tässä aktivoidaan muokkaus ja poistonapit, kun taulusta valitaan rivi
        // Tämä tulee toteuttaa kaikille päänäkymän tauluille
        toimipisteetTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                toimipisteMuokkaaButton.setDisable(false);
                toimipistePoistaButton.setDisable(false);
            }
        });
        
        
        // Tehdään toimipiste-näkymälle PropertyValueFactoryt. Näiden avulla näkymä osaa käyttää objekteja.
        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("nimi"));
        toimipisteLahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("lahiosoite"));
        toimipistePostinroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postinro"));
        toimipistePostitoimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("postitoimipaikka"));
        toimipistePuhelinnumeroColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("puhelinnro"));
        toimipisteEmailColumn.setCellValueFactory(new PropertyValueFactory<Toimipiste, String>("email"));
        
        // TODO Tehdään sama muille näkymille
        
    }    
    
    
    /**
     * Tällä metodilla poistetaan kaikkien muokkaus ja poisto-nappien aktiivisuus
     * Metodia käytetään toiminnallisuuksien yhteydessä, missä taulukon valinta poistuu.
     * 
     * TODO: Tähän pitää lisätä kaikkien tulevien muokkaa ja poista nappien toiminnot
     * 
     * 16.4.2019 Lassi Puurunen
     */
    public void poistaNappienAktivoinnit() {
        toimipisteMuokkaaButton.setDisable(true);
        toimipistePoistaButton.setDisable(true);
    }

    
    
    
    // Toimipiste-näkymän metodit
    
    /**
     * Siirryttäessä toimipiste-välilehdelle, ladataan tietokannasta toimipisteiden tiedot
     * 
     * 15.4. 2019 Lassi Puurunen
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void lataaToimipisteet(Event event) {
        
        try {
            this.poistaNappienAktivoinnit();
            toimipisteetTableView.setItems(new ToimipisteDao().list());
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Uuden toimipisteen lisäys
     * 
     * Metodi avaa LisaaToimipisteFXML-ikkunan
     * 
     * 16.4. 2019 Lassi Puurunen
     * 
     * @param event 
     */
    @FXML
    private void lisaaUusiToimipiste(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ToimipisteFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lisää toimipiste");
            stage.setScene(new Scene(root1));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);

            stage.initStyle(StageStyle.UTILITY);
            
            //Seuraava komento laittaa säikeen odotustilaan, kunnes lisäysikkuna suljetaan
            stage.showAndWait();
            this.poistaNappienAktivoinnit();
            this.lataaToimipisteet(event);
                        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Valitun toimipisteen poisto-toiminto
     * 
     * 
     * 16.4.2019 Lassi Puurunen
     * 
     * @param event 
     */
    @FXML
    private void toimipistePoistaButtonOnAction(ActionEvent event) {
        try {
            new ToimipisteDao().delete(toimipisteetTableView.getSelectionModel().getSelectedItem().getToimipisteId());
            this.poistaNappienAktivoinnit();
            this.lataaToimipisteet(event);
        } catch (SQLException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

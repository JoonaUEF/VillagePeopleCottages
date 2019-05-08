/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.varaus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import villagepeoplecottages.interfaces.FXMLControllerInterface;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VarausFXMLController implements Initializable, FXMLControllerInterface<Varaus> {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label asiakasIdLabel;
    @FXML
    private Button tallennaButton;
    @FXML
    private Button peruutaButton;
    @FXML
    private AnchorPane palvelutVarauksetAnchorPane;
    @FXML
    private Tab palveluttTab;
    @FXML
    private TextField palvelutHakuTextField;
    @FXML
    private ComboBox<?> palvelutPalvelutyyppiComboBox;
    @FXML
    private Button palveluLisaaUusiButton;
    @FXML
    private Button palveluMuokkaaButton;
    @FXML
    private Button palveluPoistaButton;
    @FXML
    private TableView<?> palvelutTableView;
    @FXML
    private TableColumn<?, ?> palveluTyyppiColumn;
    @FXML
    private TableColumn<?, ?> palveluNimiColumn;
    @FXML
    private TableColumn<?, ?> palveluKuvausColumn;
    @FXML
    private TableColumn<?, ?> palveluHintaColumn;
    @FXML
    private TableColumn<?, ?> palveluAlvColumn;
    @FXML
    private TableColumn<?, ?> palveluHintaAlvColumn;
    @FXML
    private Tab laskutTab;
    @FXML
    private TextField laskutHakuTextField;
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
    private ToolBar tilapalkki;
    @FXML
    private Label tilaPalkkiLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void tallennaButtonOnAction(ActionEvent event) {
    }

    @FXML
    public void peruutaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palvelutPalvelutyyppiComboBoxOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluPoistaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palvelutTabOnSelectionChanged(Event event) {
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

    @Override
    public void initData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Varaus haeTietoLomakkeelta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

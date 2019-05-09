/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.lasku;

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
 *
 * @author User
 */
public class LaskuFXMLController implements Initializable, FXMLControllerInterface<Lasku> {
	private Lasku vanhaLasku;
	private LaskuFXMLService lfxmls = new LaskuFXMLService();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label laskuIdLabel;
    @FXML
    private Label varausIdLabel;
    @FXML
    private Label asiakasIdLabel;
    @FXML
    private Label asiakkaanNimiLabel;
    @FXML
    private ComboBox<?> laskunTilaComboBox;
    @FXML
    private Label laskuSummaLabel;
    @FXML
    private Label laskuAlvLabel;
    @FXML
    private Label laskuSummaAlvLabel;
    @FXML
    private Button tulostaLaskuButton;
    @FXML
    private Button tallennaButton1;
    @FXML
    private Button peruutaButton;
    @FXML
    private Tab palveluVarauksetTab;
    @FXML
    private TextField palveluVarauksetHakuTextField;
    @FXML
    private ComboBox<?> palveluVarauksetPalvelutyyppiComboBox;
    @FXML
    private DatePicker varauksetMistaDatePicker;
    @FXML
    private DatePicker varauksetMihinDatePicker;
    @FXML
    private Button varauksetLisaaUusiButton;
    @FXML
    private Button varauksetMuokkaaButton;
    @FXML
    private Button varauksetPoistaButton;
    @FXML
    private TableView<?> varauksetTableView;
    @FXML
    private TableColumn<?, ?> varausPalveluTyyppiColumn;
    @FXML
    private TableColumn<?, ?> varausPalvelunNimiColumn;
    @FXML
    private TableColumn<?, ?> varausPalvelunAlkuColumn;
    @FXML
    private TableColumn<?, ?> varausPalvelunLoppuColumn;
    @FXML
    private Label tilaPalkkiLabel;

    @Override
    public void initData(Object object) {
    	this.vanhaLasku = (Lasku)object;
    	asiakkaanNimiLabel.textProperty().set(vanhaLasku.getNimi());
    	laskuIdLabel.textProperty().set(Integer.toString(vanhaLasku.getLaskuId()));
    	varausIdLabel.textProperty().set(Integer.toString(vanhaLasku.getVarausId()));
    	asiakasIdLabel.textProperty().set(Integer.toString(vanhaLasku.getAsiakasId()));
    	laskuSummaLabel.textProperty().set(Double.toString(vanhaLasku.getSumma()));
    	laskuAlvLabel.textProperty().set(Double.toString(vanhaLasku.getAlv()));
    	laskuSummaAlvLabel.textProperty().set(Double.toString(vanhaLasku.getAlv() + vanhaLasku.getSumma()));
    	//laskunTilaComboBox.setValue(vanhaLasku.get);
    	
    	
    	
    	
    }

    @Override
    public Lasku haeTietoLomakkeelta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void tallennaButtonOnAction(ActionEvent event) throws SQLException {
    	lfxmls.tallennaButton(vanhaLasku, haeTietoLomakkeelta());
        	
        //sulje ikkuna
        this.peruutaButtonOnAction(event);
    }

    @FXML
    private void peruutaButtonOnAction(ActionEvent event) {
        //sulkee ikkunan
        
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void tulostaLaskuButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluVarauksetPalvelutyyppiComboBoxOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluVarauksetMistaDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluVarauksetMihinDatePickerOnAction(ActionEvent event) {
    }

    @FXML
    private void varauksetLisaaUusiButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void varauksetMuokkaaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void varauksetPoistaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void palveluVarauksetTabOnSelectionChanged(Event event) {
    }
    
}

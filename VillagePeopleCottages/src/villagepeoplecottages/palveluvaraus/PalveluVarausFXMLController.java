/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.palveluvaraus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import villagepeoplecottages.interfaces.FXMLControllerInterface;

/**
 *
 * @author User
 */
public class PalveluVarausFXMLController implements FXMLControllerInterface<PalveluVaraus>{

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ComboBox<?> toimipisteComboBox;
    @FXML
    private ComboBox<?> tyyppiComboBox;
    @FXML
    private Button tallennaButton1;
    @FXML
    private Button peruutaButton;
    @FXML
    private Label tilaPalkkiLabel;

    public void initData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PalveluVaraus haeTietoLomakkeelta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void tallennaButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void peruutaButtonOnAction(ActionEvent event) {
    }
    
}

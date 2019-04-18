/**
 * Main -metodin sisältävä luokka.
 * 
 * Täällä käynnistetään käyttöliittymä.
 * 
 * 
 * Versiohistoria
 * 8.4.2019 tehnyt Lassi Puurunen
 * 15.4.2019 päivitetty JavaFX:ään 
 * 18.4.2019 Lisätty title
 * 
 * 
 */

package villagepeoplecottages;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VillagePeopleCottages extends Application {
       
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("VillagePeopleCottages");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

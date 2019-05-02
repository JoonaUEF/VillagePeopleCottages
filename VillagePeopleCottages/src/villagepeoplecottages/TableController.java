/**
 * Taulujen rajaus- ja hakutoiminnallisuudet
 * 
 * 2.5.2019  toimipistenäkymän hakutoiminto toteutettu. Lassi Puurunen
 */
package villagepeoplecottages;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;

/**
 *
 * @author Lassi Puurunen
 */
public class TableController {

    // Määritetään toimipistenäkymän tiedot
    private ObservableList<Toimipiste> toimipisteMasterData = FXCollections.observableArrayList();
    private FilteredList<Toimipiste> toimipisteFilteredData;
    private SortedList<Toimipiste> toimipisteSortedData;

    
    public void initializeToimipisteTable(TableView tv) throws SQLException {
        toimipisteMasterData = new ToimipisteDao().list();

        toimipisteFilteredData = new FilteredList<>(toimipisteMasterData, p -> true);
        
        toimipisteSortedData = new SortedList<>(toimipisteFilteredData);
        
        toimipisteSortedData.comparatorProperty().bind(tv.comparatorProperty());
        
        tv.setItems(toimipisteSortedData);
    }
    
    public void toimipisteHakuListener(String newValue) {
        toimipisteFilteredData.setPredicate(toimipiste -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (toimipiste.getNimi().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches toimipisteen nimi.
                } else if (toimipiste.getLahiosoite().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (toimipiste.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (toimipiste.getPostinro().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (toimipiste.getPostitoimipaikka().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (toimipiste.getPuhelinnro().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                
                return false; // Does not match.
            });
    }
    

    
    
}

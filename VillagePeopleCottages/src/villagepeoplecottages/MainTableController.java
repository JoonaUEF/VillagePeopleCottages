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
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;

/**
 *
 * @author Lassi Puurunen
 */
public class MainTableController {

    // Määritetään toimipistenäkymän tiedot
    private ObservableList<Toimipiste> toimipisteMasterData = FXCollections.observableArrayList();
    private FilteredList<Toimipiste> toimipisteFilteredData;
    private SortedList<Toimipiste> toimipisteSortedData;
    
    // Määritetään palvelunäkymän tiedot
    private ObservableList<Palvelu> palveluMasterData = FXCollections.observableArrayList();
    private FilteredList<Palvelu> palveluFilteredData;
    private SortedList<Palvelu> palveluSortedData;
    
    public void initializeTable(Object object, TableView tv) throws SQLException {
        
        if (object instanceof Toimipiste) {
            toimipisteMasterData = new ToimipisteDao().list();
            toimipisteFilteredData = new FilteredList<>(toimipisteMasterData, p -> true);
            toimipisteSortedData = new SortedList<>(toimipisteFilteredData);
            toimipisteSortedData.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(toimipisteSortedData);
            
        }
        
        if (object instanceof Palvelu) {
            palveluMasterData = new PalveluDao().list();
            palveluFilteredData = new FilteredList<>(palveluMasterData, p -> true);
            palveluSortedData = new SortedList<>(palveluFilteredData);
            palveluSortedData.comparatorProperty().bind(tv.comparatorProperty());
            tv.setItems(palveluSortedData);
            
        }
    }

    public FilteredList<Toimipiste> getToimipisteFilteredData() {
        return toimipisteFilteredData;
    }

    public FilteredList<Palvelu> getPalveluFilteredData() {
        return palveluFilteredData;
    }
    
    
    
    
}

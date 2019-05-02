package villagepeoplecottages.toimipiste;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import villagepeoplecottages.MainFXMLController;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;

/**
 *
 * @author Lassi Puurunen
 */
public class ToimipisteTableController extends MainFXMLController {

    // Määritetään palvelunäkymän tiedot
    private ObservableList<Palvelu> palveluMasterData;
    private FilteredList<Palvelu> palveluFilteredData;
    private SortedList<Palvelu> palveluSortedData;

    public ToimipisteTableController() {
    }
    
   
    public void initializeTable(Palvelu palvelu, Toimipiste selectedToimipiste, TableView<Palvelu> tv) throws SQLException {
        palveluMasterData = new PalveluDao().listByToimipisteId(selectedToimipiste.getToimipisteId());
        palveluFilteredData = new FilteredList<>(palveluMasterData, p -> true);
        palveluSortedData = new SortedList<>(palveluFilteredData);
        palveluSortedData.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(palveluSortedData);
    }

    public FilteredList<Palvelu> getPalveluFilteredData() {
        return palveluFilteredData;
    }
    
    
    
}

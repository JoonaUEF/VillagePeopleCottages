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
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.palveluvaraus.PalveluVarausDao;

/**
 *
 * @author Lassi Puurunen
 */
public class ToimipisteTableController extends MainFXMLController {

    // Määritetään palvelunäkymän tiedot
    private ObservableList<Palvelu> palveluMasterData;
    private FilteredList<Palvelu> palveluFilteredData;
    private SortedList<Palvelu> palveluSortedData;
    
    // Määritetään varausnäkymän tiedot
    private ObservableList<PalveluVaraus> pvMasterData;
    private FilteredList<PalveluVaraus> pvFilteredData;
    private SortedList<PalveluVaraus> pvSortedData;

    public ToimipisteTableController() {
    }
    
   
    public void initializeTable(Palvelu palvelu, Toimipiste selectedToimipiste, TableView<Palvelu> tv) throws SQLException {
        palveluMasterData = new PalveluDao().listByToimipisteId(selectedToimipiste.getToimipisteId());
        palveluFilteredData = new FilteredList<>(palveluMasterData, p -> true);
        palveluSortedData = new SortedList<>(palveluFilteredData);
        palveluSortedData.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(palveluSortedData);
    }
    
    public void initializeTable(PalveluVaraus pv, Toimipiste selectedToimipiste, TableView<PalveluVaraus> tv) throws SQLException {
        pvMasterData = new PalveluVarausDao().listByToimipisteId(selectedToimipiste.getToimipisteId());
        pvFilteredData = new FilteredList<>(pvMasterData, p -> true);
        pvSortedData = new SortedList<>(pvFilteredData);
        pvSortedData.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(pvSortedData);
    }

    public FilteredList<Palvelu> getPalveluFilteredData() {
        return palveluFilteredData;
    }
    
    
    
}

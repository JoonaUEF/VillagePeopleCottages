package villagepeoplecottages.interfaces;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public interface FXMLController<T, K> extends Initializable {
	void initData(Object object);
	T haeTietoLomakkeelta();
	void tallennaButtonAction(ActionEvent event) throws SQLException;
	void peruutaButtonAction(ActionEvent event);
	
}

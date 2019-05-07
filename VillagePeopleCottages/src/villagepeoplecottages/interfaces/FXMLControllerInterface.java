package villagepeoplecottages.interfaces;

import javafx.fxml.Initializable;

public interface FXMLControllerInterface<T> extends Initializable {
	void initData(Object object);
	T haeTietoLomakkeelta();
}

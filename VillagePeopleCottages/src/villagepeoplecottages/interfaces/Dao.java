/**
 * Dao -rajapinta ObjectDao:ille.
 * 
 * Tekijä: Lassi Puurunen 8.4.2019
 * 
 */
package villagepeoplecottages.interfaces;

import java.sql.SQLException;
import javafx.collections.ObservableList;


public interface Dao<T, K> {
    void create(T object) throws SQLException;
    T read(K key) throws SQLException;
    T update(T object) throws SQLException;
    void delete(K key) throws SQLException;
    ObservableList<T> list() throws SQLException;
}

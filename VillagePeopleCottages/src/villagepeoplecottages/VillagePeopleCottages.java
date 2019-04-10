/**
 * Main -metodin sisältävä luokka.
 * 
 * Täällä käynnistetään käyttöliittymä.
 * 
 * Tekijä: Lassi Puurunen 8.4.2019
 * 
 */

package villagepeoplecottages;

import java.sql.SQLException;

public class VillagePeopleCottages {
       
    public static void main(String[] args) throws SQLException {
        new TestiController().start();
    }
    
}

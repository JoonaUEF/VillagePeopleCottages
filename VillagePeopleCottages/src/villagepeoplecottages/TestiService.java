/**
 * TestiService -luokka on TestiController -luokan käyttämä palvelu, joka toteuttaa
 * metodeja testikäyttöön.
 * 
 * Lopulliset Service -luokat toteuttavat metodit, joita JavaFX-käyttöliittymässä tarvitaan.
 * 
 * Jokaiselle käyttöliittymän ikkunalle toteutetaan vastaava Service-luokka.
 * 
 * Tekijä: Lassi Puurunen 8.4.2019
 * 
 */
package villagepeoplecottages;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestiService {
    
    private ToimipisteDao toimipisteDao;

    public TestiService() {
        toimipisteDao = new ToimipisteDao();
    }

    /**
     * Controller -luokka käyttää tätä metodia uuden toimipisteen viemiseen tietokantaan.
     * 
     * @param nimi
     * @param lahiosoite
     * @param postitoimipaikka
     * @param postinro
     * @param email
     * @param puhelinnro
     * @throws SQLException 
     */
    public void createToimipiste(String nimi, String lahiosoite, 
            String postitoimipaikka, String postinro, String email, 
            String puhelinnro) throws SQLException {
        
        toimipisteDao.create(new Toimipiste(nimi, lahiosoite, postitoimipaikka, 
                postinro, email, puhelinnro));
        
    }

    /**
     * Controller -luokka käyttää tätä metodia yksittäisen toimipisteen hakemiseen
     * tietokannasta avaimen perusteella
     * @param key
     * @throws SQLException 
     */
    public Toimipiste getToimipiste(int key) throws SQLException {
        return toimipisteDao.read(key);
    }
    
}

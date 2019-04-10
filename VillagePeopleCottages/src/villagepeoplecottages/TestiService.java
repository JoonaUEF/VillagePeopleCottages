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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestiService {
    
    private ToimipisteDao toimipisteDao;

    public TestiService() {
        toimipisteDao = new ToimipisteDao();
    }

    /**
     * Controller -luokka käyttää metodia uuden toimipisteen tallentamiseksi
     * tietokantaan.
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
     * Controller -luokka käyttää metodia yksittäisen toimipisteen hakemiseen
     * tietokannasta avaimen perusteella
     * @param key
     * @throws SQLException 
     */
    public Toimipiste getToimipiste(int key) throws SQLException {
        return toimipisteDao.read(key);
    }
    
    /**
     * Controller -luokka käyttää metodia yksittäisen toimipisteen päivittämiseen
     * tietokannassa.
     * Metodille syötetään toimipiste -olio, joka päivitetään.
     * Metodi palauttaa saman olion takaisin.
     * 
     * @param toimipiste
     * @return toimipiste
     * @throws SQLException 
     */
    public Toimipiste updateToimipiste(Toimipiste t) throws SQLException {
        return toimipisteDao.update(t);
    }
    
    
    /**
     * Controller -luokka käyttää metodia yksittäisen toimipisteen päivittämiseen
     * tietokannassa.
     * Paramerina annetaan toimipisteen id.
     * 
     * @param key
     * @throws SQLException 
     */
    public void deleteToimipiste(int key) throws SQLException {
        toimipisteDao.delete(key);
    }
    
    /**
     * Controller -luokka käyttää metodia toimipisteiden listan hakemiseen tietokannasta
     * 
     */
    public List<Toimipiste> listToimipiste() throws SQLException {
        return toimipisteDao.list();
    }
    
    
}

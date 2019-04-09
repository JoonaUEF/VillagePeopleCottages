/**
 * Täällä voidaan toteuttaa ohjelmiston testausta ennen käyttöliittymän toteutusta.
 * 
 * Controller -luokka "simuloi" JavaFX:n käyttöliittymää
 * 
 * Tekijä: Lassi Puurunen 8.4.2019
 * 
 */
package villagepeoplecottages;

import java.sql.SQLException;

public class TestiController {
    
    //Tähän tuodaan Controllerin tarvitsemat palvelut
    private TestiService testiService;
    
    public TestiController() {
        testiService = new TestiService();
    }

    
    
    public void start() throws SQLException {
        // Tämä käynnistyy automaattisesti ohjelman käynnistyessä.
        // Oikeassa ohjelmassa käyttöliitymä käynnistää controllerin eri metodeja
        
        createToimipiste();
        getToimipiste();
 
    }
    
    public void createToimipiste() throws SQLException {
        //parametrit saadaan oikeassa ohjelmassa käyttöliittymältä
        String nimi = "VillagePeople Ruka";
        String lahiosoite = "Rukatunturintie 5";
        String postitoimipaikka = "RUKATUNTURI";
        String postinro = "93830";
        String email = "esimerkki@esimerkki.fi";
        String puhelinnro = "012-345 6789";
        
        testiService.createToimipiste(nimi, lahiosoite, postitoimipaikka, 
                postinro, email, puhelinnro);
    }

    public void getToimipiste() throws SQLException{
        System.out.println(testiService.getToimipiste(1));
    }
    
}

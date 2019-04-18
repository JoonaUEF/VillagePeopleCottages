package villagepeoplecottages;

import java.sql.SQLException;

/**
 * ToimipisteFXMLService
 * 
 * Toimintoja ToimipisteFXMLController:lle
 * 
 * 
 * 18.4.2019 Lassi Puurunen
 * 
 */



public class ToimipisteFXMLService {
    
    
    public void tallennaButton(Toimipiste vanha, Toimipiste uusi) throws SQLException {
        // Tekee uuden toimipisteen tietokantaan, jos vanha on tyhjä.
        // Muutoin päivittää objektin tietoja
        
        if (vanha == null) {
            new ToimipisteDao().create(uusi);
        } else {
            Toimipiste paivitettava = new ToimipisteDao().read(vanha.getToimipisteId());
            paivitettava.setNimi(uusi.getNimi());
            paivitettava.setLahiosoite(uusi.getLahiosoite());
            paivitettava.setPostinro(uusi.getPostinro());
            paivitettava.setPostitoimipaikka(uusi.getPostitoimipaikka());
            paivitettava.setPuhelinnro(uusi.getPuhelinnro());
            paivitettava.setEmail(uusi.getEmail());
            
            new ToimipisteDao().update(paivitettava);
        }
        
        
    }
}

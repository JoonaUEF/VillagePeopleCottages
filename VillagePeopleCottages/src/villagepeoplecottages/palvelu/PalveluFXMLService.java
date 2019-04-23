package villagepeoplecottages.palvelu;

import java.sql.SQLException;

/**
 * PalveluFXMLService
 * 
 * Toimintoja PalveluFXMLController:lle
 * 
 * 
 * 20.4.2019 Joona Honkanen muokaten ToimipisteFXMLService:ä.
 * 
 */



public class PalveluFXMLService {
    
    
    public void tallennaButton(Palvelu vanha, Palvelu uusi) throws SQLException {
        // Tekee uuden palvelun tietokantaan, jos vanha on tyhjä.
        // Muutoin päivittää objektin tietoja
        
        if (vanha == null) {
            new PalveluDao().create(uusi);
        } else {
            Palvelu paivitettava = new PalveluDao().read(vanha.getPalveluId());
            paivitettava.setToimipisteId(vanha.getToimipisteId());
            paivitettava.setNimi(uusi.getNimi());
            paivitettava.setTyyppi(vanha.getTyyppi());
            paivitettava.setKuvaus(vanha.getKuvaus());
            paivitettava.setHinta(uusi.getHinta());
            paivitettava.setAlv(uusi.getAlv());
            
            new PalveluDao().update(paivitettava);
        }
        
        
    }
}

package villagepeoplecottages;

import java.sql.SQLException;

/**
 * AsiakasFXMLService
 * 
 * Toimintoja AsiakasFXMLController:lle
 * 
 * 
 * 18.4.2019 Lassi Puurunen
 * 
 */



public class AsiakasFXMLService {
    
    
    public void tallennaButton(Asiakas vanha, Asiakas uusi) throws SQLException {
        // Tekee uuden toimipisteen tietokantaan, jos vanha on tyhjä.
        // Muutoin päivittää objektin tietoja
        
        if (vanha == null) {
            new AsiakasDao().create(uusi);
        } else {
            Asiakas paivitettava = new AsiakasDao().read(vanha.getAsiakasId());
            paivitettava.setEtunimi(uusi.getEtunimi());
            paivitettava.setSukunimi(uusi.getSukunimi());
            paivitettava.setLahiosoite(uusi.getLahiosoite());
            paivitettava.setPostinro(uusi.getPostinro());
            paivitettava.setPostitoimipaikka(uusi.getPostitoimipaikka());
            paivitettava.setPuhelinnro(uusi.getPuhelinnro());
            paivitettava.setEmail(uusi.getEmail());
            
            new AsiakasDao().update(paivitettava);
        }
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author User
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

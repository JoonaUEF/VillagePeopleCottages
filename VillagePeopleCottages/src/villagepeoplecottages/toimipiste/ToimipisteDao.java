/**
 * ToimipisteDao-luokka toteuttaa rajapinnan Toimipiste-objektin ja tietokannan välille.
 * 
 * 
 * Versiohistoria
 * 9.4.2019 Tiedosto luotu. Lassi Puurunen.
 * 10.4. CRUD-toiminnallisuus valmiina. Lassi Puurunen.
 *          
 * 
 */
package villagepeoplecottages.toimipiste;

import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.palvelu.PalveluDao;
import com.sun.javafx.collections.ObservableListWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.varaus.VarausDao;


public class ToimipisteDao implements Dao<Toimipiste, Integer>{

    
    /**
     * Metodi toimipisteen tallentamiselle tietokantaan.
     * 
     * @param toimipiste
     * @throws SQLException 
     */
    @Override
    public void create(Toimipiste toimipiste) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Toimipiste"
            + " (nimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro)"
            + " VALUES (?, ?, ?, ?, ?, ?)");
        
        stmt.setString(1, toimipiste.getNimi());
        stmt.setString(2, toimipiste.getLahiosoite());
        stmt.setString(3, toimipiste.getPostitoimipaikka());
        stmt.setString(4, toimipiste.getPostinro());
        stmt.setString(5, toimipiste.getEmail());
        stmt.setString(6, toimipiste.getPuhelinnro());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    
    /**
     * Metodi Toimipisteen hakemiselle tietokannasta.
     * 
     * @param key
     * @return Toimipiste
     * @throws SQLException 
     */
    @Override
    public Toimipiste read(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Toimipiste WHERE toimipiste_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan null-viite
        if(!rs.next()) {
            return null;
        }

        // Edellä siirryttiin ensimmäiselle tulostaulun
        // riville -- luodaan toimipiste
        
        // Haetaan ensin listaukset toimipisteisiin liittyvistä palveluista ja varauksista
        
        // Ylemmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia 
        // ja metodeja ei vielä toteutettu.
        // Laitetaan tilalle tyhjät listat.
        
        int toimipisteId = rs.getInt("toimipiste_id");


        Toimipiste toimipiste = new Toimipiste(toimipisteId, rs.getString("nimi"), 
                rs.getString("lahiosoite"), rs.getString("postitoimipaikka"), 
                rs.getString("postinro"), rs.getString("email"), 
                rs.getString("puhelinnro"));

        stmt.close();
        rs.close();
        connection.close();
        
        //Lisää Toimipisteelle siihen kuuluvat palvelut ja varaukset
        toimipiste.setPalvelut(new PalveluDao().listByToimipisteId(toimipisteId));
        toimipiste.setVaraukset(new VarausDao().listByToimipisteId(toimipisteId));

        return toimipiste;
    }

    
    /**
     * Metodi parametrina annetun toimipisteen päivittämiseksi tietokannassa.
     * 
     * @param toimipiste
     * @return toimipiste
     * @throws SQLException 
     */
    @Override
    public Toimipiste update(Toimipiste toimipiste) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("UPDATE Toimipiste"
            + " SET nimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, email = ?, puhelinnro = ?"
            + " WHERE toimipiste_id = ?");
        
        stmt.setString(1, toimipiste.getNimi());
        stmt.setString(2, toimipiste.getLahiosoite());
        stmt.setString(3, toimipiste.getPostitoimipaikka());
        stmt.setString(4, toimipiste.getPostinro());
        stmt.setString(5, toimipiste.getEmail());
        stmt.setString(6, toimipiste.getPuhelinnro());
        stmt.setInt(7, toimipiste.getToimipisteId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        
        return toimipiste;
    }

    
    /**
     * Metodi toimipisteen poistamiseksi tietokannasta.
     * Metodille syötetään parametrina poistettavan toimisteen id.
     * 
     * @param key
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Toimipiste"
            + " WHERE toimipiste_id = ?");
        
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    
    /**
     * Metodi tietokannan taulussa olevien toimipisteiden listan hakemiseksi.
     * 
     * @return List<Toimipiste>
     * @throws SQLException 
     */
    @Override
    public ObservableList<Toimipiste> list() throws SQLException {
        List<Toimipiste> toimipisteet = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Toimipiste");
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            toimipisteet.add(new Toimipiste(rs.getInt("toimipiste_id"), rs.getString("nimi"),
                    rs.getString("lahiosoite"), rs.getString("postitoimipaikka"),
                    rs.getString("postinro"), rs.getString("email"), rs.getString("puhelinnro")));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<Toimipiste> observableToimipiste = FXCollections.observableArrayList();
        
        observableToimipiste.addAll(toimipisteet);
        
        
        //Lisää Toimipisteille niihin kuuluvat palvelut ja varaukset
        
        for (Toimipiste toimipiste : observableToimipiste) {
            
            toimipiste.setPalvelut(new PalveluDao().listByToimipisteId(toimipiste.getToimipisteId()));
            toimipiste.setVaraukset(new VarausDao().listByToimipisteId(toimipiste.getToimipisteId()));
        }
        
        return observableToimipiste;
    }
    
}

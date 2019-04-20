/**
 * PalveluDao-luokka toteuttaa rajapinnan Palvelu-objektin ja tietokannan välille.
 * 
 * 
 * Versiohistoria
 * 20.4.19 Tiedosto luotu ToimipisteDaon pohjalta. Joona Honkanen.
 * 
 * 
 */
package villagepeoplecottages;

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


public class PalveluDao implements Dao<Palvelu, Integer>{

    
    /**
     * Metodi palvelun tallentamiselle tietokantaan.
     * 
     * @param palvelu Palvelu-luokka
     * @throws SQLException 
     */
    @Override
    public void create(Palvelu palvelu) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Palvelu"
            + " (nimi, tyyppi, kuvaus, hinta, alv)"
            + " VALUES (?, ?, ?, ?, ?)");
        
        stmt.setString(1, palvelu.getNimi());
        stmt.setInt(2, palvelu.getTyyppi());
        stmt.setString(3, palvelu.getKuvaus());
        stmt.setDouble(4, palvelu.getHinta());
        stmt.setDouble(5, palvelu.getAlv());

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
    public Palvelu read(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Palvelu WHERE palvelu_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan null-viite
        if(!rs.next()) {
            return null;
        }

        // Edellä siirryttiin ensimmäiselle tulostaulun
        // riville -- luodaan palvelu
        
        // Haetaan ensin listaukset palveluihin liittyvistä varausten palveluista
        
        // Ylemmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia 
        // ja metodeja ei vielä toteutettu.
        // Laitetaan tilalle tyhjät listat.
        
        int palveluId = rs.getInt("palvelu_id");
        // List<VarauksenPalvelut> varauksenPalvelut = new VaraustenPalveluDao().listByToimipisteId(toimipisteId);
        List<VarauksenPalvelut> varauksenPalvelut = new ArrayList<>();

        Palvelu palvelu = new Palvelu(palveluId, rs.getInt("toimipiste_id"), 
            rs.getString("nimi"),rs.getInt("tyyppi"), rs.getString("kuvaus"), 
            rs.getDouble("hinta"), rs.getDouble("alv"), varauksenPalvelut);

        stmt.close();
        rs.close();
        connection.close();

        return palvelu;
    }

    
    /**
     * Metodi parametrina annetun palvelun päivittämiseksi tietokannassa.
     * 
     * @param palvelu Palvelu
     * @return toimipiste
     * @throws SQLException 
     */
    @Override
    public Palvelu update(Palvelu palvelu) throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("UPDATE Palvelu"
            + " SET toimipiste_id = ?, nimi = ?, tyyppi = ?, kuvaus = ?, hinta = ?, alv = ?"
            + " WHERE palvelu_id = ?");
        
        stmt.setInt(1, palvelu.getToimipisteId());
        stmt.setString(2, palvelu.getNimi());
        stmt.setInt(3, palvelu.getTyyppi());
        stmt.setString(4, palvelu.getKuvaus());
        stmt.setDouble(5, palvelu.getHinta());
        stmt.setDouble(6, palvelu.getAlv());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        
        return palvelu;
    }

    
    /**
     * Metodi palvelun poistamiseksi tietokannasta.
     * Metodille syötetään parametrina poistettavan palvelun id.
     * 
     * @param key
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Palvelu"
            + " WHERE palvelu_id = ?");
        
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    
    /**
     * Metodi tietokannan taulussa olevien palveluiden listan hakemiseksi.
     * 
     * @return List<Toimipiste> Listaus toimipisteistä
     * @throws SQLException 
     */
    @Override
    public ObservableList<Palvelu> list() throws SQLException {
        List<Palvelu> palvelut = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Palvelu");
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            palvelut.add(new Palvelu(rs.getInt("palvelu_id"), rs.getInt("toimipiste_id"), rs.getString("nimi"),
                    rs.getInt("tyyppi"), rs.getString("kuvaus"),
                    rs.getDouble("hinta"), rs.getDouble("alv")));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<Palvelu> observablePalvelu = FXCollections.observableArrayList();
        
        observablePalvelu.addAll(palvelut);
        
        return observablePalvelu;
    }
    
}

/**
 * AsiakasDao-luokka toteuttaa rajapinnan Asiakas-objektin ja tietokannan välille.
 * 
 * Tekijä: Lassi Puurunen 8.4.2019
 * 
 */
package villagepeoplecottages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AsiakasDao implements Dao<Asiakas, Integer> {

    @Override
    public void create(Asiakas asiakas) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Asiakas"
            + " (etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        stmt.setString(1, asiakas.getEtunimi());
        stmt.setString(2, asiakas.getSukunimi());
        stmt.setString(3, asiakas.getLahiosoite());
        stmt.setString(4, asiakas.getPostitoimipaikka());
        stmt.setString(5, asiakas.getPostinro());
        stmt.setString(6, asiakas.getEmail());
        stmt.setString(7, asiakas.getPuhelinnro());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
    }

    @Override
    public Asiakas read(Integer key) throws SQLException {
    	Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Asiakas WHERE asiakas_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan null-viite
        if(!rs.next()) {
            return null;
        }

        // Edellä siirryttiin ensimmäiselle tulostaulun
        // riville -- luodaan asiakas
        
        // Haetaan ensin listaukset asiakkaisiin liittyvistä laskuista ja varauksista
        
        // Ylemmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia 
        // ja metodeja ei vielä toteutettu.
        // Laitetaan tilalle tyhjät listat.
        
        int asiakasId = rs.getInt("asiakas_id");
        
        // List<Lasku> laskut = new PalveluDao().listByToimipisteId(toimipisteId);
        // List<Varaus> varaukset = new VarausDao().listByToimipisteId(toimipisteId);
        
        List<Lasku> laskut = new ArrayList<>();
        List<Varaus> varaukset = new ArrayList<>();

        Asiakas asiakas = new Asiakas(asiakasId,
        		rs.getString("etunimi"), rs.getString("sukunimi"),
                rs.getString("lahiosoite"), rs.getString("postitoimipaikka"), 
                rs.getString("postinro"), rs.getString("email"), 
                rs.getString("puhelinnro"), varaukset, laskut);

        stmt.close();
        rs.close();
        connection.close();

        return asiakas;
    }

    @Override
    public Asiakas update(Asiakas asiakas) throws SQLException {
    	
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("UPDATE Asiakas"
            + " SET etunimi = ?, sukunimi=?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, email = ?, puhelinnro = ?"
            + " WHERE asiakas_id = ?");
        
        stmt.setString(1, asiakas.getEtunimi());
        stmt.setString(2, asiakas.getSukunimi());
        stmt.setString(3, asiakas.getLahiosoite());
        stmt.setString(4, asiakas.getPostitoimipaikka());
        stmt.setString(5, asiakas.getPostinro());
        stmt.setString(6, asiakas.getEmail());
        stmt.setString(7, asiakas.getPuhelinnro());
        stmt.setInt(8, asiakas.getAsiakasId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        
        return asiakas;    }

    @Override
    public void delete(Integer key) throws SQLException {
    	Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Asiakas"
            + " WHERE asiakas_id = ?");
        
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
        }

    @Override
    public ObservableList<Asiakas> list() throws SQLException {
    	List<Asiakas> asiakkaat = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Asiakas");
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            asiakkaat.add(new Asiakas(rs.getInt("asiakas_id"),
            		rs.getString("etunimi"), rs.getString("sukunimi"),
                    rs.getString("lahiosoite"), rs.getString("postitoimipaikka"),
                    rs.getString("postinro"), rs.getString("email"), rs.getString("puhelinnro")));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<Asiakas> observableAsiakas = FXCollections.observableArrayList();
        
        observableAsiakas.addAll(asiakkaat);
        
        return observableAsiakas;    }
    
}

/**
 * ToimipisteDao-luokka toteuttaa rajapinnan Toimipiste-objektin ja tietokannan välille.
 * 
 * 
 * Versiohistoria
 * 9.4.2019 Tekijä: Lassi Puurunen
 *          
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToimipisteDao implements Dao<Toimipiste, Integer>{

    /**
     * Metodi toimipisteen tallentamiselle tietokantaan.
     * 
     * @param toimipiste
     * @throws SQLException 
     */
    @Override
    public void create(Toimipiste toimipiste) throws SQLException {
        
        int maxId = maxId();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Toimipiste"
            + " (toimipiste_id, nimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        stmt.setInt(1, maxId + 1);
        stmt.setString(2, toimipiste.getNimi());
        stmt.setString(3, toimipiste.getLahiosoite());
        stmt.setString(4, toimipiste.getPostitoimipaikka());
        stmt.setString(5, toimipiste.getPostinro());
        stmt.setString(6, toimipiste.getEmail());
        stmt.setString(7, toimipiste.getPuhelinnro());

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
        
        // ylimmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia 
        // ja metodeja ei vielä toteutettu,
        // Laitetaan tilalle tyhjät listat.
        
        int toimipisteId = rs.getInt("toimipiste_id");
        
//        List<Palvelu> palvelut = new PalveluDao().listByToimipisteId(toimipisteId);
//        List<Varaus> varaukset = new VarausDao().listByToimipisteId(toimipisteId);
        
        List<Palvelu> palvelut = new ArrayList<>();
        List<Varaus> varaukset = new ArrayList<>();

        Toimipiste toimipiste = new Toimipiste(toimipisteId, rs.getString("nimi"), 
                rs.getString("lahiosoite"), rs.getString("postitoimipaikka"), 
                rs.getString("postinro"), rs.getString("email"), 
                rs.getString("puhelinnro"), palvelut, varaukset);

        stmt.close();
        rs.close();
        connection.close();

        return toimipiste;
    }

    @Override
    public Toimipiste update(Toimipiste object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Toimipiste> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Metodi hakee kyseisen tauluun liittyvän suurimman Id:n tietokannasta.
     * Tämä metodi on tarkoitettu create-metodin käyttöön.
     * 
     * Mikäli taulu on tyhjä, metodi palauttaa arvon 0
     * 
     * @return maxId
     * @throws SQLException 
     */
    private int maxId() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT MAX(toimipiste_Id) FROM Toimipiste");
        ResultSet rs = stmt.executeQuery();
        
        int maxId = 0;
        
        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan 0
        if(!rs.next()) {
            return 0;
        }
        
        //Asetetaan maxId:lle rivin 1. arvo
        maxId = rs.getInt(1);
        
        stmt.close();
        rs.close();
        connection.close();

        return maxId;
    }
    
}

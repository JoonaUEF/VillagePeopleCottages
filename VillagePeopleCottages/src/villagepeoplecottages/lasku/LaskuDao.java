/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.lasku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.varaus.Varaus;

/**
 *
 * @author User
 */
public class LaskuDao implements Dao<Lasku, Integer>{

    public LaskuDao() {
    }

    @Override
    public void create(Lasku lasku) throws SQLException {
    	Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");
	
	    PreparedStatement stmt = connection.prepareStatement("INSERT INTO Lasku"
	        + " (nimi, lahiosoite, postitoimipaikka, postinro, asiakas_Id, varaus_id, tila, summa, alv)"
	        + " VALUES (?,?,?,?,?,?,?,?,?)");
	    
	    stmt.setString(1, lasku.getNimi());
	    stmt.setString(2, lasku.getLahiosoite());
	    stmt.setString(3, lasku.getPostitoimipaikka());
	    stmt.setString(4, lasku.getPostinro());
	    stmt.setInt(5, lasku.getAsiakasId());
	    stmt.setInt(6, lasku.getVarausId());
	    stmt.setInt(7, lasku.getTila());
	    stmt.setDouble(8, lasku.getSumma());
	    stmt.setDouble(9, lasku.getAlv());
	    
	
	    stmt.executeUpdate();
	    stmt.close();
	    connection.close();
    }

    @Override
    public Lasku read(Integer key) throws SQLException {
    	Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Lasku WHERE lasku_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan null-viite
        if(!rs.next()) {
            return null;
        }

        // Edellä siirryttiin ensimmäiselle tulostaulun
        // riville -- luodaan lasku
        
        // Haetaan ensin listaukset asiakkaisiin liittyvistä laskuista ja varauksista
        
        // Ylemmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia 
        // ja metodeja ei vielä toteutettu.
        // Laitetaan tilalle tyhjät listat.
        
        int laskuId = rs.getInt("lasku_id");
        
        // List<Lasku> laskut = new PalveluDao().listByToimipisteId(toimipisteId);
        // List<Varaus> varaukset = new VarausDao().listByToimipisteId(toimipisteId);
        
        //List<Lasku> laskut = new ArrayList<>();
        //List<Varaus> varaukset = new ArrayList<>();

        Lasku lasku = new Lasku(laskuId, rs.getInt("varaus_id"),
        		rs.getInt("asiakas_id"), rs.getString("nimi"),
                rs.getString("lahiosoite"), rs.getString("postitoimipaikka"), 
                rs.getString("postinro"), rs.getDouble("summa"), 
                rs.getDouble("alv"));

        stmt.close();
        rs.close();
        connection.close();

        return lasku;
    }

    @Override
    public Lasku update(Lasku object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Lasku> list() throws SQLException {
        ObservableList<Lasku> observableLasku = FXCollections.observableArrayList();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT Lasku.*, Toimipiste.nimi AS toimipiste FROM Lasku\n" +
                                                            "JOIN Toimipiste ON Lasku.toimipiste_id = Toimipiste.toimipiste_id;");
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return observableLasku;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            observableLasku.add(new Lasku(rs.getInt("lasku_id"), rs.getInt("varaus_id"), rs.getInt("asiakas_id"), rs.getInt("tila"), rs.getString("nimi"), rs.getString("lahiosoite"), rs.getString("postitoimipaikka"), rs.getString("postinro"), rs.getDouble("summa"), rs.getDouble("alv"), rs.getDate("paivays"), rs.getString("toimipiste")));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
       
        return observableLasku;
    }
    
}

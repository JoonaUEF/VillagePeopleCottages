/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.varaus;

import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.lasku.Lasku;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class VarausDao implements Dao<Varaus, Integer>{

    public VarausDao() {
    }

    public ObservableList<Varaus> listByToimipisteId(int toimipisteId) throws SQLException {
        List<Varaus> palautettava = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus WHERE varaus_id = ?");
        
        stmt.setInt(1, toimipisteId);
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            palautettava.add(new Varaus(rs.getInt("varaus_id"), rs.getInt("asiakas_id"), rs.getInt("toimipiste_id"), rs.getDate("varattu_pvm"), rs.getDate("vahvistus_pvm"), new ArrayList<PalveluVaraus>(), new ArrayList<Lasku>()));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<Varaus> observablePalvelu = FXCollections.observableArrayList();
        
        observablePalvelu.addAll(palautettava);
        
        return observablePalvelu;
    }

    @Override
    public void create(Varaus object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Varaus read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Varaus update(Varaus object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Varaus> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

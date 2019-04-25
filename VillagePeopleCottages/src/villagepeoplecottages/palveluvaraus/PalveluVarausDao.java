/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeoplecottages.palveluvaraus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.varaus.VarausDao;

/**
 *
 * @author User
 */
public class PalveluVarausDao implements Dao<PalveluVaraus, Integer>{

    public PalveluVarausDao() {
    }

    @Override
    public void create(PalveluVaraus object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PalveluVaraus read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PalveluVaraus update(PalveluVaraus object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<PalveluVaraus> list() throws SQLException {
        List<PalveluVaraus> palveluvaraukset = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT TOIMIPISTE.NIMI, PALVELUVARAUS.VARAUS_ID, PALVELUVARAUS.PALVELU_ID, VARAUS.asiakas_ID, PALVELU.TYYPPI, PALVELU.NIMI AS PALVELUN_NIMI, PALVELUVARAUS.ALKU, PALVELUVARAUS.LOPPU, VARAUS.VARATTU_PVM, VARAUS.VAHVISTUS_PVM\n" +
                "FROM PALVELUVARAUS\n" +
                "\n" +
                "JOIN VARAUS ON PALVELUVARAUS.VARAUS_ID = VARAUS.VARAUS_ID\n" +
                "\n" +
                "LEFT JOIN PALVELU ON PALVELUVARAUS.PALVELU_ID = PALVELU.PALVELU_ID\n" +
                "\n" +
                "JOIN TOIMIPISTE ON PALVELU.TOIMIPISTE_ID = TOIMIPISTE.TOIMIPISTE_ID;");
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            palveluvaraukset.add(new PalveluVaraus(rs.getString("nimi"), rs.getInt("varaus_id"), rs.getInt("palvelu_id"), rs.getInt("asiakas_id"), rs.getInt("tyyppi"), rs.getString("palvelun_nimi"), rs.getDate("alku"), rs.getDate("loppu"), rs.getDate("varattu_pvm"), rs.getDate("vahvistettu_pvm")));
                                 
             
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<PalveluVaraus> observable = FXCollections.observableArrayList();
        
        observable.addAll(palveluvaraukset);
        
        
        return observable;
    }

    public ObservableList listByToimipisteId(int toimipisteId) throws SQLException {
        List<PalveluVaraus> palveluVaraukset = new ArrayList<>();
        
        Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT TOIMIPISTE.NIMI, PALVELUVARAUS.VARAUS_ID, PALVELUVARAUS.PALVELU_ID, VARAUS.asiakas_ID, PALVELU.TYYPPI, PALVELU.NIMI AS PALVELUN_NIMI, PALVELUVARAUS.ALKU, PALVELUVARAUS.LOPPU, VARAUS.VARATTU_PVM, VARAUS.VAHVISTUS_PVM\n" +
                "FROM PALVELUVARAUS\n" +
                "\n" +
                "JOIN VARAUS ON PALVELUVARAUS.VARAUS_ID = VARAUS.VARAUS_ID\n" +
                "\n" +
                "LEFT JOIN PALVELU ON PALVELUVARAUS.PALVELU_ID = PALVELU.PALVELU_ID\n" +
                "\n" +
                "JOIN TOIMIPISTE ON PALVELU.TOIMIPISTE_ID = TOIMIPISTE.TOIMIPISTE_ID "
                + "WHERE ToIMIPISTE.TOIMIPISTE_ID = ?");
        
        stmt.setInt(1, toimipisteId);
        
        ResultSet rs = stmt.executeQuery();
        
        //Jos ei ole rivejä, palautetaan null-viite
        if(!rs.next()) {
            return null;
        }
        
        //Lisätään tietokannan taulun rivit listalle olioina
        do {
            palveluVaraukset.add(new PalveluVaraus(rs.getString("nimi"), rs.getInt("varaus_id"), rs.getInt("palvelu_id"), rs.getInt("asiakas_id"), rs.getInt("tyyppi"), rs.getString("palvelun_nimi"), rs.getDate("alku"), rs.getDate("loppu"), rs.getDate("varattu_pvm"), rs.getDate("vahvistus_pvm")));
        } while (rs.next());
        
        rs.close();
        stmt.close();
        connection.close();
        
        
        
        //Siirretään luotu lista Observablelistiin.
        
        ObservableList<PalveluVaraus> observablePalvelu = FXCollections.observableArrayList();
        
        observablePalvelu.addAll(palveluVaraukset);
        
        return observablePalvelu;
    }
    
}

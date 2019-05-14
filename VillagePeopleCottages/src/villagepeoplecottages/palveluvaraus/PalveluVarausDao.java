/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package villagepeoplecottages.palveluvaraus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.palvelu.PalveluDao;
import villagepeoplecottages.toimipiste.Toimipiste;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.varaus.VarausDao;

/**
 *
 * @author User
 */
public class PalveluVarausDao implements Dao<PalveluVaraus, Integer> {

  public PalveluVarausDao() {}

  @Override
  public void create(PalveluVaraus palveluVaraus) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement("INSERT INTO PalveluVaraus"
        + " (palvelu_id, varaus_id, alku_pvm, loppu_pvm)" + " VALUES (?, ?, ?, ?)");

    stmt.setInt(1, palveluVaraus.getPalveluId());
    stmt.setInt(2, palveluVaraus.getVarausId());
    stmt.setObject(3, palveluVaraus.getPalvelunVarausAlku());
    stmt.setObject(4, palveluVaraus.getPalvelunVarausLoppu());

    stmt.executeUpdate();
    stmt.close();
    connection.close();

  }

  /**
   * Hakee tietokannasta palvelun varauksen. Käyttää hakemiseen varausID:tä.
   * 
   * @param key VarausID
   */
  @Override
  public PalveluVaraus read(Integer key) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement(
        "SELECT palveluvaraus.*, toimipiste.nimi as toimipistenimi, palvelu.tyyppi as palvelutyyppi, palvelu.nimi as palvelunnimi, "
            + "varaus.asiakas_id as asiakasid, varaus.varattu_pvm as varausvarattu, varaus.vahvistus_pvm as varausvahvistettu "
            + "FROM PalveluVaraus LEFT JOIN Palvelu LEFT JOIN VARAUS LEFT JOIN Toimipiste "
            + "WHERE palveluvaraus.palvelu_id = palvelu.PALVELU_ID AND palveluvaraus.varaus_id = varaus.VARAUS_ID "
            + "AND varaus.toimipiste_id = toimipiste.TOIMIPISTE_ID AND palveluvaraus.varaus_id = ?");
    stmt.setInt(1, key);
    ResultSet rs = stmt.executeQuery();

    // Mikäli tulostaulussa ei ole yhtäkään riviä,
    // palautetaan null-viite
    if (!rs.next()) {
      return null;
    }

    // Ylemmät oikeat listojen muodostimet kommentoitu pois, sillä luokkia
    // ja metodeja ei vielä toteutettu.
    // Laitetaan tilalle tyhjät listat.

    int varausId = rs.getInt("varaus_id");

    PalveluVaraus palveluVaraus = new PalveluVaraus(rs.getString("toimipistenimi"), varausId,
        rs.getInt("palvelu_id"), rs.getInt("asiakasid"), rs.getInt("palvelutyyppi"),
        rs.getString("palvelunnimi"), rs.getDate("alku"),
        rs.getDate("loppu"), rs.getDate("varausvarattu"),
        rs.getDate("varausvahvistettu"));

    stmt.close();
    rs.close();
    connection.close();

    // Lisätään varaukseen kuuluvat asiakas ja toimipiste
    palveluVaraus.setPalvelu(new PalveluDao().read(palveluVaraus.getPalveluId()));
    palveluVaraus.setVaraus(new VarausDao().read(palveluVaraus.getVarausId()));

    return palveluVaraus;
  }

  /**
   * Päivittää tietokannan tietoja.
   */
  @Override
  public PalveluVaraus update(PalveluVaraus palveluVaraus) throws SQLException {

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement("UPDATE PalveluVaraus"
        + " SET palvelu_id = ?, varaus_id = ?, alku = ?, loppu = ?" + " WHERE varaus_id = ?");

    stmt.setInt(1, palveluVaraus.getPalveluId());
    stmt.setInt(2, palveluVaraus.getVarausId());
    stmt.setObject(3, palveluVaraus.getPalvelunVarausAlku());
    stmt.setObject(4, palveluVaraus.getPalvelunVarausLoppu());
    stmt.setInt(5, palveluVaraus.getVarausId());


    stmt.executeUpdate();
    stmt.close();
    connection.close();


    return palveluVaraus;
  }

  /**
   * Poistaa tietoja tietokannasta.
   */
  @Override
  public void delete(Integer key) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt =
        connection.prepareStatement("DELETE FROM PalveluVaraus" + " WHERE palvelu_id = ?");

    stmt.setInt(1, key);

    stmt.executeUpdate();
    stmt.close();
    connection.close();
  }

  @Override
  public ObservableList<PalveluVaraus> list() throws SQLException {
    List<PalveluVaraus> palveluvaraukset = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement(
        "SELECT TOIMIPISTE.NIMI, PALVELUVARAUS.VARAUS_ID, PALVELUVARAUS.PALVELU_ID, VARAUS.asiakas_ID, PALVELU.TYYPPI, PALVELU.NIMI AS PALVELUN_NIMI, PALVELUVARAUS.ALKU, PALVELUVARAUS.LOPPU, VARAUS.VARATTU_PVM, VARAUS.VAHVISTUS_PVM\n"
            + "FROM PALVELUVARAUS\n" + "\n"
            + "JOIN VARAUS ON PALVELUVARAUS.VARAUS_ID = VARAUS.VARAUS_ID\n" + "\n"
            + "LEFT JOIN PALVELU ON PALVELUVARAUS.PALVELU_ID = PALVELU.PALVELU_ID\n" + "\n"
            + "JOIN TOIMIPISTE ON PALVELU.TOIMIPISTE_ID = TOIMIPISTE.TOIMIPISTE_ID;");

    ResultSet rs = stmt.executeQuery();

    // Jos ei ole rivejä, palautetaan null-viite
    if (!rs.next()) {
      return null;
    }

    // Lisätään tietokannan taulun rivit listalle olioina
    do {
      palveluvaraukset.add(new PalveluVaraus(rs.getString("nimi"), rs.getInt("varaus_id"),
          rs.getInt("palvelu_id"), rs.getInt("asiakas_id"), rs.getInt("tyyppi"),
          rs.getString("palvelun_nimi"), rs.getDate("alku"),
          rs.getDate("loppu"), rs.getDate("varattu_pvm"),
          rs.getDate("vahvistus_pvm")));


    } while (rs.next());

    rs.close();
    stmt.close();
    connection.close();

    // Siirretään luotu lista Observablelistiin.

    ObservableList<PalveluVaraus> observable = FXCollections.observableArrayList();

    observable.addAll(palveluvaraukset);


    return observable;
  }

  public ObservableList<PalveluVaraus> listByToimipisteId(int toimipisteId) throws SQLException {
    List<PalveluVaraus> palveluVaraukset = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement(
        "SELECT TOIMIPISTE.NIMI, PALVELUVARAUS.VARAUS_ID, PALVELUVARAUS.PALVELU_ID, VARAUS.asiakas_ID, PALVELU.TYYPPI, PALVELU.NIMI AS PALVELUN_NIMI, PALVELUVARAUS.ALKU, PALVELUVARAUS.LOPPU, VARAUS.VARATTU_PVM, VARAUS.VAHVISTUS_PVM\n"
            + "FROM PALVELUVARAUS\n" + "\n"
            + "JOIN VARAUS ON PALVELUVARAUS.VARAUS_ID = VARAUS.VARAUS_ID\n" + "\n"
            + "LEFT JOIN PALVELU ON PALVELUVARAUS.PALVELU_ID = PALVELU.PALVELU_ID\n" + "\n"
            + "JOIN TOIMIPISTE ON PALVELU.TOIMIPISTE_ID = TOIMIPISTE.TOIMIPISTE_ID "
            + "WHERE ToIMIPISTE.TOIMIPISTE_ID = ?");

    stmt.setInt(1, toimipisteId);

    ResultSet rs = stmt.executeQuery();

    // Jos ei ole rivejä, palautetaan null-viite
    if (!rs.next()) {
      return null;
    }

    // Lisätään tietokannan taulun rivit listalle olioina
    do {
      palveluVaraukset.add(new PalveluVaraus(rs.getString("nimi"), rs.getInt("varaus_id"),
          rs.getInt("palvelu_id"), rs.getInt("asiakas_id"), rs.getInt("tyyppi"),
          rs.getString("palvelun_nimi"), rs.getDate("alku"),
          rs.getDate("loppu"), rs.getDate("varattu_pvm"),
          rs.getDate("vahvistus_pvm")));
    } while (rs.next());

    rs.close();
    stmt.close();
    connection.close();



    // Siirretään luotu lista Observablelistiin.

    ObservableList<PalveluVaraus> observablePalvelu = FXCollections.observableArrayList();

    observablePalvelu.addAll(palveluVaraukset);

    return observablePalvelu;
  }

}

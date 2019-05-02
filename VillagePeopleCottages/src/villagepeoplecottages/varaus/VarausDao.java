/**
 * VarausDao-luokka toteuttaa rajapinnan Varaus-objektin ja tietokannan välille.
 * 
 * 
 * Versiohistoria
 * 30.04.2019 Toteutettu toimivaksi. Joona Honkanen
 * 2.5. 2019 LocalDate otettu pois Daosta, ei toimi sql:n kanssa. Lassi Puurunen
 * 
 */

package villagepeoplecottages.varaus;

import villagepeoplecottages.varaus.Varaus;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.toimipiste.ToimipisteDao;
import villagepeoplecottages.asiakas.AsiakasDao;
import villagepeoplecottages.interfaces.Dao;
import villagepeoplecottages.lasku.Lasku;
import villagepeoplecottages.palvelu.Palvelu;
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


public class VarausDao implements Dao<Varaus, Integer> {

  public VarausDao() {}

  /**
   * Palauttaa listan varauksista toimipisteen mukaisesti.
   * @param toimipisteId Toimipisteen tunnusluku.
   * @return Listaus varauksista.
   * @throws SQLException
   */
  public ObservableList<Varaus> listByToimipisteId(int toimipisteId) throws SQLException {
    List<Varaus> palautettava = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt =
        connection.prepareStatement("SELECT * FROM Varaus WHERE toimipiste_id = ?");

    stmt.setInt(1, toimipisteId);

    ResultSet rs = stmt.executeQuery();

    // Jos ei ole rivejä, palautetaan null-viite
    if (!rs.next()) {
      return null;
    }

    // Lisätään tietokannan taulun rivit listalle olioina
    do {
      palautettava.add(new Varaus(rs.getInt("varaus_id"), rs.getInt("asiakas_id"),
          rs.getInt("toimipiste_id"), rs.getDate("varattu_pvm"), rs.getDate("vahvistus_pvm"),
          new ArrayList<PalveluVaraus>(), new ArrayList<Lasku>()));
    } while (rs.next());

    rs.close();
    stmt.close();
    connection.close();

    // Siirretään luotu lista Observablelistiin.

    ObservableList<Varaus> observablePalvelu = FXCollections.observableArrayList();

    observablePalvelu.addAll(palautettava);

    return observablePalvelu;
  }

  /**
   * Lisää tietokantaan tiedot.
   */
  @Override
  public void create(Varaus varaus) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement("INSERT INTO Varaus"
        + " (asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm)" + " VALUES (?, ?, ?, ?)");

    stmt.setInt(1, varaus.getAsiakasId());
    stmt.setInt(2, varaus.getToimipisteId());
    stmt.setObject(3, varaus.getVarattuPvm());
    stmt.setObject(4, varaus.getVahvistusPvm());

    stmt.executeUpdate();
    stmt.close();
    connection.close();

  }

  /**
   * Hakee tietokannasta tiedot.
   */
  @Override
  public Varaus read(Integer key) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt =
        connection.prepareStatement("SELECT * FROM Varaus WHERE varaus_id = ?");
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
    // List<VarauksenPalvelut> varauksenPalvelut = new
    // VaraustenPalveluDao().listByToimipisteId(toimipisteId);
    List<PalveluVaraus> varauksenPalvelut = new ArrayList<>();
    List<Lasku> varauksenLaskut = new ArrayList<>();

    Varaus varaus = new Varaus(varausId, rs.getInt("asiakas_id"), rs.getInt("toimipiste_id"),
        rs.getDate("varattu_pvm"), rs.getDate("vahvistus_pvm"),
        varauksenPalvelut, varauksenLaskut);

    stmt.close();
    rs.close();
    connection.close();

    // Lisätään varaukseen kuuluvat asiakas ja toimipiste
    varaus.setAsiakas(new AsiakasDao().read(varaus.getAsiakasId()));
    varaus.setToimipiste(new ToimipisteDao().read(varaus.getToimipisteId()));

    return varaus;
  }

  /**
   * Päivittää tietokannan tietoja.
   */
  @Override
  public Varaus update(Varaus varaus) throws SQLException {

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement("UPDATE Varaus"
        + " SET asiakas_id = ?, toimipiste_id = ?, varattu_pvm = ?, vahvistus_pvm = ?"
        + " WHERE varaus_id = ?");

    stmt.setInt(1, varaus.getAsiakasId());
    stmt.setInt(2, varaus.getToimipisteId());
    stmt.setObject(3, varaus.getVarattuPvm());
    stmt.setObject(4, varaus.getVahvistusPvm());
    stmt.setInt(5, varaus.getVarausId());


    stmt.executeUpdate();
    stmt.close();
    connection.close();


    return varaus;
  }

  /**
   * Poistaa tietoja tietokannasta.
   */
  @Override
  public void delete(Integer key) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt =
        connection.prepareStatement("DELETE FROM Varaus" + " WHERE varaus_id = ?");

    stmt.setInt(1, key);

    stmt.executeUpdate();
    stmt.close();
    connection.close();
  }

  /**
   * Palauttaa listan tietokannan varauksista.
   */
  @Override
  public ObservableList<Varaus> list() throws SQLException {
    List<Varaus> varaukset = new ArrayList<>();

    Connection connection = DriverManager.getConnection("jdbc:h2:./database", "sa", "");

    PreparedStatement stmt = connection.prepareStatement("SELECT varaus.*, "
        + "toimipiste.nimi as toimipistenimi, asiakas.etunimi as asiakasetunimi, asiakas.sukunimi as asiakassukunimi "
        + "FROM Varaus LEFT JOIN Toimipiste LEFT JOIN Asiakas WHERE varaus.toimipiste_id = toimipiste.TOIMIPISTE_ID "
        + "AND varaus.asiakas_id = asiakas.ASIAKAS_ID ");

    ResultSet rs = stmt.executeQuery();

    // Jos ei ole rivejä, palautetaan null-viite
    if (!rs.next()) {
      return null;
    }

    // Lisätään tietokannan taulun rivit listalle olioina
    do {
      varaukset.add(new Varaus(rs.getInt("varaus_id"), rs.getInt("asiakas_id"),
          rs.getInt("toimipiste_id"), rs.getDate("varattu_pvm"),
          rs.getDate("vahvistus_pvm"), rs.getString("asiakassukunimi"),
          rs.getString("asiakassukunimi"), rs.getString("toimipistenimi")));
    } while (rs.next());



    rs.close();
    stmt.close();
    connection.close();

    // Siirretään luotu lista Observablelistiin.

    ObservableList<Varaus> observableVaraus = FXCollections.observableArrayList();

    observableVaraus.addAll(varaukset);

    return observableVaraus;
  }

}

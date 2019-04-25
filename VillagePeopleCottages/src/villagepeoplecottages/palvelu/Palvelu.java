 /**
 * Palvelu -olioluokka
 * 
 * Luokan ilmentämä olio sisältää palvelun tiedot tietokannasta. 
 * Tiedot olioon haetaan tietokannasta PalveluDao -luokan avulla.
 * 
 * Luokalla on konstruktori ilman parametreja, sekä konstruktori kaikilla
 * parametreilla viitaten luokan muuttujiin.
 * 
 * Luokalla on getter ja setter metodit kaikille muuttujille.
 * 
 * 
 * Versiohistoria
 * 8.4.2019 Tekijä Lassi Puurunen
 * 10.4.2019 Dao-luokan vaatimat konstruktorit tehty
 * 25.4.2019 toimipisteNimi lisätty
 */

package villagepeoplecottages.palvelu;

import java.sql.SQLException;
import java.util.List;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.toimipiste.Toimipiste;

public class Palvelu {
    
    private int palveluId;
    private int toimipisteId;
    private String nimi;
    private int tyyppi;
    private String kuvaus;
    private double hinta;
    private double alv;
    private String toimipisteNimi;
    
    private Toimipiste toimipiste;
    
    private List<PalveluVaraus> palvelunVaraukset;
    
    final private String[] tyypit = {"Majoitus", "Lisäpalvelu"}; 

    public Palvelu() {
    }

    public Palvelu(int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv) {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
    }

    public Palvelu(int palveluId, int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv) {
      this.palveluId = palveluId;
      this.toimipisteId = toimipisteId;
      this.nimi = nimi;
      this.tyyppi = tyyppi;
      this.kuvaus = kuvaus;
      this.hinta = hinta;
      this.alv = alv;
  }
 
    public Palvelu(int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv, List<PalveluVaraus> palvelunVaraukset) throws SQLException {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.palvelunVaraukset = palvelunVaraukset;
    }

    public Palvelu(int palveluId, int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv, String toimipisteNimi) {
        this.palveluId = palveluId;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.toimipisteNimi = toimipisteNimi;
    }
    
    
    
    public Palvelu(int palveluId, int toimipisteId, String nimi, int tyyppi, 
            String kuvaus, double hinta, double alv, 
            List<PalveluVaraus> palvelunVaraukset) throws SQLException {
        
        this.palveluId = palveluId;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.palvelunVaraukset = palvelunVaraukset;
    }

    public Palvelu(int palveluId, int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv, Toimipiste toimipiste) {
        this.palveluId = palveluId;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.toimipiste = toimipiste;
    }
    
    

    public int getPalveluId() {
        return palveluId;
    }

    public void setPalveluId(int palveluId) {
        this.palveluId = palveluId;
    }

    public int getToimipisteId() {
        return toimipisteId;
    }

    public void setToimipisteId(int toimipisteId) {
        this.toimipisteId = toimipisteId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(int tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }
    
    public double getHintaAlv() {
        return hinta + alv;
    }

    public List<PalveluVaraus> getPalvelunVaraukset() {
        return palvelunVaraukset;
    }

    public void setPalvelunVaraukset(List<PalveluVaraus> palvelunVaraukset) {
        this.palvelunVaraukset = palvelunVaraukset;
    }
    
    // Muuttaa tyypin Stringiksi
    public String getTyyppiString() {
        return tyypit[this.tyyppi];
    }

    public String[] getTyypit() {
        return tyypit;
    }
    
    // Toimipisteen getteri palauttaa tiedon tietokannasta

    public Toimipiste getToimipiste()  {
        return this.toimipiste;
    }
    

    public void setToimipiste(Toimipiste toimipiste) {
        this.toimipiste = toimipiste;
    }
    
    public String getToimipisteNimi()  {
        return toimipisteNimi;
    }

    public void setToimipisteNimi(String toimipisteNimi) {
        this.toimipisteNimi = toimipisteNimi;
    }
    
    
  
}

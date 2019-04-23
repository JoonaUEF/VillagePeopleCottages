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
 */

package villagepeoplecottages;

import java.util.List;

public class Palvelu {
    
    private int palveluId;
    private int toimipisteId;
    private String nimi;
    private int tyyppi;
    private String kuvaus;
    private double hinta;
    private double alv;
    
    private List<VarauksenPalvelut> palvelunVaraukset;

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
 
    public Palvelu(int toimipisteId, String nimi, int tyyppi, String kuvaus, double hinta, double alv, List<VarauksenPalvelut> palvelunVaraukset) {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.palvelunVaraukset = palvelunVaraukset;
    }
    
    
    public Palvelu(int palveluId, int toimipisteId, String nimi, int tyyppi, 
            String kuvaus, double hinta, double alv, 
            List<VarauksenPalvelut> palvelunVaraukset) {
        
        this.palveluId = palveluId;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
        this.palvelunVaraukset = palvelunVaraukset;
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

    public List<VarauksenPalvelut> getPalvelunVaraukset() {
        return palvelunVaraukset;
    }

    public void setPalvelunVaraukset(List<VarauksenPalvelut> palvelunVaraukset) {
        this.palvelunVaraukset = palvelunVaraukset;
    }
    
    
}

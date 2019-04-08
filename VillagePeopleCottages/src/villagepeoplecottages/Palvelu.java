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
 * Tekijä: Lassi Puurunen
 * 8.4.2019
 */

package villagepeoplecottages;

import java.util.List;

public class Palvelu {
    
    private int palveluId;
    private int toimipisteId;
    private String nimi;
    private String tyyppi;
    private String kuvaus;
    private double hinta;
    private double alv;
    
    private List<VarauksenPalvelut> palvelunVaraukset;

    public Palvelu() {
    }

    public Palvelu(int palveluId, int toimipisteId, String nimi, String tyyppi, 
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

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
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

    public List<VarauksenPalvelut> getPalvelunVaraukset() {
        return palvelunVaraukset;
    }

    public void setPalvelunVaraukset(List<VarauksenPalvelut> palvelunVaraukset) {
        this.palvelunVaraukset = palvelunVaraukset;
    }
    
    
}

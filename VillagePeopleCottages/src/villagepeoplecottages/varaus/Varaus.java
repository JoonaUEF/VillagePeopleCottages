/**
 * Varaus -olioluokka
 * 
 * Luokan ilmentämä olio sisältää varauksen tiedot tietokannasta. 
 * Tiedot olioon haetaan tietokannasta VarausDao -luokan avulla.
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
 * 30.4.2019 Date muutettu LocalDate muotoon, lisätty asiakkaan etu ja sukunimi ja toimipisteen nimi
 */

package villagepeoplecottages.varaus;

import java.sql.Date;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.lasku.Lasku;
import java.util.List;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.toimipiste.Toimipiste;

public class Varaus {
    
    private int varausId;
    private int asiakasId;
    private int toimipisteId;
    private Date varattuPvm;
    private Date vahvistusPvm;
    
    private String asiakasEtunimi;
    private String asiakasSukunimi;
    private String toimipisteNimi;
    
    private List<PalveluVaraus> palvelut;
    private List<Lasku> laskut;
    
    private Asiakas asiakas;
    private Toimipiste toimipiste;

    public Varaus() {

    }

    public Varaus(int asiakasId, int toimipisteId, Date varattuPvm, Date vahvistusPvm) {
        this.asiakasId = asiakasId;
        this.toimipisteId = toimipisteId;
        this.varattuPvm = varattuPvm;
        this.vahvistusPvm = vahvistusPvm;

    }

    
    public Varaus(int varausId, int asiakasId, int toimipisteId, Date varattuPvm, Date vahvistusPvm) {
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.toimipisteId = toimipisteId;
        this.varattuPvm = varattuPvm;
        this.vahvistusPvm = vahvistusPvm;
    }

    
    public Varaus(int varausId, int asiakasId, int toimipisteId, Date varattuPvm, 
            Date vahvistusPvm, 
            List<PalveluVaraus> varauksenPalvelut, List<Lasku> varauksenLaskut) {
        
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.toimipisteId = toimipisteId;
        this.varattuPvm = varattuPvm;
        this.vahvistusPvm = vahvistusPvm;
        this.palvelut = varauksenPalvelut;
        this.laskut = varauksenLaskut;
    }

    

    public Varaus(int varausId, int asiakasId, int toimipisteId, Date varattuPvm, 
            Date vahvistusPvm, String asiakasEtunimi, String asiakasSukunimi, 
            String toimipisteNimi) {
      
      this.varausId = varausId;
      this.asiakasId = asiakasId;
      this.toimipisteId = toimipisteId;
      this.varattuPvm = varattuPvm;
      this.vahvistusPvm = vahvistusPvm;
      this.asiakasEtunimi = asiakasEtunimi;
      this.asiakasSukunimi = asiakasSukunimi;
      this.toimipisteNimi = toimipisteNimi;
    }

    public int getVarausId() {
        return varausId;
    }

    public void setVarausId(int varausId) {
        this.varausId = varausId;
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }

    public int getToimipisteId() {
        return toimipisteId;
    }

    public void setToimipisteId(int toimipisteId) {
        this.toimipisteId = toimipisteId;
    }

    public Date getVarattuPvm() {
        return varattuPvm;
    }

    public void setVarattuPvm(Date varattuPvm) {
        this.varattuPvm = varattuPvm;
    }

    public Date getVahvistusPvm() {
        return vahvistusPvm;
    }

    public void setVahvistusPvm(Date vahvistusPvm) {
        this.vahvistusPvm = vahvistusPvm;
    }

    public List<PalveluVaraus> getPalvelut() {
        return palvelut;
    }

    public void setPalvelut(List<PalveluVaraus> varauksenPalvelut) {
        this.palvelut = varauksenPalvelut;
    }

    public List<Lasku> getLaskut() {
        return laskut;
    }

    public void setLaskut(List<Lasku> varauksenLaskut) {
        this.laskut = varauksenLaskut;
    }


    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public Toimipiste getToimipiste() {
        return toimipiste;
    }

    public void setToimipiste(Toimipiste toimipiste) {
        this.toimipiste = toimipiste;
    }
    
    

    @Override
    public String toString() {
        return "Varaus{" + "varausId=" + varausId + ", asiakasId=" + asiakasId + ", toimipisteId=" + toimipisteId + ", varattuPvm=" + varattuPvm + ", vahvistusPvm=" + vahvistusPvm +  ", varauksenPalvelut=" + palvelut + ", varauksenLaskut=" + laskut + '}';
    }
 
    
}

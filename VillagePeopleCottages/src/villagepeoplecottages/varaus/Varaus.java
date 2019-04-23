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
 */

package villagepeoplecottages.varaus;

import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.lasku.Lasku;
import java.util.Date;
import java.util.List;


public class Varaus {
    
    private int varausId;
    private int asiakasId;
    private int toimipisteId;
    private Date varattuPvm;
    private Date vahvistusPvm;
    
    private List<PalveluVaraus> varauksenPalvelut;
    private List<Lasku> varauksenLaskut;

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
        this.varauksenPalvelut = varauksenPalvelut;
        this.varauksenLaskut = varauksenLaskut;
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

    public List<PalveluVaraus> getVarauksenPalvelut() {
        return varauksenPalvelut;
    }

    public void setVarauksenPalvelut(List<PalveluVaraus> varauksenPalvelut) {
        this.varauksenPalvelut = varauksenPalvelut;
    }

    public List<Lasku> getLaskut() {
        return varauksenLaskut;
    }

    public void setLaskut(List<Lasku> varauksenLaskut) {
        this.varauksenLaskut = varauksenLaskut;
    }

    @Override
    public String toString() {
        return "Varaus{" + "varausId=" + varausId + ", asiakasId=" + asiakasId + ", toimipisteId=" + toimipisteId + ", varattuPvm=" + varattuPvm + ", vahvistusPvm=" + vahvistusPvm +  ", varauksenPalvelut=" + varauksenPalvelut + ", varauksenLaskut=" + varauksenLaskut + '}';
    }
 
    
}

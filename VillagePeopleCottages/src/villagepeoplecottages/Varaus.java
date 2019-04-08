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
 * Tekijä: Lassi Puurunen
 * 8.4.2019
 */

package villagepeoplecottages;

import java.util.Date;
import java.util.List;


public class Varaus {
    
    private int varausId;
    private int asiakasId;
    private int toimipisteId;
    private Date varattuPvm;
    private Date vahvistusPvm;
    private Date varattuAlkupvm;
    private Date varattuLoppupvm;
    
    private List<VarauksenPalvelut> varauksenPalvelut;
    private List<Lasku> laskut;

    public Varaus() {

    }

    public Varaus(int varausId, int asiakasId, int toimipisteId, Date varattuPvm, 
            Date vahvistusPvm, Date varattuAlkupvm, Date varattuLoppupvm, 
            List<VarauksenPalvelut> varauksenPalvelut, List<Lasku> laskut) {
        
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.toimipisteId = toimipisteId;
        this.varattuPvm = varattuPvm;
        this.vahvistusPvm = vahvistusPvm;
        this.varattuAlkupvm = varattuAlkupvm;
        this.varattuLoppupvm = varattuLoppupvm;
        this.varauksenPalvelut = varauksenPalvelut;
        this.laskut = laskut;
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

    public Date getVarattuAlkupvm() {
        return varattuAlkupvm;
    }

    public void setVarattuAlkupvm(Date varattuAlkupvm) {
        this.varattuAlkupvm = varattuAlkupvm;
    }

    public Date getVarattuLoppupvm() {
        return varattuLoppupvm;
    }

    public void setVarattuLoppupvm(Date varattuLoppupvm) {
        this.varattuLoppupvm = varattuLoppupvm;
    }

    public List<VarauksenPalvelut> getVarauksenPalvelut() {
        return varauksenPalvelut;
    }

    public void setVarauksenPalvelut(List<VarauksenPalvelut> varauksenPalvelut) {
        this.varauksenPalvelut = varauksenPalvelut;
    }

    public List<Lasku> getLaskut() {
        return laskut;
    }

    public void setLaskut(List<Lasku> laskut) {
        this.laskut = laskut;
    }
 
}

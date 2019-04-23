 /**
 * PalveluVaraus -olioluokka
 * 
 * Luokan ilmentämä olio sisältää palveluvarauksien tiedot 
 * tietokannasta. Tiedot olioon haetaan tietokannasta PalveluVarausDao 
 * -luokan avulla.
 * 
 * Luokalla on konstruktori ilman parametreja, sekä konstruktori kaikilla
 * parametreilla viitaten luokan muuttujiin.
 * 
 * Luokalla on getter ja setter metodit kaikille muuttujille.
 * 
 * 
 * Tekijä: Lassi Puurunen
 * 8.4.2019
 * 23.4. Päivitetty uuden tietokantamallin mukaiseksi. Lassi Puurunen
 */

package villagepeoplecottages.palveluvaraus;

import java.util.Date;


public class PalveluVaraus {
    
    private int VarausId;
    private int palveluId;
    private Date palvelunVarausAlku;
    private Date palvelunVarausLoppu;

    public PalveluVaraus() {
    }

    public PalveluVaraus(int VarausId, int palveluId, Date palvelunVarausAlku, Date palvelunVarausLoppu) {
        this.VarausId = VarausId;
        this.palveluId = palveluId;
        this.palvelunVarausAlku = palvelunVarausAlku;
        this.palvelunVarausLoppu = palvelunVarausLoppu;
    }

    public int getVarausId() {
        return VarausId;
    }

    public void setVarausId(int VarausId) {
        this.VarausId = VarausId;
    }

    public int getPalveluId() {
        return palveluId;
    }

    public void setPalveluId(int palveluId) {
        this.palveluId = palveluId;
    }

    public Date getPalvelunVarausAlku() {
        return palvelunVarausAlku;
    }

    public void setPalvelunVarausAlku(Date palvelunVarausAlku) {
        this.palvelunVarausAlku = palvelunVarausAlku;
    }

    public Date getPalvelunVarausLoppu() {
        return palvelunVarausLoppu;
    }

    public void setPalvelunVarausLoppu(Date palvelunVarausLoppu) {
        this.palvelunVarausLoppu = palvelunVarausLoppu;
    }
    
    
}
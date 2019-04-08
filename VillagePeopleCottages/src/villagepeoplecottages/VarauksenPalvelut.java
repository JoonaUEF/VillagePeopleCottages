 /**
 * VarauksenPalvelut -olioluokka
 * 
 * Luokan ilmentämä olio sisältää varauksen sisältämien palveluiden tiedot 
 * tietokannasta. Tiedot olioon haetaan tietokannasta VarauksenPalvelutDao 
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
 */

package villagepeoplecottages;

import java.util.Date;


public class VarauksenPalvelut {
    
    private int VarausId;
    private int palveluId;
    private Date palvelunVarausAlku;
    private Date palvelunVarausLoppu;

    public VarauksenPalvelut() {
    }

    public VarauksenPalvelut(int VarausId, int palveluId, Date palvelunVarausAlku, Date palvelunVarausLoppu) {
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

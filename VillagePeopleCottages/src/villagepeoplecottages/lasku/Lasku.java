 /**
 * Lasku -olioluokka
 * 
 * Luokan ilmentämä olio sisältää laskun tiedot tietokannasta. 
 * Tiedot olioon haetaan tietokannasta LaskuDao -luokan avulla.
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
 * 23.4.2019 Laskun tila lisätty
 */

package villagepeoplecottages.lasku;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import villagepeoplecottages.asiakas.Asiakas;
import villagepeoplecottages.varaus.Varaus;

public class Lasku {

    private int laskuId;
    private int varausId;
    private int asiakasId;
    private int tila;
    private String nimi;
    private String lahiosoite;
    private String postitoimipaikka;
    private String postinro;
    private double summa;
    private double alv;
    private Date paivays;
    
    private String toimipiste;
    
    private Asiakas asiakas;
    private Varaus varaus;
    
    private List<String> laskunTila;
    
    //Laskun tilat Stringinä listan järjestyksessä
    
    public List<String> getLaskunTila() {
        laskunTila = new ArrayList<>();
        laskunTila.add("Luotu");
        laskunTila.add("Laskutettu");
        laskunTila.add("Maksettu");
        return laskunTila;
    }
    
    // Muuttaa tilan Stringiksi
    public String getTilaString() {
        return getLaskunTila().get(tila);
    }
    
    public Double getSummaAlv() {
        return summa + alv;
    }

    public Lasku() {
    }

    public Lasku(int laskuId, int varausId, int asiakasId, int tila, String nimi, String lahiosoite, String postitoimipaikka, String postinro, double summa, double alv, Date paivays, String toimipiste) {
        this.laskuId = laskuId;
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.tila = tila;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.summa = summa;
        this.alv = alv;
        this.paivays = paivays;
        this.toimipiste = toimipiste;
    }
    
    

    public Lasku(int varausId, int asiakasId, int tila, String nimi, String lahiosoite, String postitoimipaikka, String postinro, double summa, double alv) {
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.summa = summa;
        this.alv = alv;
    }

    
    public Lasku(int laskuId, int varausId, int asiakasId, int tila, String nimi, 
            String lahiosoite, String postitoimipaikka, String postinro, 
            double summa, double alv) {
        
        this.laskuId = laskuId;
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.summa = summa;
        this.alv = alv;
    }

    public int getLaskuId() {
        return laskuId;
    }

    public void setLaskuId(int laskuId) {
        this.laskuId = laskuId;
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

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public String getPostinro() {
        return postinro;
    }

    public void setPostinro(String postinro) {
        this.postinro = postinro;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }

    public int getTila() {
        return tila;
    }

    public void setTila(int tila) {
        this.tila = tila;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public Varaus getVaraus() {
        return varaus;
    }

    public void setVaraus(Varaus varaus) {
        this.varaus = varaus;
    }

    public Date getPaivays() {
        return paivays;
    }

    public String getToimipiste() {
        return toimipiste;
    }
    
    
    

    @Override
    public String toString() {
        return "Lasku{" + "laskuId=" + laskuId + ", varausId=" + varausId + ", asiakasId=" + asiakasId + ", nimi=" + nimi + ", lahiosoite=" + lahiosoite + ", postitoimipaikka=" + postitoimipaikka + ", postinro=" + postinro + ", summa=" + summa + ", alv=" + alv + '}';
    }
    
    
}

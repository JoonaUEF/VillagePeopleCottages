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
 * Tekijä: Lassi Puurunen
 * 8.4.2019
 */

package villagepeoplecottages;

public class Lasku {

    private int laskuId;
    private int varausId;
    private int asiakasId;
    private String nimi;
    private String lahiosoite;
    private String postitoimipaikka;
    private String postinro;
    private double summa;
    private double alv;

    public Lasku() {
    }

    public Lasku(int laskuId, int varausId, int asiakasId, String nimi, 
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
    
    
}

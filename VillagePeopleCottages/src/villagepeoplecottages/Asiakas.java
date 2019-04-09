 /**
 * Asiakas -olioluokka
 * 
 * Luokan ilmentämä olio sisältää asiakkaan tiedot tietokannasta. 
 * Tiedot olioon haetaan tietokannasta AsiakasDao -luokan avulla.
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

import java.util.ArrayList;
import java.util.List;


public class Asiakas {
    
    private int asiakasId;
    private String etunimi;
    private String sukunimi;
    private String lahiosoite;
    private String postitoimipaikka;
    private String postinro;
    private String email;
    private String puhelinnro;
    
    private List<Varaus> varaukset;
    private List<Lasku> laskut;

    public Asiakas() {
        
    }

    public Asiakas(int asiakasId, String etunimi, String sukunimi, String lahiosoite, 
            String postitoimipaikka, String postinro, String email, String puhelinnro, 
            List<Varaus> varaukset, List<Lasku> laskut) {
        
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
        this.varaukset = varaukset;
        this.laskut = laskut;
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuhelinnro() {
        return puhelinnro;
    }

    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }

    public List<Varaus> getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(List<Varaus> varaukset) {
        this.varaukset = varaukset;
    }

    public List<Lasku> getLaskut() {
        return laskut;
    }

    public void setLaskut(List<Lasku> laskut) {
        this.laskut = laskut;
    }
 
}

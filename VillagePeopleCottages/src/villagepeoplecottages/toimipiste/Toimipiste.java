 /**
 * Toimipiste -olioluokka
 * 
 * Luokan ilmentämä olio sisältää toimipisteen tiedot tietokannasta. 
 * Tiedot olioon haetaan tietokannasta ToimiPisteDao -luokan avulla.
 * 
 * Luokalla on konstruktori ilman parametreja, sekä konstruktori kaikilla
 * parametreilla viitaten luokan muuttujiin.
 * 
 * Luokalla on getter ja setter metodit kaikille muuttujille.
 * 
 * 
 * Versiohistoria
 * 8.4.2019 tekijä Lassi Puurunen
 * 10.4. 2019 lisätty konstruktori ilman viittauslistoja
 * 
 */
package villagepeoplecottages.toimipiste;

import villagepeoplecottages.palvelu.Palvelu;
import java.util.List;
import javafx.collections.ObservableList;
import villagepeoplecottages.palveluvaraus.PalveluVaraus;
import villagepeoplecottages.varaus.Varaus;


public class Toimipiste {
    
    private int toimipisteId;
    private String nimi;
    private String lahiosoite;
    private String postitoimipaikka;
    private String postinro;
    private String email;
    private String puhelinnro;
    
    private ObservableList<Palvelu> palvelut;
    private ObservableList<PalveluVaraus> palveluvaraukset;

    public Toimipiste() {

    }

    public Toimipiste(String nimi, String lahiosoite, String postitoimipaikka, String postinro, String email, String puhelinnro) {
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    public Toimipiste(int toimipisteId, String nimi, String lahiosoite, String postitoimipaikka, String postinro, String email, String puhelinnro) {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    public Toimipiste(int toimipisteId, String nimi, String lahiosoite, 
            String postitoimipaikka, String postinro, String email, String puhelinnro, 
            ObservableList<Palvelu> palvelut, ObservableList<PalveluVaraus> varaukset) {
        
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
        this.palvelut = palvelut;
        this.palveluvaraukset = varaukset;
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

    public ObservableList<Palvelu> getPalvelut() {
        return palvelut;
    }

    public void setPalvelut(ObservableList<Palvelu> palvelut) {
        this.palvelut = palvelut;
    }

    public ObservableList<PalveluVaraus> getVaraukset() {
        return palveluvaraukset;
    }

    public void setVaraukset(ObservableList<PalveluVaraus> varaukset) {
        this.palveluvaraukset = varaukset;
    }

    @Override
    public String toString() {
        return "Toimipiste{" + "toimipisteId=" + toimipisteId + ", nimi=" + nimi + ", lahiosoite=" + lahiosoite + ", postitoimipaikka=" + postitoimipaikka + ", postinro=" + postinro + ", email=" + email + ", puhelinnro=" + puhelinnro + ", palvelut=" + palvelut + ", palveluvaraukset=" + palveluvaraukset + '}';
    }

    
    
    
    
}

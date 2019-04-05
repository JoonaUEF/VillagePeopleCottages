//**********************************************************************

package proto;


public abstract class Vuokrauskohde {
    private String nimi;
    private String kuvaus;

    public Vuokrauskohde (String nimi, String kuvaus) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Override
    public String toString() {
        return "Vuokrattava kohde: " + "nimi = " + nimi + ", kuvaus = " + kuvaus+ '.';
    }
    
    
    
public abstract String nykyinenVuokralainen(); // Palauttaa tiedon, kuka vuokraa kohdetta just nyt
    
}

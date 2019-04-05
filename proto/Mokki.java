package proto;

public class Mokki extends Vuokrauskohde implements Vuokrattava {
  private String vuokraaja;
  private boolean vapaa;
  private double viikkovuokra;

  public Mokki(String nimi, String kuvaus) {
    super(nimi, kuvaus);
    this.vuokraaja = "Ei vuokrattu";
    this.vapaa = true;
  }

  public Mokki(String nimi, String kuvaus, String vuokraaja, double viikkovuokra) {
    super(nimi, kuvaus);
    this.vuokraaja = vuokraaja;
    this.vapaa = false;
    this.viikkovuokra = viikkovuokra;
  }

  @Override
  public String nykyinenVuokralainen() {
    return this.vuokraaja;
  }

  public void setVuokraaja(String vuokraaja) {
    this.vuokraaja = vuokraaja;
  }

  public Boolean getOnVapaa() {
    return this.vapaa;
  }

  public void setVapaa(boolean vapaa) {
    this.vapaa = vapaa;
  }

  public double getViikkovuokra() {
    return this.viikkovuokra;
  }

  public void setViikkovuokra(double viikkovuokra) {
    this.viikkovuokra = viikkovuokra;
  }

  @Override
  public String toString() {
    return "Vuokrattava kohde: " + "nimi = " + super.getNimi() + ", kuvaus = " + super.getKuvaus() + ", vapaa = " + vapaa
       + (vapaa != true ? ", vuokralainen = " + vuokraaja + ", viikkovuokra = " + viikkovuokra : "") + '.';
  }


}

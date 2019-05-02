/**
 * PalveluVaraus -olioluokka
 * 
 * Luokan ilmentämä olio sisältää palveluvarauksien tiedot tietokannasta. Tiedot olioon haetaan
 * tietokannasta PalveluVarausDao -luokan avulla.
 * 
 * Luokalla on konstruktori ilman parametreja, sekä konstruktori kaikilla parametreilla viitaten
 * luokan muuttujiin.
 * 
 * Luokalla on getter ja setter metodit kaikille muuttujille.
 * 
 * 
 * Tekijä: Lassi Puurunen 8.4.2019 23.4. Päivitetty uuden tietokantamallin mukaiseksi. Lassi
 * Puurunen 25.4. Lisätty attribuutteja listausta varten.
 */

package villagepeoplecottages.palveluvaraus;

import java.time.LocalDate;
import villagepeoplecottages.palvelu.Palvelu;
import villagepeoplecottages.varaus.Varaus;


public class PalveluVaraus {

  private String toimipiste;
  private int varausId;
  private int palveluId;
  private int asiakasId;
  private int palveluTyyppi;
  private String palvelunNimi;
  private LocalDate palvelunVarausAlku;
  private LocalDate palvelunVarausLoppu;
  private LocalDate varausVarattu;
  private LocalDate varausVahvistettu;

  private Palvelu palvelu;
  private Varaus varaus;

  public String getPalveluTyyppiString() {
    String[] palvelutyypit = new Palvelu().getTyypit();
    return palvelutyypit[this.palveluTyyppi];
  }


  public PalveluVaraus() {}

  public PalveluVaraus(String toimipiste, int VarausId, int palveluId, int asiakasId,
      int palveluTyyppi, String palvelunNimi, LocalDate palvelunVarausAlku, LocalDate palvelunVarausLoppu,
      LocalDate varausVarattu, LocalDate varausVahvistettu) {
    this.toimipiste = toimipiste;
    this.varausId = VarausId;
    this.palveluId = palveluId;
    this.asiakasId = asiakasId;
    this.palveluTyyppi = palveluTyyppi;
    this.palvelunNimi = palvelunNimi;
    this.palvelunVarausAlku = palvelunVarausAlku;
    this.palvelunVarausLoppu = palvelunVarausLoppu;
    this.varausVarattu = varausVarattu;
    this.varausVahvistettu = varausVahvistettu;
  }

  public String getToimipiste() {
    return toimipiste;
  }

  public void setToimipiste(String toimipiste) {
    this.toimipiste = toimipiste;
  }

  public int getVarausId() {
    return varausId;
  }

  public void setVarausId(int VarausId) {
    this.varausId = VarausId;
  }

  public int getPalveluId() {
    return palveluId;
  }

  public void setPalveluId(int palveluId) {
    this.palveluId = palveluId;
  }

  public int getAsiakasId() {
    return asiakasId;
  }

  public void setAsiakasId(int asiakasId) {
    this.asiakasId = asiakasId;
  }

  public int getPalveluTyyppi() {
    return palveluTyyppi;
  }

  public void setPalveluTyyppi(int palveluTyyppi) {
    this.palveluTyyppi = palveluTyyppi;
  }

  public String getPalvelunNimi() {
    return palvelunNimi;
  }

  public void setPalvelunNimi(String palvelunNimi) {
    this.palvelunNimi = palvelunNimi;
  }

  public LocalDate getPalvelunVarausAlku() {
    return palvelunVarausAlku;
  }

  public void setPalvelunVarausAlku(LocalDate palvelunVarausAlku) {
    this.palvelunVarausAlku = palvelunVarausAlku;
  }

  public LocalDate getPalvelunVarausLoppu() {
    return palvelunVarausLoppu;
  }

  public void setPalvelunVarausLoppu(LocalDate palvelunVarausLoppu) {
    this.palvelunVarausLoppu = palvelunVarausLoppu;
  }

  public LocalDate getVarausVarattu() {
    return varausVarattu;
  }

  public void setVarausVarattu(LocalDate varausVarattu) {
    this.varausVarattu = varausVarattu;
  }

  public LocalDate getVarausVahvistettu() {
    return varausVahvistettu;
  }

  public void setVarausVahvistettu(LocalDate varausVahvistettu) {
    this.varausVahvistettu = varausVahvistettu;
  }

  public Palvelu getPalvelu() {
    return palvelu;
  }

  public void setPalvelu(Palvelu palvelu) {
    this.palvelu = palvelu;
  }

  public Varaus getVaraus() {
    return varaus;
  }

  public void setVaraus(Varaus varaus) {
    this.varaus = varaus;
  }



}

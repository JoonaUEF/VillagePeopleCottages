package proto;

public class VuokrausTesti {

  public static void main(String[] args) {
    Vuokrattava vuokrattava = new Mokki("Mökki1", "Huono");
    Mokki mokki = new Mokki("Mökki2", "Hieno", "Ville Vuokralainen", 100.5);
    System.out.println(vuokrattava.toString());
    System.out.println(mokki.toString());
  }

}

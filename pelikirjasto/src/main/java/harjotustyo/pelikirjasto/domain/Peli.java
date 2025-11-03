package harjotustyo.pelikirjasto.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Peli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nimi, julkaisija;
    private int julkaisuVuosi;
    private double hinta;

    public Peli() {
    }

    public Peli(String nimi, String genre, String julkaisija, int julkaisuVuosi, double hinta) {
        this.nimi = nimi;
        this.julkaisija = julkaisija;
        this.julkaisuVuosi = julkaisuVuosi;
        this.hinta = hinta;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getJulkaisija() {
        return julkaisija;
    }

    public void setJulkaisija(String julkaisija) {
        this.julkaisija = julkaisija;
    }

    public int getJulkaisuVuosi() {
        return julkaisuVuosi;
    }

    public void setJulkaisuVuosi(int julkaisuVuosi) {
        this.julkaisuVuosi = julkaisuVuosi;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    @Override
    public String toString() {
        return "Peli [id=" + id + ", nimi=" + nimi + ", julkaisija=" + julkaisija
                + ", julkaisuVuosi=" + julkaisuVuosi + ", hinta=" + hinta + "]";
    }

}

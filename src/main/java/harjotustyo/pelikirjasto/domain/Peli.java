package harjotustyo.pelikirjasto.domain;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Peli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Developer can't be empty")
    private String developer;

    @NotNull(message = "Release date can't be negative or empty")
    private Year releaseDate;

    @Min(value = 0, message = "Price can't be negative")
    private double price;

    @JsonIgnoreProperties("pelit")
    @ManyToOne
    @JoinColumn(name = "genreid")
    private Genre genre;

    public Peli() {
    }

    public Peli(String name, String developer, Year releaseDate, double price) {
        this.name = name;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Peli(String name, String developer, Year releaseDate, double price, Genre genre) {
        this.name = name;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.price = price;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Year getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Year releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Peli [id=" + id + ", name=" + name + ", developer=" + developer
                + ", releaseDate=" + releaseDate + ", price=" + price + "]";
    }

}

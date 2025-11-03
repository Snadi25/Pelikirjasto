package harjotustyo.pelikirjasto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnoreProperties("genre")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Peli> pelit;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Peli> getPelit() {
        return pelit;
    }

    public void setPelit(List<Peli> pelit) {
        this.pelit = pelit;
    }

    @Override
    public String toString() {
        return "Genre [id=" + id + ", name=" + name + ", pelit=" + pelit + "]";
    }

}

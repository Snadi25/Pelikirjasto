package harjotustyo.pelikirjasto.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);

}
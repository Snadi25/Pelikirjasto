package harjotustyo.pelikirjasto.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliRepository extends JpaRepository<Peli, Long> {

    List<Peli> findByName(String name);

}

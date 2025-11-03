package harjotustyo.pelikirjasto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjotustyo.pelikirjasto.domain.Peli;
import harjotustyo.pelikirjasto.domain.PeliRepository;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PelikirjastoApplication {

	private static final Logger log = LoggerFactory.getLogger(PelikirjastoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PelikirjastoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PeliRepository p_repository) {
		return (args) -> {
			Peli peli1 = new Peli("God of War", "Action-adventure", "Sony Interactive Entertainment", 2018, 19.95);
			Peli peli2 = new Peli("Hades", "Roguelike", "Supergiant Games", 2020, 24.50);
			Peli peli3 = new Peli("Hades 2", "Roguelike", "Supergiant Games", 2025, 28.99);

			p_repository.save(peli1);
			p_repository.save(peli2);
			p_repository.save(peli3);

			for (Peli book : p_repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}

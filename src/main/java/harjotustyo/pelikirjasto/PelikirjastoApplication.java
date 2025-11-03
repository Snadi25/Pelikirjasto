package harjotustyo.pelikirjasto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Year;

import harjotustyo.pelikirjasto.domain.Peli;
import harjotustyo.pelikirjasto.domain.PeliRepository;
import harjotustyo.pelikirjasto.domain.AppUser;
import harjotustyo.pelikirjasto.domain.AppUserRepository;
import harjotustyo.pelikirjasto.domain.Genre;
import harjotustyo.pelikirjasto.domain.GenreRepository;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PelikirjastoApplication {

	private static final Logger log = LoggerFactory.getLogger(PelikirjastoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PelikirjastoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PeliRepository p_repository, GenreRepository g_repository,
			AppUserRepository u_repository, PasswordEncoder encoder) {
		return (args) -> {

			Genre genre1 = new Genre("Roguelike");
			Genre genre2 = new Genre("Action");
			Genre genre3 = new Genre("Adventure");
			Genre genre4 = new Genre("RPG");

			Peli peli1 = new Peli("God of War", "Santa Monica Studio", Year.of(2018), 19.95, genre3);
			Peli peli2 = new Peli("Hades", "Supergiant Games", Year.of(2020), 24.50, genre1);
			Peli peli3 = new Peli("Hades 2", "Supergiant Games", Year.of(2025), 28.99, genre1);

			AppUser user1 = new AppUser("user", encoder.encode("user"), "USER");
			AppUser user2 = new AppUser("admin", encoder.encode("admin"),
					"ADMIN");

			g_repository.save(genre1);
			g_repository.save(genre2);
			g_repository.save(genre3);
			g_repository.save(genre4);

			u_repository.save(user1);
			u_repository.save(user2);

			p_repository.save(peli1);
			p_repository.save(peli2);
			p_repository.save(peli3);

			for (Peli book : p_repository.findAll()) {
				log.info(book.toString());
			}

			for (AppUser appUser : u_repository.findAll()) {
				log.info(appUser.toString());
			}
		};
	}

}

package harjotustyo.pelikirjasto.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import harjotustyo.pelikirjasto.domain.Peli;
import harjotustyo.pelikirjasto.domain.PeliRepository;

@Controller
public class PeliController {

    private final PeliRepository p_repository;

    private static final Logger log = LoggerFactory.getLogger(PeliController.class);

    public PeliController(PeliRepository p_repository) {
        this.p_repository = p_repository;
    }

    @GetMapping("/pelit")
    public String getAllGames(Model model) {
        log.info("GetAllGames...");
        model.addAttribute("pelit", p_repository.findAll());
        return "pelit";
    }

    @GetMapping("/addGame")
    public String addGameForm(Model model) {
        model.addAttribute("peli", new Peli());
        return "addGame";
    }

}

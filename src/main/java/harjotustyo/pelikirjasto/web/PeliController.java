package harjotustyo.pelikirjasto.web;

import java.time.Year;
import java.util.stream.IntStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import harjotustyo.pelikirjasto.domain.Peli;
import harjotustyo.pelikirjasto.domain.PeliRepository;
import jakarta.validation.Valid;
import harjotustyo.pelikirjasto.domain.GenreRepository;

@Controller
public class PeliController {

    private final PeliRepository p_repository;
    private final GenreRepository g_repository;

    private static final Logger log = LoggerFactory.getLogger(PeliController.class);

    public PeliController(PeliRepository p_repository, GenreRepository g_repository) {
        this.p_repository = p_repository;
        this.g_repository = g_repository;
    }

    private List<Integer> getYearList() {
        int startYear = 1970;
        int endYear = Year.now().getValue() + 5;
        return IntStream.rangeClosed(startYear, endYear).boxed().toList();
    }

    @GetMapping("/games")
    public String getAllGames(Model model) {
        log.info("GetAllGames...");
        model.addAttribute("games", p_repository.findAll());
        return "games";
    }

    @GetMapping("/addGame")
    public String addGameForm(Model model) {
        model.addAttribute("game", new Peli());
        model.addAttribute("genres", g_repository.findAll());
        model.addAttribute("years", getYearList());
        return "addGame";
    }

    @PostMapping("/saveGame")
    public String saveGame(@Valid @ModelAttribute("game") Peli peli,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.error("Validation error during save: ", peli);
            model.addAttribute("genres", g_repository.findAll());
            model.addAttribute("years", getYearList());
            return "addGame";
        }

        log.info("Save game: " + peli);
        p_repository.save(peli);
        return "redirect:/games";
    }

    @GetMapping("/editGame/{id}")
    public String editGameForm(@PathVariable Long id, Model model) {
        log.info("Edit game with id = " + id);
        model.addAttribute("editGame", p_repository.findById(id));
        model.addAttribute("genres", g_repository.findAll());
        model.addAttribute("years", getYearList());
        return "editGameWithValidation";
    }

    @PostMapping("/updateGame")
    public String saveEditedGame(@Valid @ModelAttribute("editGame") Peli peli,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Validation error with game: ", peli);
            model.addAttribute("editGame", peli);
            model.addAttribute("genres", g_repository.findAll());
            model.addAttribute("years", getYearList());

            return "editGameWithValidation";
        }
        log.info("Save edited game: " + peli);
        p_repository.save(peli);
        return "redirect:/games";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteGame/{id}")
    public String deleteGame(@PathVariable Long id) {
        log.info("Delete game with id = " + id);
        p_repository.deleteById(id);
        return "redirect:/games";
    }

}

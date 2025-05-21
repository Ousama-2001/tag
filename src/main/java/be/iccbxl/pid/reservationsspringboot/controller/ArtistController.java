package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.Troupe;
import be.iccbxl.pid.reservationsspringboot.service.ArtistService;
import be.iccbxl.pid.reservationsspringboot.service.TroupeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ArtistController {
    @Autowired
    private ArtistService service;

    @Autowired
    private TroupeService troupeService;

    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = service.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");
        return "artist/index";
    }

    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = service.getArtist(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");

        // Pour l'affichage de la troupe (nom + logo)
        model.addAttribute("troupe", artist.getTroupe());

        // Pour remplir la liste déroulante d'affiliation
        List<Troupe> troupes = troupeService.getAllTroupes();
        model.addAttribute("troupes", troupes);

        return "artist/show";
    }

    @PostMapping("/artists/{id}/affect-troupe")
    public String assignTroupe(@PathVariable long id,
                               @RequestParam(value = "troupeId", required = false) Long troupeId,
                               RedirectAttributes redirAttrs) {
        service.assignToTroupe(id, troupeId);
        redirAttrs.addFlashAttribute("successMessage", "Affectation mise à jour.");
        return "redirect:/artists/" + id;
    }

    @GetMapping("/artists/{id}/edit")
    public String edit(Model model, @PathVariable long id, HttpServletRequest request) {
        Artist artist = service.getArtist(id);

        model.addAttribute("artist", artist);

        //Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if (referrer != null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/artists/" + artist.getId());
        }

        return "artist/edit";
    }

    @PutMapping("/artists/{id}/edit")
    public String update(@Valid @ModelAttribute Artist artist, BindingResult bindingResult,
                         @PathVariable long id, Model model, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Échec de la modification de l'artiste !");

            return "artist/edit";
        }

        Artist existing = service.getArtist(id);

        if (existing == null) {
            return "artist/index";
        }

        service.updateArtist(id, artist);
        redirAttrs.addFlashAttribute("successMessage", "Artiste modifié avec succès.");

        return "redirect:/artists/" + artist.getId();
    }

    @GetMapping("/artists/create")
    public String create(Model model) {
        if (!model.containsAttribute("artist")) {
            model.addAttribute("artist", new Artist());
        }

        return "artist/create";
    }

    @PostMapping("/artists/create")
    public String store(@Valid @ModelAttribute Artist artist, BindingResult bindingResult,
                        Model model, RedirectAttributes redirAttrs) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Échec de la création de l'artiste !");

            return "artist/create";
        }

        service.addArtist(artist);
        redirAttrs.addFlashAttribute("successMessage", "Artiste créé avec succès.");

        return "redirect:/artists/" + artist.getId();
    }

    @DeleteMapping("/artists/{id}")
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirAttrs) {
        Artist existing = service.getArtist(id);

        if (existing != null) {
            service.deleteArtist(id);

            redirAttrs.addFlashAttribute("successMessage", "Artiste supprimé avec succès.");
        } else {
            redirAttrs.addFlashAttribute("errorMessage", "Échec de la suppression de l'artiste !");
        }

        return "redirect:/artists";
    }

}
package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import be.iccbxl.pid.reservationsspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class ShowController {
    @Autowired
    ShowService service;
    TagService tagService;

    @GetMapping("/shows")
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Show> shows;
        if (keyword != null && !keyword.isEmpty()) {
            shows = service.searchByKeyword(keyword);
            model.addAttribute("resultCount", shows.size());
        } else {
            shows = service.getAll();
        }
        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");
        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.get(id);

        //Récupérer les artistes du spectacle et les grouper par type
        Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();

        for (ArtistType at : show.getArtistTypes()) {
            String type = at.getType().getType();

            if (collaborateurs.get(type) == null) {
                collaborateurs.put(type, new ArrayList<>());
            }

            collaborateurs.get(type).add(at.getArtist());
        }

        model.addAttribute("collaborateurs", collaborateurs);

        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");

        return "show/show";
    }
    @PostMapping("/shows/{id}/tags/add")
    public String addTagToShow(@PathVariable("id") String id, @RequestParam("tag") String tagValue) {
        Show show = service.get(id);
        if (show == null) {
            return "redirect:/shows";
        }

        // Crée un tag ou récupère s’il existe déjà
        Tag tag = tagService.createTag(tagValue);

        // Ajoute le tag au show
        show.getTags().add(tag);
        service.update(show.getId().toString(), show);

        return "redirect:/shows/" + id;
    }


}

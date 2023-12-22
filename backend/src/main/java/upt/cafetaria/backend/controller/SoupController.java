package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Soup;
import upt.cafetaria.backend.model.web.SoupDto;
import upt.cafetaria.backend.service.SoupService;
import java.util.List;

/**
 * Manages CRUD(Create, Read, Update, Delete) operations for soups available in the cafeteria.
 * Provides endpoints to retrieve all soups, add a new soup,
 * update an existing soup, and delete a soup.
 *
 * Endpoints:
 * - /all: GET request to retrieve all soups.
 * - /add: POST request to add a new soup.
 * - /update/{id}: PUT request to update an existing soup by ID.
 * - /delete/{id}: DELETE request to delete a soup by ID.
 * @author Rúben Santos
 */
@RestController
@RequestMapping("api/soup")
public class SoupController {

    @Autowired
    private final SoupService soupService;

    public SoupController(SoupService soupService) {
        this.soupService = soupService;
    }

    @GetMapping("/all")
    List<Soup> allSoups() {
        return soupService.getSoups();
    }

    @GetMapping("/active/all")
    List<Soup> allActiveSoups() {
        return soupService.getActiveSoups();
    }

    @PostMapping("/add")
    Soup addSoup(@Valid @RequestBody SoupDto soupDto) {
        return soupService.addSoup(soupDto);
    }

    @PutMapping("/update/{id}")
    Soup updateSoup(@Valid @RequestBody SoupDto newSoup, @PathVariable Long id) {
        Soup updatedSoup = soupService.getSoup(id);
        soupService.updateSoup(id, newSoup);
        return updatedSoup;
    }

    @DeleteMapping("/delete/{id}")
    public Soup deleteSoup(@PathVariable Long id) {
        Soup deletedSoup = soupService.getSoup(id);
        soupService.deleteSoup(id);
        return deletedSoup;
    }
}

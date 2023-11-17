package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Soup;
import upt.cafetaria.backend.model.web.SoupDto;
import upt.cafetaria.backend.service.SoupService;

import java.util.List;

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

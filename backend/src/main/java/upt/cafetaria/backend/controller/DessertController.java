package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Dessert;
import upt.cafetaria.backend.model.web.DessertDto;
import upt.cafetaria.backend.service.DessertService;

import java.util.List;

@RestController
@RequestMapping("api/dessert")
public class DessertController {
    @Autowired
    private final DessertService dessertService;

    public DessertController(DessertService dessertService) {
        this.dessertService = dessertService;
    }

    @GetMapping("/all")
    List<Dessert> allDesserts() {
        return dessertService.getDesserts();
    }

    @PostMapping("/add")
    Dessert addDessert(@Valid @RequestBody DessertDto dessertDto) {
        return dessertService.addDessert(dessertDto);
    }

    @PutMapping("/update/{id}")
    Dessert updateDessert(@Valid @RequestBody DessertDto newDessert, @PathVariable Long id) {
        Dessert updatedDessert = dessertService.getDessert(id);
        dessertService.updateDessert(id, newDessert);
        return updatedDessert;
    }

    @DeleteMapping("/delete/{id}")
    public Dessert deleteDessert(@PathVariable Long id) {
        Dessert deletedDessert = dessertService.getDessert(id);
        dessertService.deleteDessert(deletedDessert);
        return deletedDessert;
    }
}

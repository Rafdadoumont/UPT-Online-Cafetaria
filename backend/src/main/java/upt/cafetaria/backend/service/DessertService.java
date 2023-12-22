package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Dessert;
import upt.cafetaria.backend.model.web.DessertDto;
import upt.cafetaria.backend.repository.DessertRepository;
import java.util.List;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;

    public List<Dessert> getDesserts() { return dessertRepository.findAll();}

    public Dessert getDessert(long id) {
        return dessertRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "dessert.not.found"));
    }

    public List<Dessert> getActiveDesserts() {
        return dessertRepository.findAllByActive(true);
    }

    public Dessert addDessert(DessertDto dto) {
        Dessert dessert = new Dessert();

        dessert.setName(dto.getName());
        dessert.setPrice(dto.getPrice());
        dessert.setDescription(dto.getDescription());
        dessert.setActive(true);
        return dessertRepository.save(dessert);
    }

    public Dessert updateDessert(Long id, DessertDto dto) {
        Dessert dessert = getDessert(id);

        dessert.setName(dto.getName());
        dessert.setPrice(dto.getPrice());
        dessert.setDescription(dto.getDescription());
        return dessertRepository.save(dessert);
    }

    public void deleteDessert(Dessert dessert) {
        dessertRepository.delete(dessert);
    }
}

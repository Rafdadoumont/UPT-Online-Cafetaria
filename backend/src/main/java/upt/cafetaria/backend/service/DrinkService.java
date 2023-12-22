package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;
import upt.cafetaria.backend.model.web.DrinkDto;
import upt.cafetaria.backend.repository.DrinkRepository;

import java.util.List;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    public List<Drink> getDrinks() { return drinkRepository.findAll();}

    public Drink getDrink(long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "drink.not.found"));
    }

    public Drink addDrink(DrinkDto dto) {
        Drink drink = new Drink();

        drink.setName(dto.getName());
        drink.setPrice(dto.getPrice());
        drink.setDescription(dto.getDescription());
        drink.setSparkling(dto.isSparkling());
        drink.setHasCaffeine(dto.isHasCaffeine());
        drink.setSugarLevel(dto.getSugarLevel());

        return drinkRepository.save(drink);
    }

    public Drink updateDrink(Long id, DrinkDto dto) {
        Drink drink = getDrink(id);

        drink.setName(dto.getName());
        drink.setPrice(dto.getPrice());
        drink.setDescription(dto.getDescription());
        drink.setSparkling(dto.isSparkling());
        drink.setHasCaffeine(dto.isHasCaffeine());
        drink.setSugarLevel(dto.getSugarLevel());
        drink.setActive(true);
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id) {
        drinkRepository.delete(getDrink(id));
    }

    public List<SugarLevelEnum> getSugarLevels() {
        return List.of(SugarLevelEnum.values());
    }
}

package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Soup;
import upt.cafetaria.backend.model.web.SoupDto;
import upt.cafetaria.backend.repository.SoupRepository;

import java.util.List;

@Service
public class SoupService {
    @Autowired
    private SoupRepository soupRepository;

    public List<Soup> getSoups() { return soupRepository.findAll();}

    public Soup getSoup(long id) {
        return soupRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "soup.not.found"));
    }

    public Soup addSoup(SoupDto dto) {
        Soup soup = new Soup();

        soup.setName(dto.getName());
        soup.setPrice(dto.getPrice());
        soup.setDescription(dto.getDescription());
        return soupRepository.save(soup);
    }

    public Soup updateSoup(Long id, SoupDto dto) {
        Soup soup = getSoup(id);

        soup.setName(dto.getName());
        soup.setPrice(dto.getPrice());
        soup.setDescription(dto.getDescription());

        return soupRepository.save(soup);
    }

    public void deleteSoup(Long id) {
        soupRepository.delete(getSoup(id));
    }
}
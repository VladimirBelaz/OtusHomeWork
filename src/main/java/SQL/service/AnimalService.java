package SQL.service;

import SQL.dao.IAnimalTable;
import SQL.dto.Animal;

import java.util.List;

public class AnimalService {
    IAnimalTable animalTable;

    public AnimalService (IAnimalTable animalTable) {
       this.animalTable = animalTable;
    }

    public List<Animal> findAll() {
        return animalTable.findAll();
    }
}

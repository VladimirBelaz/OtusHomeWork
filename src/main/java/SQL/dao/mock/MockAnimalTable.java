package SQL.dao.mock;

import SQL.dao.IAnimalTable;
import SQL.dto.Animal;

import java.util.List;

public class MockAnimalTable implements IAnimalTable {
    @Override
    public List<Animal> findAll() {
        return List.of(new Animal(1, "Bobik"), new Animal(2, "Barsik"));
    }
}

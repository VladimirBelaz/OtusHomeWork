package SQL.dao;

import SQL.dto.Animal;

import java.util.List;

public interface IAnimalTable {
    List<Animal> findAll();
}

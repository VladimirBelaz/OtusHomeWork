package db.dao;

import db.dto.Animal;
import java.util.List;

public interface IAnimalTable {
    List<Animal> findAll();
    void save(Animal animal);
    void updateAnimalById(Animal animal);
    void deleteById(int id);
    List<Animal> findByType(String type);
    Animal findById(int id);
}
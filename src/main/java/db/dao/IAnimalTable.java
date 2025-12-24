package db.dao;

import db.dto.Animal;
import java.util.List;

public interface IAnimalTable {
    List<Animal> findAll();
    void save(Animal animal);
    void updateAnimalById(Animal animal);
    void deleteById(int id);
    List<Animal> findByType(String type);
    List<Animal> findByColor(String color);
    List<Animal> findByAgeRange(int minAge, int maxAge);
    Animal findById(int id);
}
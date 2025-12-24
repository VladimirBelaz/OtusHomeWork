package db.service;

import db.dao.IAnimalTable;
import db.dto.Animal;

import java.util.List;

public class AnimalService {
    private IAnimalTable animalTable;

    public AnimalService(IAnimalTable animalTable) {
        this.animalTable = animalTable;
    }

    public List<Animal> findAll() {
        return animalTable.findAll();
    }

    public void saveAnimal(String name, String type) {
        Animal animal = new Animal(name, type);
        animalTable.save(animal);
    }

    public void updateAnimal(int id, String name, String type) {
        Animal animal = new Animal(id, name, type);
        animalTable.updateAnimalById(animal);
    }

    public void deleteAnimal(int id) {
        animalTable.deleteById(id);
    }

    public List<Animal> findAnimalsByType(String type) {
        return animalTable.findByType(type);
    }

    public Animal findAnimalById(int id) {
        return animalTable.findById(id);
    }

    public void printAllAnimals() {
        List<Animal> animals = findAll();
        if (animals.isEmpty()) {
            System.out.println("В базе данных нет животных");
        } else {
            System.out.println("Список всех животных:");
            animals.forEach(System.out::println);
        }
    }

    public void printAnimalsByType(String type) {
        List<Animal> animals = findAnimalsByType(type);
        if (animals.isEmpty()) {
            System.out.println("Животных типа '" + type + "' не найдено");
        } else {
            System.out.println("Животные типа '" + type + "':");
            animals.forEach(System.out::println);
        }
    }
}
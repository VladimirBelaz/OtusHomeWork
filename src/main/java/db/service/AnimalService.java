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

    // Метод принимает объект Animal для сохранения
    public void saveAnimal(Animal animal) {
        animalTable.save(animal);
        System.out.println("✓ Животное сохранено: " + animal.getName());
    }

    // Метод принимает объект Animal для обновления (должен содержать ID)
    public void updateAnimal(Animal animal) {
        animalTable.updateAnimalById(animal);
        System.out.println("✓ Животное обновлено: ID=" + animal.getId());
    }

    public void deleteAnimal(int id) {
        animalTable.deleteById(id);
        System.out.println("✓ Удаление животного с ID=" + id + " выполнено");
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
            System.out.println("Нет животных");
            return;
        }
        System.out.println("Все животные (" + animals.size() + "):");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void printAnimals(List<Animal> animals, String title) {
        if (animals == null || animals.isEmpty()) {
            System.out.println(title + " не найдено");
        } else {
            System.out.println("\n" + title + " (" + animals.size() + " животных):");
            for (Animal animal : animals) {
                System.out.println(animal);
            }
        }
    }
}
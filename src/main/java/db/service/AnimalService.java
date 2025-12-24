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

    public void saveAnimal(String name, String type, String color, Integer age, Integer weight) {
        Animal animal = new Animal(name, type, color, age, weight);
        animalTable.save(animal);
    }

    public void updateAnimal(int id, String name, String type, String color, Integer age, Integer weight) {
        Animal animal = new Animal(id, name, type, color, age, weight);
        animalTable.updateAnimalById(animal);
    }

    public void deleteAnimal(int id) {
        animalTable.deleteById(id);
    }

    public List<Animal> findAnimalsByType(String type) {
        return animalTable.findByType(type);
    }

    public List<Animal> findAnimalsByColor(String color) {
        return animalTable.findByColor(color);
    }

    public List<Animal> findAnimalsByAgeRange(int minAge, int maxAge) {
        return animalTable.findByAgeRange(minAge, maxAge);
    }

    public Animal findAnimalById(int id) {
        return animalTable.findById(id);
    }

    public void printAllAnimals() {
        List<Animal> animals = findAll();
        if (animals.isEmpty()) {
            System.out.println("В базе данных нет животных");
        } else {
            System.out.println("Список всех животных (" + animals.size() + " записей):");
            System.out.println("==================================================================");
            System.out.printf("%-5s %-15s %-10s %-15s %-5s %-5s%n",
                    "ID", "Имя", "Тип", "Цвет", "Возр", "Вес");
            System.out.println("==================================================================");
            for (Animal animal : animals) {
                System.out.printf("%-5d %-15s %-10s %-15s %-5d %-5d%n",
                        animal.getId(),
                        animal.getName() != null ? animal.getName() : "-",
                        animal.getType() != null ? animal.getType() : "-",
                        animal.getColor() != null ? animal.getColor() : "-",
                        animal.getAge() != null ? animal.getAge() : 0,
                        animal.getWeight() != null ? animal.getWeight() : 0);
            }
            System.out.println("==================================================================");
        }
    }

    public void printAnimals(List<Animal> animals, String title) {
        if (animals.isEmpty()) {
            System.out.println(title + " не найдено");
        } else {
            System.out.println(title + " (" + animals.size() + " записей):");
            System.out.println("==================================================================");
            System.out.printf("%-5s %-15s %-10s %-15s %-5s %-5s%n",
                    "ID", "Имя", "Тип", "Цвет", "Возр", "Вес");
            System.out.println("==================================================================");
            for (Animal animal : animals) {
                System.out.printf("%-5d %-15s %-10s %-15s %-5d %-5d%n",
                        animal.getId(),
                        animal.getName() != null ? animal.getName() : "-",
                        animal.getType() != null ? animal.getType() : "-",
                        animal.getColor() != null ? animal.getColor() : "-",
                        animal.getAge() != null ? animal.getAge() : 0,
                        animal.getWeight() != null ? animal.getWeight() : 0);
            }
            System.out.println("==================================================================");
        }
    }
}
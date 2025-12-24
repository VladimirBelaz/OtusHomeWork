package db.dao.mock;

import db.dao.IAnimalTable;
import db.dto.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockAnimalTable implements IAnimalTable {
    private List<Animal> animals = new ArrayList<>();
    private int idCounter = 1;

    public MockAnimalTable() {
        // Добавляем тестовые данные
        animals.add(new Animal(idCounter++, "Барсик", "Кошка"));
        animals.add(new Animal(idCounter++, "Шарик", "Собака"));
        animals.add(new Animal(idCounter++, "Кеша", "Попугай"));
        animals.add(new Animal(idCounter++, "Мурзик", "Кошка"));
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(animals);
    }

    @Override
    public void save(Animal animal) {
        animal.setId(idCounter++);
        animals.add(animal);
        System.out.println("Mock: Животное сохранено - " + animal.getName());
    }

    @Override
    public void updateAnimalById(Animal animal) {
        animals.removeIf(a -> a.getId() == animal.getId());
        animals.add(animal);
        System.out.println("Mock: Животное обновлено - ID=" + animal.getId());
    }

    @Override
    public void deleteById(int id) {
        animals.removeIf(a -> a.getId() == id);
        System.out.println("Mock: Животное удалено - ID=" + id);
    }

    @Override
    public List<Animal> findByType(String type) {
        return animals.stream()
                .filter(animal -> animal.getType().toLowerCase().contains(type.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Animal findById(int id) {
        return animals.stream()
                .filter(animal -> animal.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
package db.dao.mock;

import db.dao.IAnimalTable;
import db.dto.Animal;
import java.util.ArrayList;
import java.util.List;


public class MockAnimalTable implements IAnimalTable {
    private List<Animal> animals = new ArrayList<>();
    private int idCounter = 1;

    public MockAnimalTable() {
        // Добавляем тестовые данные
        animals.add(new Animal(idCounter++, "Барсик", "Кошка", "Рыжий", 3, 4));
        animals.add(new Animal(idCounter++, "Мурка", "Кошка", "Серый", 2, 3));
        animals.add(new Animal(idCounter++, "Шарик", "Собака", "Черный", 5, 15));
        animals.add(new Animal(idCounter++, "Кеша", "Попугай", "Зеленый", 1, 1));
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
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == animal.getId()) {
                animals.set(i, animal);
                System.out.println("Mock: Животное обновлено - ID=" + animal.getId());
                return;
            }
        }
    }

    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == id) {
                animals.remove(i);
                System.out.println("Mock: Животное удалено - ID=" + id);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Animal> findByType(String type) {
        List<Animal> result = new ArrayList<>();
        String searchType = type.toLowerCase();

        for (Animal animal : animals) {
            if (animal.getType().toLowerCase().contains(searchType)) {
                result.add(animal);
            }
        }
        return result;
    }

    @Override
    public Animal findById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }
}
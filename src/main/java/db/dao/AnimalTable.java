package db.dao;

import db.dto.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements IAnimalTable {
    public AnimalTable() {
        super("animal");
        columns.put("id", "bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY");
        columns.put("name", "varchar(15)");
        columns.put("type", "varchar(10)");
        columns.put("color", "varchar(20)");
        columns.put("age", "int");
        columns.put("weight", "int");
        create();
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " ORDER BY id";

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(createAnimalFromResultSet(r));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении всех животных: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public void save(Animal animal) {
        String sql = String.format(
                "INSERT INTO %s (name, type, color, age, weight) VALUES ('%s', '%s', '%s', %s, %s)",
                tableName,
                animal.getName(),
                animal.getType(),
                animal.getColor(),
                animal.getAge() != null ? animal.getAge().toString() : "NULL",
                animal.getWeight() != null ? animal.getWeight().toString() : "NULL"
        );

        try {
            connectionManager.executeUpdate(sql);
            System.out.println("Животное сохранено: " + animal.getName());
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении животного: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnimalById(Animal animal) {
        String sql = String.format(
                "UPDATE %s SET name = '%s', type = '%s', color = '%s', age = %s, weight = %s WHERE id = %d",
                tableName,
                animal.getName(),
                animal.getType(),
                animal.getColor(),
                animal.getAge() != null ? animal.getAge().toString() : "NULL",
                animal.getWeight() != null ? animal.getWeight().toString() : "NULL",
                animal.getId()
        );

        try {
            int rowsAffected = connectionManager.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Животное с ID=" + animal.getId() + " обновлено");
            } else {
                System.out.println("Животное с ID=" + animal.getId() + " не найдено");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении животного: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = String.format("DELETE FROM %s WHERE id = %d", tableName, id);

        try {
            int rowsAffected = connectionManager.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Животное с ID=" + id + " удалено");
            } else {
                System.out.println("Животное с ID=" + id + " не найдено");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при удалении животного: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Animal> findByType(String type) {
        List<Animal> animals = new ArrayList<>();
        String sql = String.format(
                "SELECT * FROM %s WHERE LOWER(type) LIKE LOWER('%%%s%%') ORDER BY id",
                tableName, type
        );

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(createAnimalFromResultSet(r));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по типу: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public List<Animal> findByColor(String color) {
        List<Animal> animals = new ArrayList<>();
        String sql = String.format(
                "SELECT * FROM %s WHERE LOWER(color) LIKE LOWER('%%%s%%') ORDER BY id",
                tableName, color
        );

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(createAnimalFromResultSet(r));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по цвету: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public List<Animal> findByAgeRange(int minAge, int maxAge) {
        List<Animal> animals = new ArrayList<>();
        String sql = String.format(
                "SELECT * FROM %s WHERE age BETWEEN %d AND %d ORDER BY age",
                tableName, minAge, maxAge
        );

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(createAnimalFromResultSet(r));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по возрасту: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public Animal findById(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id = %d", tableName, id);

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            if (r.next()) {
                return createAnimalFromResultSet(r);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Вспомогательный метод для создания объекта Animal из ResultSet
    private Animal createAnimalFromResultSet(ResultSet r) throws SQLException {
        return new Animal(
                r.getInt("id"),
                r.getString("name"),
                r.getString("type"),
                r.getString("color"),
                r.getInt("age"),
                r.getInt("weight")
        );
    }
}
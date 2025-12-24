package db.dao;

import db.dto.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalsTable extends AbsTable implements IAnimalTable {
    public AnimalsTable() {
        super("animal");
        columns.put("id", "bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY");
        columns.put("name", "varchar(50)");
        columns.put("type", "varchar(50)");
        create();
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(new Animal(
                        r.getInt("id"),
                        r.getString("name"),
                        r.getString("type")
                ));
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
                "INSERT INTO %s (name, type) VALUES ('%s', '%s')",
                tableName, animal.getName(), animal.getType()
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
                "UPDATE %s SET name = '%s', type = '%s' WHERE id = %d",
                tableName, animal.getName(), animal.getType(), animal.getId()
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
                "SELECT * FROM %s WHERE LOWER(type) LIKE LOWER('%%%s%%')",
                tableName, type
        );

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            while (r.next()) {
                animals.add(new Animal(
                        r.getInt("id"),
                        r.getString("name"),
                        r.getString("type")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по типу: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public Animal findById(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id = %d", tableName, id);

        try (ResultSet r = connectionManager.executeQuery(sql)) {
            if (r.next()) {
                return new Animal(
                        r.getInt("id"),
                        r.getString("name"),
                        r.getString("type")
                );
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске по ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
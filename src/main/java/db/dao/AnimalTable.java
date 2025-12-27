package db.dao;

import db.dto.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements IAnimalTable {
    public AnimalTable() {
        super("animal");
        columns.put("id", "bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY");
        columns.put("name", "varchar(100)");
        columns.put("type", "varchar(100)");
        columns.put("color", "varchar(100)");
        columns.put("age", "int");
        columns.put("weight", "int");
        create();
    }

    @Override
    public List<Animal> findAll() {
        return executeQuery("SELECT * FROM " + tableName + " ORDER BY id");
    }

    @Override
    public void save(Animal animal) {
        executeUpdate(String.format(
                "INSERT INTO %s (name, type, color, age, weight) " +
                        "VALUES ('%s', '%s', '%s', %d, %d)",
                tableName, animal.getName(), animal.getType(), animal.getColor(),
                animal.getAge(), animal.getWeight()
        ));
    }

    @Override
    public void updateAnimalById(Animal animal) {
        executeUpdate(String.format(
                "UPDATE %s " +
                        "SET name='%s', type='%s', color='%s', age=%d, weight=%d " +
                        "WHERE id=%d",
                tableName, animal.getName(), animal.getType(), animal.getColor(),
                animal.getAge(), animal.getWeight(), animal.getId()
        ));
    }

    @Override
    public boolean deleteById(int id) {
        String sql = String.format("DELETE FROM %s WHERE id = %d", tableName, id);

        try {
            int rowsAffected = connectionManager.executeUpdate(sql);
            return rowsAffected > 0;  // возвращаем true, если удалили хотя бы одну строку
        } catch (Exception e) {
            System.err.println("Ошибка при удалении животного: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Animal> findByType(String type) {
        return executeQuery(
                "SELECT * " +
                        "FROM " + tableName +
                        " WHERE LOWER(type) " +
                        "LIKE LOWER('%" + type + "%') " +
                        "ORDER BY id"
        );
    }

    @Override
    public Animal findById(int id) {
        List<Animal> animals = executeQuery(
                String.format("SELECT * " +
                        "FROM %s " +
                        "WHERE id = %d", tableName, id)
        );
        return animals.isEmpty() ? null : animals.get(0);
    }

    private List<Animal> executeQuery(String sql) {
        List<Animal> animals = new ArrayList<>();
        try (ResultSet rs = connectionManager.executeQuery(sql)) {
            while (rs.next()) {
                try {
                    animals.add(new Animal(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("color"),
                            rs.getInt("age"),
                            rs.getInt("weight")
                    ));
                } catch (SQLException e) {

                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка запроса: " + e.getMessage());
        }
        return animals;
    }

    private void executeUpdate(String sql) {
        try {
            connectionManager.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println("Ошибка выполнения: " + e.getMessage());
        }
    }
}
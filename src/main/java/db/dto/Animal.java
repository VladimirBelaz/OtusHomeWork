package db.dto;

public class Animal {
    private int id;
    private String name;
    private String type;
    private String color;
    private Integer age;
    private Integer weight;

    // Конструктор для создания нового животного (без ID)
    public Animal(String name, String type, String color, Integer age, Integer weight) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }

    // Конструктор для загрузки из БД (с ID)
    public Animal(int id, String name, String type, String color, Integer age, Integer weight) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Animal{id=%d, name='%s', type='%s', color='%s', age=%d, weight=%d}",
                id, name, type, color, age, weight);
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
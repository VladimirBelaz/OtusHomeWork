package db.dto;

public class Animal {
    private int id;
    private String name;
    private String type;
    private String color;
    private int age;
    private int weight;

    public Animal(String name, String type, String color, int age, int weight) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }

    public Animal(int id, String name, String type, String color, int age, int weight) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }

    public int getId() { return id; }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getColor() {
        return color;
    }
    public int getAge() {
        return age;
    }
    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Имя: %s, Тип: %s, Цвет: %s, Возраст: %d, Вес: %d",
                id, name, type, color, age, weight);
    }
}
package db.dto;

public class Animal {
    private int id;
    private String name;
    private String type; // Тип животного (например: "Млекопитающее", "Птица", "Рыба")

    public Animal(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
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
}
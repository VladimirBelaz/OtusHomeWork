package db;

import db.dto.Animal;
import db.factory.AnimalTableFactory;
import db.service.AnimalService;
import db.utils.ConnectionManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    static AnimalService animalService;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        animalService = new AnimalService(new AnimalTableFactory().getProd());
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");
            switch (choice) {
                case 1: animalService.printAllAnimals(); break;
                case 2: addAnimal(); break;
                case 3: updateAnimal(); break;
                case 4: deleteAnimal(); break;
                case 5: findAnimalsByType(); break;
                case 6: findAnimalById(); break;
                case 0: exit = true; System.out.println("Завершение работы..."); break;
                default: System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println();
        }
        ConnectionManager.getInstance().close();
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Показать всех животных");
        System.out.println("2. Добавить новое животное");
        System.out.println("3. Редактировать животное");
        System.out.println("4. Удалить животное");
        System.out.println("5. Найти животных по типу");
        System.out.println("6. Найти животное по ID");
        System.out.println("0. Выход");
    }

    private static void addAnimal() {
        Animal animal = inputAnimalData("=== Добавление нового животного ===");
        animalService.saveAnimal(animal);
    }

    private static void updateAnimal() {
        System.out.println("\n=== Редактирование животного ===");
        int id = getIntInput("Введите ID животного: ");

        Animal currentAnimal = animalService.findAnimalById(id);
        if (currentAnimal == null) {
            System.out.println("Животное с ID=" + id + " не найдено");
            return;
        }

        System.out.println("Текущее животное: " + currentAnimal);
        Animal updatedAnimal = inputAnimalData("\nВведите новые данные:");

        updatedAnimal.setId(id);

        animalService.updateAnimal(updatedAnimal);
    }

    private static void deleteAnimal() {
        System.out.println("\n=== Удаление животного ===");
        int id = getIntInput("Введите ID животного для удаления: ");
        animalService.deleteAnimal(id);
    }

    private static void findAnimalsByType() {
        System.out.println("\n=== Поиск животных по типу ===");
        System.out.print("Введите тип животного: ");
        String type = scanner.nextLine();
        List<Animal> animals = animalService.findAnimalsByType(type);
        animalService.printAnimals(animals, "Животные типа '" + type + "'");
    }

    private static void findAnimalById() {
        System.out.println("\n=== Поиск животного по ID ===");
        int id = getIntInput("Введите ID животного: ");
        Animal animal = animalService.findAnimalById(id);

        if (animal != null) {
            System.out.println("\nНайдено животное:");
            System.out.println(animal);
        } else {
            System.out.println("Животное с ID=" + id + " не найдено");
        }
    }

    private static Animal inputAnimalData(String title) {
        System.out.println("\n" + title);

        String name = getNonEmptyString("Имя животного: ");
        String type = getNonEmptyString("Тип животного: ");
        String color = getNonEmptyString("Цвет животного: ");
        int age = getPositiveInt("Возраст животного: ");
        int weight = getPositiveInt("Вес животного: ");

        return new Animal(name, type, color, age, weight);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.isEmpty() && input.matches("-?\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Введите целое число!");
        }
    }

    private static int getPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.isEmpty() && input.matches("[1-9]\\d*")) {
                return Integer.parseInt(input);
            }
            System.out.println("Введите положительное целое число!");
        }
    }

    private static String getNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не может быть пустым!");
        }
    }
}
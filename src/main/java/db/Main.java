package db;

import db.dto.Animal;
import db.factory.AnimalTableFactory;
import db.service.AnimalService;
import db.utils.ConnectionManager;

import java.util.Scanner;

public class Main {
    static AnimalService animalService;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Используем фабрику для выбора реализации (продакшн или мок)
        animalService = new AnimalService(new AnimalTableFactory().getProd());

        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    // Показать всех животных
                    animalService.printAllAnimals();
                    break;

                case 2:
                    // Добавить животное
                    addAnimal();
                    break;

                case 3:
                    // Редактировать животное
                    updateAnimal();
                    break;

                case 4:
                    // Удалить животное
                    deleteAnimal();
                    break;

                case 5:
                    // Найти животных по типу
                    findAnimalsByType();
                    break;

                case 6:
                    // Найти животное по ID
                    findAnimalById();
                    break;

                case 0:
                    // Выход
                    exit = true;
                    System.out.println("Завершение работы...");
                    break;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println();
        }

        // Закрываем соединение с БД
        ConnectionManager.getInstance().close();
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Система управления животными ===");
        System.out.println("1. Показать всех животных");
        System.out.println("2. Добавить новое животное");
        System.out.println("3. Редактировать животное");
        System.out.println("4. Удалить животное");
        System.out.println("5. Найти животных по типу");
        System.out.println("6. Найти животное по ID");
        System.out.println("0. Выход");
    }

    private static void addAnimal() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите тип животного: ");
        String type = scanner.nextLine();

        animalService.saveAnimal(name, type);
        System.out.println("Животное успешно добавлено!");
    }

    private static void updateAnimal() {
        int id = getIntInput("Введите ID животного для редактирования: ");

        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            System.out.println("Животное с ID=" + id + " не найдено");
            return;
        }

        System.out.println("Текущие данные: " + animal);
        System.out.print("Введите новое имя (оставьте пустым для сохранения текущего): ");
        String name = scanner.nextLine();

        System.out.print("Введите новый тип (оставьте пустым для сохранения текущего): ");
        String type = scanner.nextLine();

        if (!name.isEmpty()) {
            animal.setName(name);
        }
        if (!type.isEmpty()) {
            animal.setType(type);
        }

        animalService.updateAnimal(animal.getId(), animal.getName(), animal.getType());
        System.out.println("Данные обновлены!");
    }

    private static void deleteAnimal() {
        int id = getIntInput("Введите ID животного для удаления: ");
        animalService.deleteAnimal(id);
    }

    private static void findAnimalsByType() {
        System.out.print("Введите тип животного для поиска: ");
        String type = scanner.nextLine();
        animalService.printAnimalsByType(type);
    }

    private static void findAnimalById() {
        int id = getIntInput("Введите ID животного: ");
        Animal animal = animalService.findAnimalById(id);

        if (animal != null) {
            System.out.println("Найдено животное: " + animal);
        } else {
            System.out.println("Животное с ID=" + id + " не найдено");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число!");
            }
        }
    }
}
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
        System.out.println("=== Система управления животными ===");

        // Используем фабрику для выбора реализации
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
                    // Найти животных по цвету
                    findAnimalsByColor();
                    break;

                case 7:
                    // Найти животных по возрасту
                    findAnimalsByAge();
                    break;

                case 8:
                    // Найти животное по ID
                    findAnimalById();
                    break;

                case 9:
                    // Добавить тестовые данные
                    addTestData();
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
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Показать всех животных");
        System.out.println("2. Добавить новое животное");
        System.out.println("3. Редактировать животное");
        System.out.println("4. Удалить животное");
        System.out.println("5. Найти животных по типу");
        System.out.println("6. Найти животных по цвету");
        System.out.println("7. Найти животных по возрасту");
        System.out.println("8. Найти животное по ID");
        System.out.println("9. Добавить тестовые данные");
        System.out.println("0. Выход");
    }

    private static void addAnimal() {
        System.out.println("\n=== Добавление нового животного ===");

        System.out.print("Введите имя животного (макс 15 символов): ");
        String name = scanner.nextLine();

        System.out.print("Введите тип животного (макс 10 символов): ");
        String type = scanner.nextLine();

        System.out.print("Введите цвет животного (макс 20 символов): ");
        String color = scanner.nextLine();

        Integer age = getNullableIntInput("Введите возраст животного (или Enter, чтобы пропустить): ");
        Integer weight = getNullableIntInput("Введите вес животного (или Enter, чтобы пропустить): ");

        animalService.saveAnimal(name, type, color, age, weight);
        System.out.println("Животное успешно добавлено!");
    }

    private static void updateAnimal() {
        System.out.println("\n=== Редактирование животного ===");
        int id = getIntInput("Введите ID животного для редактирования: ");

        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            System.out.println("Животное с ID=" + id + " не найдено");
            return;
        }

        System.out.println("Текущие данные:");
        System.out.println("ID: " + animal.getId());
        System.out.println("Имя: " + (animal.getName() != null ? animal.getName() : "-"));
        System.out.println("Тип: " + (animal.getType() != null ? animal.getType() : "-"));
        System.out.println("Цвет: " + (animal.getColor() != null ? animal.getColor() : "-"));
        System.out.println("Возраст: " + (animal.getAge() != null ? animal.getAge() : "-"));
        System.out.println("Вес: " + (animal.getWeight() != null ? animal.getWeight() : "-"));

        System.out.print("\nВведите новое имя (Enter - оставить текущее): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            animal.setName(name);
        }

        System.out.print("Введите новый тип (Enter - оставить текущее): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) {
            animal.setType(type);
        }

        System.out.print("Введите новый цвет (Enter - оставить текущее): ");
        String color = scanner.nextLine();
        if (!color.isEmpty()) {
            animal.setColor(color);
        }

        Integer age = getNullableIntInput("Введите новый возраст (Enter - оставить текущее): ");
        if (age != null) {
            animal.setAge(age);
        }

        Integer weight = getNullableIntInput("Введите новый вес (Enter - оставить текущее): ");
        if (weight != null) {
            animal.setWeight(weight);
        }

        animalService.updateAnimal(
                animal.getId(),
                animal.getName(),
                animal.getType(),
                animal.getColor(),
                animal.getAge(),
                animal.getWeight()
        );
        System.out.println("Данные обновлены!");
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

    private static void findAnimalsByColor() {
        System.out.println("\n=== Поиск животных по цвету ===");
        System.out.print("Введите цвет животного: ");
        String color = scanner.nextLine();
        List<Animal> animals = animalService.findAnimalsByColor(color);
        animalService.printAnimals(animals, "Животные цвета '" + color + "'");
    }

    private static void findAnimalsByAge() {
        System.out.println("\n=== Поиск животных по возрасту ===");
        int minAge = getIntInput("Введите минимальный возраст: ");
        int maxAge = getIntInput("Введите максимальный возраст: ");

        if (minAge > maxAge) {
            System.out.println("Минимальный возраст не может быть больше максимального!");
            return;
        }

        List<Animal> animals = animalService.findAnimalsByAgeRange(minAge, maxAge);
        animalService.printAnimals(animals,
                "Животные в возрасте от " + minAge + " до " + maxAge + " лет");
    }

    private static void findAnimalById() {
        System.out.println("\n=== Поиск животного по ID ===");
        int id = getIntInput("Введите ID животного: ");
        Animal animal = animalService.findAnimalById(id);

        if (animal != null) {
            System.out.println("\nНайдено животное:");
            System.out.println("ID: " + animal.getId());
            System.out.println("Имя: " + (animal.getName() != null ? animal.getName() : "-"));
            System.out.println("Тип: " + (animal.getType() != null ? animal.getType() : "-"));
            System.out.println("Цвет: " + (animal.getColor() != null ? animal.getColor() : "-"));
            System.out.println("Возраст: " + (animal.getAge() != null ? animal.getAge() : "-"));
            System.out.println("Вес: " + (animal.getWeight() != null ? animal.getWeight() : "-"));
        } else {
            System.out.println("Животное с ID=" + id + " не найдено");
        }
    }

    private static void addTestData() {
        System.out.println("\n=== Добавление тестовых данных ===");

        // Тестовые данные
        animalService.saveAnimal("Барсик", "Кошка", "Рыжий", 3, 4);
        animalService.saveAnimal("Мурка", "Кошка", "Серый", 2, 3);
        animalService.saveAnimal("Шарик", "Собака", "Черный", 5, 15);
        animalService.saveAnimal("Рекс", "Собака", "Коричневый", 7, 20);
        animalService.saveAnimal("Кеша", "Попугай", "Зеленый", 1, 1);
        animalService.saveAnimal("Гоша", "Попугай", "Синий", 2, 1);
        animalService.saveAnimal("Зорька", "Лошадь", "Рыжий", 10, 500);
        animalService.saveAnimal("Буренка", "Корова", "Черно-белый", 6, 400);

        System.out.println("Тестовые данные добавлены!");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Пожалуйста, введите число!");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число!");
            }
        }
    }

    private static Integer getNullableIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    return null; // Пользователь нажал Enter, возвращаем null
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число или нажмите Enter!");
            }
        }
    }
}
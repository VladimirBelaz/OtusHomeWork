package Homework;

import Homework.animals.Animal;
import Homework.animals.Color;
import Homework.factory.AnimalFactory;
import Homework.factory.AnimalType;


import java.util.*;

public class AnimalApp {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory = new AnimalFactory();

        Scanner scanner = new Scanner(System.in);
        Command currentCommand = null;

        System.out.println("=== Программа Животные ===");
        while (currentCommand != Command.EXIT) {
            currentCommand = getCommand(scanner);
            if(currentCommand == Command.LIST) {
                if(animals.isEmpty()) {
                    System.out.println("Список пуст.");
                }
                for (Animal animal: animals) {
                    System.out.println(animal);
                }
            } else if (currentCommand == Command.ADD) {
                AnimalType animalType = getAnimal(scanner);
                Animal animal = animalFactory.create(animalType);
                animal.setColor(getColor(scanner));
                animal.setName(getName(scanner));
                animal.setAge(getIntInput(scanner, "возраст"));
                animal.setWeight(getIntInput(scanner, "вес"));
                animals.add(animal);
                animal.say();
            }
        }
    }

    private static Command getCommand(Scanner scanner) {
       String commandInput = null;
       while (Command.doesNotContains(commandInput)) {
           if(commandInput != null) {
               System.out.println("Введена неверная команда");
           }
           System.out.printf("Введите одну из команд (%s) :", String.join("/", Command.NAMES));
           commandInput = scanner.nextLine();
       }
       return Command.fromString(commandInput);
    }

    private static AnimalType getAnimal(Scanner scanner) {
        String animalInput = null;
        while (AnimalType.doesNotContains(animalInput)) {
            if(animalInput != null) {
                System.out.println("Введена неверная команда");
            }
            System.out.printf("Введите одну из команд (%s) :", String.join("/", AnimalType.NAMES));
            animalInput = scanner.nextLine();
        }
        return AnimalType.fromString(animalInput);
    }

    public static String getName(Scanner scanner) {
        String nameInput = null;
        while (nameInput == null || nameInput.isEmpty()) {
            if (nameInput != null) {
                System.out.println("Ошибка: Имя не может быть пустым! Попробуйте снова.");
            }
            System.out.println("Введите имя:");
            nameInput = scanner.nextLine().trim();
        }
        return nameInput;
    }

    private static Color getColor(Scanner scanner) {
        String colorInput = null;

        while (true) {
            if (colorInput != null) {
                System.out.println("Ошибка: Цвет не может быть пустым! Попробуйте снова.");
            }

            System.out.println("Введите цвет животного:");
            System.out.println("Доступные цвета: " + String.join("/", Color.RUSSIAN_NAMES));
            colorInput = scanner.nextLine().trim();

            if (colorInput.isEmpty()) {
                continue;
            }

            for (Color color : Color.values()) {
                if (color.getValue().equalsIgnoreCase(colorInput)) {
                    return color;
                }
            }

            System.out.println("Ошибка: Неизвестный цвет '" + colorInput + "'!");
            colorInput = null;
        }
    }

    public static int getIntInput(Scanner scanner, String fieldName) {
        while (true) {
            System.out.println("Введите " + fieldName + ":");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ошибка: " + fieldName + " не может быть пустым! Попробуйте снова.");
                continue;
            }

            try {
                int age = Integer.parseInt(input);
                if (age < 0) {
                    System.out.println("Ошибка: " + fieldName + " не может быть отрицательным! Попробуйте снова.");
                    continue;
                }
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: " + fieldName + " должен быть целым числом! Попробуйте снова.");
            }
        }
    }
}






package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    static Scanner scanner;
    static DinnerConstructor dinnerConstructor;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        dinnerConstructor = new DinnerConstructor();


        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Такой команды нет!");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
        System.out.print("Ваш выбор: ");
    }

    private static void addNewDish() {


        System.out.println("Введите тип блюда: (например: напиток, гарнир, мясо)");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        boolean select = true;
        for (ArrayList<String> src : dinnerConstructor.dinnerMenuMap.values()) {
            if (src.contains(dishName)) {
                select = false;
            }
        }
        if (!select) {
            System.out.println("это блюдо уже есть в списке");
        } else {
            dinnerConstructor.saveNameMenu(dishType, dishName);
        }
    }

    private static void generateDishCombo() {

        if (!dinnerConstructor.dinnerMenuMap.isEmpty()) {
            ArrayList<String> keyMenuArray = new ArrayList<>();
            System.out.println("Начинаем конструировать обед...");
            System.out.println("Введите количество наборов, которые нужно сгенерировать: 1,2,3,4,5");
            int numberOfCombos = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
                    "Для завершения ввода введите пустую строку");
            ArrayList<String> selectedTypes = new ArrayList<>();

            String nextItem = scanner.nextLine();

            while (!nextItem.isEmpty()) {
                if (dinnerConstructor.checkType(nextItem)) {
                    selectedTypes.add(nextItem);
                } else {
                    System.out.println("Такого типа блюд пока не существует. Введите другой тип");
                }
                nextItem = scanner.nextLine();
            }
            dinnerConstructor.mixComboMenu(numberOfCombos, selectedTypes);
        }
    }
}
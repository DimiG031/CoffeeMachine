package org.example;

import java.util.Scanner;

public class ConsoleApp {

    public static void startConsoleApp() {
        System.out.println("Welcome!");
        System.out.println("Choose your drink:");
        System.out.println("1. Espresso. (Espresso is a strong, concentrated coffee drink prepared under high pressure.)");
        System.out.println("2. Latte. (Latte is a coffee drink with a large amount of milk, known for its smooth texture.)");
        System.out.println("3. Cappuccino. (Cappuccino is espresso with equal parts milk and milk foam.)");
        System.out.println("4. Mocha. (A mocha is a chocolate latte, a combination of espresso, milk, and cocoa.)");
        System.out.println("5. Americano. (Americano is espresso diluted with water, providing a lighter taste.)");
        System.out.println("6. Macchiato. (A macchiato is espresso with a small amount of milk foam.)");

        Scanner scanner = new Scanner(System.in);
        int drink = scanner.nextInt();

        String chosenDrink = "";

        switch (drink) {
            case 1:
                chosenDrink = "Espresso";
                break;
            case 2:
                chosenDrink = "Latte";
                break;
            case 3:
                chosenDrink = "Cappuccino";
                break;
            case 4:
                chosenDrink = "Mocha";
                break;
            case 5:
                chosenDrink = "Americano";
                break;
            case 6:
                chosenDrink = "Macchiato";
                break;
            default:
                System.out.println("You did not choose a correct option!");
                return;
        }

        System.out.println("You chose " + chosenDrink + "!");

        int sugar = getSugarAmount(scanner);
        System.out.println("You chose " + sugar + " teaspoons of sugar.");

        simulatePreparation();
        System.out.println(chosenDrink + " with " + sugar + " teaspoons of sugar is ready! Enjoy your drink!");
    }

    private static int getSugarAmount(Scanner scanner) {
        int sugar = -1;
        while (sugar < 0 || sugar > 6) {
            System.out.println("How much sugar would you like? (0-6 teaspoons)");
            sugar = scanner.nextInt();
            if (sugar < 0 || sugar > 6) {
                System.out.println("Invalid choice! Please choose a number between 0 and 6.");
            }
        }
        return sugar;
    }

    private static void simulatePreparation() {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\rPreparing your drink: " + i + "%");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        startConsoleApp();
    }
}

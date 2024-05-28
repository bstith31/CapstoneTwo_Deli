package com.ps;
// import java.util.HashMap;
// import java.util.Map;
import java.util.Scanner;

public class Sandwich extends Product {

    private String bread;
    private int size;
    private String meat;
    private String cheese;
    private String[] otherToppings;
    private String[] sauces;
    private boolean toasted;


    public Sandwich(String name, double price) {
        super(name, price);
    }

    @Override
    public void productSelection() {

        Scanner scanner = new Scanner(System.in);

        // Bread options
        String[] breads = {"white", "wheat", "rye", "wrap"};
        System.out.println("Select your bread:");
        for (int i = 0; i < breads.length; i++) {
            System.out.printf("%d. %s%n", i + 1, breads[i]);
        }
        bread = breads[scanner.nextInt() - 1];

        // Size options
        int[] sizes = {4, 8, 12};
        System.out.println("Select your sandwich size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d. %d inches%n", i + 1, sizes[i]);
        }
        size = sizes[scanner.nextInt() - 1];

        // Meat options
        String[] meats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
        System.out.println("Select meat:");
        for (int i = 0; i < meats.length; i++) {
            System.out.printf("%d. %s%n", i + 1, meats[i]);
        }
        meat = meats[scanner.nextInt() - 1];

        // Cheese options
        String[] cheeses = {"american", "provolone", "cheddar", "swiss"};
        System.out.println("Select cheese:");
        for (int i = 0; i < cheeses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, cheeses[i]);
        }
        cheese = cheeses[scanner.nextInt() - 1];

        // Other toppings
        String[] possibleToppings = {"lettuce", "tomato", "onion", "pickle", "olives"};
        System.out.println("Select other toppings:");
        for (int i = 0; i < possibleToppings.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleToppings[i]);
        }
        String[] toppingIndices = scanner.next().split(",");
        otherToppings = new String[toppingIndices.length];
        for (int i = 0; i < toppingIndices.length; i++) {
            otherToppings[i] = possibleToppings[Integer.parseInt(toppingIndices[i]) - 1];
        }

        // Sauces
        String[] possibleSauces = {"mayo", "mustard", "ketchup", "ranch", "BBQ"};
        System.out.println("Select sauces:");
        for (int i = 0; i < possibleSauces.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleSauces[i]);
        }
        String[] sauceIndices = scanner.next().split(",");
        sauces = new String[sauceIndices.length];
        for (int i = 0; i < sauceIndices.length; i++) {
            sauces[i] = possibleSauces[Integer.parseInt(sauceIndices[i]) - 1];
        }

        // Toasted
        System.out.print("Would you like the sandwich toasted? (1 for yes, 2 for no): ");
        toasted = scanner.nextInt() == 1;

        calculatePrice();
    }

    private void calculatePrice() {
        double sizePrice;
        double meatPrice;
        double cheesePrice;

        switch (size) {
            case 4:
                sizePrice = 5.50;
                meatPrice = 1.00;
                cheesePrice = 0.75;
                break;
            case 8:
                sizePrice = 7.00;
                meatPrice = 2.00;
                cheesePrice = 1.50;
                break;
            case 12:
                sizePrice = 8.50;
                meatPrice = 3.00;
                cheesePrice = 2.25;
                break;
            default:
                throw new IllegalArgumentException("Invalid sandwich size");
        }

        price = sizePrice + meatPrice + cheesePrice;
    }

    @Override
    public String toString() {
        return String.format("Sandwich - %d inches, %s, Meat: %s, Cheese: %s, Toppings: %s, Sauces: %s, Toasted: %s, Price: $%.2f",
                size, bread, meat, cheese, String.join(", ", otherToppings), String.join(", ", sauces), toasted ? "yes" : "no", price);
    }
}
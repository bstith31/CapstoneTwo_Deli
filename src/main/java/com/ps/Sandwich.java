package com.ps;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sandwich extends Product {

    private String bread;
    private int size;
    private String[] meats;
    private String[] cheeses;
    private String[] otherToppings;
    private String[] sauces;
    private boolean toasted;
    private Scanner scanner;

    public Sandwich(String name, double price) {
        super(name, price);
        this.scanner = new Scanner(System.in);
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getMeats() {
        return meats;
    }

    public void setMeats(String[] meats) {
        this.meats = meats;
    }

    public String[] getCheeses() {
        return cheeses;
    }

    public void setCheeses(String[] cheeses) {
        this.cheeses = cheeses;
    }

    public String[] getOtherToppings() {
        return otherToppings;
    }

    public void setOtherToppings(String[] otherToppings) {
        this.otherToppings = otherToppings;
    }

    public String[] getSauces() {
        return sauces;
    }

    public void setSauces(String[] sauces) {
        this.sauces = sauces;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
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
        setBread(breads[getUserInput(breads.length) - 1]);

        // Size options
        int[] sizes = {4, 8, 12};
        System.out.println("Select your sandwich size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d. %d inches%n", i + 1, sizes[i]);
        }
        setSize(sizes[getUserInput(sizes.length) - 1]);

        // Meat options
        String[] possibleMeats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
        System.out.println("Select meats:");
        for (int i = 0; i < possibleMeats.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleMeats[i]);
        }
        setMeats(getSelectedItems(scanner.nextLine(), possibleMeats, 1, 3));

        // Cheese options
        String[] possibleCheeses = {"american", "provolone", "cheddar", "swiss"};
        System.out.println("Select cheeses:");
        for (int i = 0; i < possibleCheeses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleCheeses[i]);
        }
        setCheeses(getSelectedItems(scanner.nextLine(), possibleCheeses, 0, possibleCheeses.length));

        // Other toppings
        String[] possibleToppings = {"lettuce", "tomato", "onion", "pickle", "olives"};
        System.out.println("Select other toppings:");
        for (int i = 0; i < possibleToppings.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleToppings[i]);
        }
        setOtherToppings(getSelectedItems(scanner.nextLine(), possibleToppings, 0, possibleToppings.length));

        // Sauces
        String[] possibleSauces = {"mayo", "mustard", "ketchup", "ranch", "BBQ"};
        System.out.println("Select sauces:");
        for (int i = 0; i < possibleSauces.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleSauces[i]);
        }
        setSauces(getSelectedItems(scanner.nextLine(), possibleSauces, 0, possibleSauces.length));

        // Toasted
        System.out.print("Would you like the sandwich toasted? (1 for yes, 2 for no): ");
        setToasted(getUserInput(2) == 1);

        calculatePrice();
        displaySandwichArt();
    }

    private String[] getSelectedItems(String input, String[] options, int minChoices, int maxChoices) {
        String[] indices = input.split(",");
        if (indices.length < minChoices || indices.length > maxChoices) {
            System.out.printf("Invalid number of selections. Please select between %d and %d items.%n", minChoices, maxChoices);
            return null;
        }

        String[] selectedItems = new String[indices.length];
        for (int i = 0; i < indices.length; i++) {
            try {
                int index = Integer.parseInt(indices[i].trim()) - 1;
                if (index >= 0 && index < options.length) {
                    selectedItems[i] = options[index];
                } else {
                    System.out.printf("Invalid selection: %d. Skipping.%n", index + 1);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.printf("Invalid input: %s. Skipping.%n", indices[i]);
            }
        }
        return selectedItems;
    }

    protected void calculatePrice() {
        double sizePrice;
        double meatPrice;
        double cheesePrice;

        switch (size) {
            case 4:
                sizePrice = 5.50;
                meatPrice = 1.00 * meats.length;
                cheesePrice = 0.75 * cheeses.length;
                break;
            case 8:
                sizePrice = 7.00;
                meatPrice = 2.00 * meats.length;
                cheesePrice = 1.50 * cheeses.length;
                break;
            case 12:
                sizePrice = 8.50;
                meatPrice = 3.00 * meats.length;
                cheesePrice = 2.25 * cheeses.length;
                break;
            default:
                throw new IllegalArgumentException("Invalid sandwich size");
        }

        price = sizePrice + meatPrice + cheesePrice;
    }

    private void displaySandwichArt() {
    }

    private int getUserInput(int upperBound) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= 1 && input <= upperBound) {
                    return input;
                } else {
                    System.out.printf("Invalid input. Please enter a number between 1 and %d.%n", upperBound);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }


    @Override
    public String toString() {
        return String.format("Sandwich - %d inches, %s, Meats: %s, Cheeses: %s, Toppings: %s, Sauces: %s, Toasted: %s, Price: $%.2f",
                size, bread, String.join(", ", meats), String.join(", ", cheeses), String.join(", ", otherToppings), String.join(", ", sauces), toasted ? "yes" : "no", price);
    }
}
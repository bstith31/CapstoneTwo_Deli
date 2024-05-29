package com.ps;
// import java.util.HashMap;
// import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;

public class Sandwich extends Product {

    private String bread;
    private int size;
    private String[] meats;
    private String[] cheeses;
    private String[] otherToppings;
    private String[] sauces;
    private boolean toasted;

    public Sandwich(String name, double price) {
        super(name, price);
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
        setBread(breads[scanner.nextInt() - 1]);

        // Size options
        int[] sizes = {4, 8, 12};
        System.out.println("Select your sandwich size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d. %d inches%n", i + 1, sizes[i]);
        }
        setSize(sizes[scanner.nextInt() - 1]);

        // Meat options
        String[] possibleMeats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
        System.out.println("Select meats:");
        for (int i = 0; i < possibleMeats.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleMeats[i]);
        }
        scanner.nextLine(); // Consume newline left-over
        setMeats(getSelectedItems(scanner.nextLine(), possibleMeats));

        // Cheese options
        String[] possibleCheeses = {"american", "provolone", "cheddar", "swiss"};
        System.out.println("Select cheeses:");
        for (int i = 0; i < possibleCheeses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleCheeses[i]);
        }
        setCheeses(getSelectedItems(scanner.nextLine(), possibleCheeses));

        // Other toppings
        String[] possibleToppings = {"lettuce", "tomato", "onion", "pickle", "olives"};
        System.out.println("Select other toppings:");
        for (int i = 0; i < possibleToppings.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleToppings[i]);
        }
        setOtherToppings(getSelectedItems(scanner.nextLine(), possibleToppings));

        // Sauces
        String[] possibleSauces = {"mayo", "mustard", "ketchup", "ranch", "BBQ"};
        System.out.println("Select sauces:");
        for (int i = 0; i < possibleSauces.length; i++) {
            System.out.printf("%d. %s%n", i + 1, possibleSauces[i]);
        }
        setSauces(getSelectedItems(scanner.nextLine(), possibleSauces));

        // Toasted
        System.out.print("Would you like the sandwich toasted? (1 for yes, 2 for no): ");
        setToasted(scanner.nextInt() == 1);

        calculatePrice();
    }

    private String[] getSelectedItems(String input, String[] options) {
        String[] indices = input.split(",");
        String[] selectedItems = new String[indices.length];
        for (int i = 0; i < indices.length; i++) {
            selectedItems[i] = options[Integer.parseInt(indices[i].trim()) - 1];
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


    @Override
    public String toString() {
        return String.format("Sandwich - %d inches, %s, Meats: %s, Cheeses: %s, Toppings: %s, Sauces: %s, Toasted: %s, Price: $%.2f",
                size, bread, String.join(", ", meats), String.join(", ", cheeses), String.join(", ", otherToppings), String.join(", ", sauces), toasted ? "yes" : "no", price);
    }
}
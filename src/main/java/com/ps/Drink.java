package com.ps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Drink extends Product {

    private String size;
    private Scanner scanner;
    public Drink(String name, double price) {
        super(name, price);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void productSelection() {

        Scanner scanner = new Scanner(System.in);

        String[] sizes = {"small", "medium", "large"};
        System.out.println("Select drink size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d. %s%n", i + 1, sizes[i]);
        }
        size = sizes[getUserInput(3) - 1];

        switch (size.toLowerCase()) {
            case "small":
                price = 2.00;
                break;
            case "medium":
                price = 2.50;
                break;
            case "large":
                price = 3.00;
                break;
            default:
                throw new IllegalArgumentException("Invalid drink size");
        }
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
        return String.format("Drink - %s, Price: $%.2f", size, price);
    }
}


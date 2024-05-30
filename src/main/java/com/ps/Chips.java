package com.ps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Chips extends Product {

    private String type;
    private Scanner scanner;

    public Chips(String name, double price) {
        super(name, price);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void productSelection() {
        Scanner scanner = new Scanner(System.in);

        String[] types = {"regular", "barbecue", "sour cream & onion", "salt & vinegar"};
        System.out.println("Select chip type:");
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i]);
        }
        type = types[getUserInput() - 1];
        price = 1.50;
    }

    private int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Chips - %s, Price: $%.2f", type, price);

    }


}

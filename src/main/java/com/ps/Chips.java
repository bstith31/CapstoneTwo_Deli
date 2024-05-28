package com.ps;

import java.util.Scanner;

public class Chips extends Product {

    private String type;

    public Chips(String name, double price) {
        super(name, price);
    }

    @Override
    public void productSelection() {
        Scanner scanner = new Scanner(System.in);

        String[] types = {"regular", "barbecue", "sour cream & onion", "salt & vinegar"};
        System.out.println("Select chip type:");
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i]);
        }
        type = types[scanner.nextInt() - 1];
        price = 1.50;
    }

    @Override
    public String toString() {
        return String.format("Chips - %s, Price: $%.2f", type, price);

    }


}

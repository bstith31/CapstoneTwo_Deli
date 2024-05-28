package com.ps;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private ArrayList<Product> products = new ArrayList<>();
    private double totalPrice = 0.0;
    private Scanner scanner = new Scanner(System.in);
    private ReceiptManager receiptManager = new ReceiptManager();

    public void startMenu() {
        boolean exitApplication = false;

        while (!exitApplication) {
            System.out.println("""
                    1. New Order
                    2. Exit
                    Please choose an option
                    """);

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("New Order");
                    startOrder();
                    break;
                case 2:
                    System.out.println("Exiting");
                    exitApplication = true;
                    break;
                default:
                    System.out.println("Invalid Choice, please choose again");
                    break;
            }
        }
    }

    private void startOrder() {
        boolean cancelOrder = false;

        while (!cancelOrder) {
            System.out.println("""
                    1. Add a Sandwich
                    2. Add a Drink
                    3. Add Chips
                    4. Checkout
                    5. Cancel Order
                    Please choose an option:
                    """);

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    addProduct(new Sandwich("Custom Sandwich", 0.0));
                    break;
                case 2:
                    addProduct(new Drink("Custom Drink", 0.0));
                    break;
                case 3:
                    addProduct(new Chips("Custom Chips", 0.0));
                    break;
                case 4:
                    checkout();
                    return;
                case 5:
                    System.out.println("Order cancelled.");
                    cancelOrder = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private void addProduct(Product product) {
        product.productSelection();
        products.add(product);
        totalPrice += product.getPrice();
        System.out.println("Added: " + product);
        System.out.printf("Current total: $%.2f%n", totalPrice);
    }

    private void checkout() {
        System.out.println("Order Summary:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.printf("Total Price: $%.2f%n", totalPrice);

        receiptManager.writeReceipt(products, totalPrice);

    }
}
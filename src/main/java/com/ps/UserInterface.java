package com.ps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private ArrayList<Product> products = new ArrayList<>();
    private double totalPrice = 0.0;
    private Scanner scanner = new Scanner(System.in);
    private ReceiptManager receiptManager = new ReceiptManager();
    private ArrayList<Order> scheduledOrders = new ArrayList<>();
    private LocalDateTime scheduledDateTime = null;
    private boolean adminMode = false; // Flag to track admin mode

    public void startMenu() {

        boolean exitApplication = false;

        while (!exitApplication) {
            System.out.println("""
                    1. New Order
                    2. Admin Mode
                    3. Exit
                    Please choose an option
                    """);

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("New Order");
                    scheduleOrderAtStart();
                    startOrder();
                    break;
                case 2:
                    enterAdminMode();
                    break;
                case 3:
                    System.out.println("Exiting");
                    exitApplication = true;
                    break;
                default:
                    System.out.println("Invalid Choice, please choose again");
                    break;
            }
        }
    }

    private void scheduleOrderAtStart() {
        System.out.println("Would you like to schedule this order? (1 for yes, 2 for no): ");
        int scheduleChoice = scanner.nextInt();
        if (scheduleChoice == 1) {
            scheduleOrder();
        }
    }

    private void startOrder() {

        boolean cancelOrder = false;

        while (!cancelOrder) {
            if (adminMode) {
                System.out.println("""
                        1. Print All Receipts
                        2. Exit Admin Mode
                        Please choose an option:
                        """);

                int adminChoice = scanner.nextInt();

                switch (adminChoice) {
                    case 1:
                        receiptManager.printAllReceipts(scheduledOrders);
                        break;
                    case 2:
                        exitAdminMode();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            } else {
                System.out.println("""
                        1. Add a Custom Sandwich
                        2. Add a Signature Sandwich
                        3. Add a Drink
                        4. Add Chips
                        5. Checkout
                        6. Cancel Order
                        Please choose an option:
                        """);

                int userChoice = scanner.nextInt();

                if (!adminMode && (userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4)) {
                    System.out.println("Invalid Choice, please choose again");
                    continue;
                }

                switch (userChoice) {
                    case 1:
                        addProduct(new Sandwich("Custom Sandwich", 0.0));
                        break;
                    case 2:
                        selectSignatureSandwich();
                        break;
                    case 3:
                        addProduct(new Drink("Custom Drink", 0.0));
                        break;
                    case 4:
                        addProduct(new Chips("Custom Chips", 0.0));
                        break;
                    case 5:
                        checkout();
                        return;
                    case 6:
                        System.out.println("Order cancelled.");
                        cancelOrder = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
        }
    }

    private void selectSignatureSandwich() {
        System.out.println("""
                Select a Signature Sandwich:
                1. BLT
                2. Philly Cheese Steak
                Please choose an option:
                """);

        int sandwichChoice = scanner.nextInt();
        switch (sandwichChoice) {
            case 1:
                addProduct(CustomSandwich.createBLT());
                break;
            case 2:
                addProduct(CustomSandwich.createPhillyCheeseSteak());
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
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

        if (scheduledDateTime != null) {
            receiptManager.writeReceipt(products, totalPrice, scheduledDateTime);
        } else {
            receiptManager.writeReceipt(products, totalPrice);
        }

        resetOrder();
    }

    private void scheduleOrder() {
        System.out.println("Enter the date and time for the order (YYYY-MM-DD HH:mm): ");
        scanner.nextLine(); // consume newline
        String dateTimeString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

        try {
            scheduledDateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date and time format. Please use YYYY-MM-DD HH:mm format.");
            return;
        }

        Order scheduledOrder = new Order(products, totalPrice, scheduledDateTime);
        scheduledOrders.add(scheduledOrder);

        System.out.println("Order scheduled successfully for " + scheduledDateTime.format(formatter) + ".");
    }

    private void resetOrder() {
        products.clear();
        totalPrice = 0.0;
        scheduledDateTime = null;
    }

    private void processOrder() {
        System.out.println("Order Summary:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.printf("Total Price: $%.2f%n", totalPrice);

        if (scheduledDateTime != null) {
            receiptManager.writeReceipt(products, totalPrice, scheduledDateTime);
        } else {
            receiptManager.writeReceipt(products, totalPrice);
        }
    }

    private void enterAdminMode() {
        System.out.println("Enter Admin Mode Password: "); // Example: "adminpassword"
        String password = scanner.next();
        if (password.equals("adminpassword")) {
            adminMode = true;
            System.out.println("Admin Mode Entered.");
            adminMenu();
        } else {
            System.out.println("Incorrect Password.");
        }
    }

    private void adminMenu() {
        boolean exitAdminMenu = false;
        while (!exitAdminMenu) {
            System.out.println("""
                    Admin Mode Menu:
                    1. Print All Receipts
                    2. Exit Admin Mode
                    Please choose an option:
                    """);
            int adminChoice = scanner.nextInt();
            switch (adminChoice) {
                case 1:
                    if (scheduledOrders.isEmpty()) {
                        System.out.println("No receipts currently.");
                    } else {
                        receiptManager.printAllReceipts(scheduledOrders);
                    }
                    break;
                case 2:
                    exitAdminMode();
                    exitAdminMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private void exitAdminMode() {
        adminMode = false;
        System.out.println("Exited Admin Mode.");
    }
}
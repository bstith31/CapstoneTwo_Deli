package com.ps;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {

    private static final String RECEIPTS = "receipts/";

    /*
    The first of two writeReceipt methods that are used to write the receipt to the file.
    This version of the method accepts the parameters of product from the List<Product> as well as the totalPrice.
    When accepting these parameters, it uses LocalDateTime to save the receipt with given info to the current time.
     */
    public void writeReceipt(List<Product> products, double totalPrice) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        writeReceipt(products, totalPrice, currentDateTime);
    }

    /*
    The second of the writeReceipt methods, in this version the accepted parameters vary.
    This method accepts a product and totalPrice like the first method, however, it also accepts a scheduledDateTime.
    This overloaded method triggers when a user decides to schedule their order when prompted before actually ordering.
     */
    public void writeReceipt(List<Product> products, double totalPrice, LocalDateTime scheduledDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = scheduledDateTime.format(formatter);
        String filename = RECEIPTS + timestamp + ".txt";

        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter(filename))) {
            if (scheduledDateTime.equals(LocalDateTime.now())) {
                bufwriter.write("Order Receipt\n");
                bufwriter.write("==============\n");
            } else {
                bufwriter.write("Scheduled Order Receipt\n");
                bufwriter.write("========================\n");
                bufwriter.write("Scheduled Date and Time: " + scheduledDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n\n");
            }

            for (Product product : products) {
                bufwriter.write(product.toString() + "\n");
            }

            bufwriter.write("--------------\n");
            bufwriter.write(String.format("Total Price: $%.2f\n", totalPrice));
            if (scheduledDateTime.equals(LocalDateTime.now())) {
                System.out.println("Receipt written to " + filename);
            } else {
                System.out.println("Receipt for scheduled order written to " + filename);
            }
            bufwriter.flush();
        } catch (IOException e) {
            System.err.println("Error writing receipt: " + e.getMessage());
        }
    }

    /*
    Method for admin access, allowing admins to print all receipts within the file for viewing. This is useful to see
    all transactions and would help in keeping track of transactions within given periods of times such as the feature
    included in capstone 1.
     */
    public void printAllReceipts() {
        Path directory = Paths.get(RECEIPTS);
        try {
            Files.walk(directory)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> {
                        try (BufferedReader bufreader = new BufferedReader(new FileReader(path.toFile()))) {
                            String line;
                            while ((line = bufreader.readLine()) != null) {
                                System.out.println(line);
                            }
                            System.out.println();
                        } catch (IOException e) {
                            System.err.println("Error reading receipt: " + path.getFileName() + " - " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error walking directory: " + e.getMessage());
        }
    }
}


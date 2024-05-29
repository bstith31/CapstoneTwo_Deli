package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {

    private static final String RECEIPTS = "receipts/";

    public void writeReceipt(List<Product> products, double totalPrice) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        writeReceipt(products, totalPrice, currentDateTime);
    }

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

    public void printAllReceipts(List<Order> orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {

    public void writeReceipt(List<Product> products, double totalPrice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String filename = timestamp + ".txt";

        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter(filename))) {
            bufwriter.write("Order Receipt\n");
            bufwriter.write("==============\n");

            for (Product product : products) {
                bufwriter.write(product.toString() + "\n");
            }

            bufwriter.write("--------------\n");
            bufwriter.write(String.format("Total Price: $%.2f\n", totalPrice));
            System.out.println("Receipt written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing receipt: " + e.getMessage());
        }
    }
}
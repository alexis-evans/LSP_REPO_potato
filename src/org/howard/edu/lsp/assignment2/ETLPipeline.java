/** 
 * Name: Alexis Evans
 * 
 * ETL Pipeline for Product Data
   This program reads product data from a CSV file, transforms it according to specified rules,
   and writes the transformed data to a new CSV file.
   
   Transformation Rules:
   1. Convert product names to UPPERCASE.
   2. Apply a 10% discount to products in the "Electronics" category.
   3. If the price of an "Electronics" product exceeds $500 after discount, change its category to "Premium Electronics".
   4. Add a new column "PriceRange" based on the final price:
      - "Low" for prices <= $10.00
      - "Medium" for prices > $10.00 and <= $100.00
      - "High" for prices > $100.00 and <= $500.00
      - "Premium" for prices > $500.00

   Input File: data/products.csv
   Output File: data/transformed_products.csv
*/

package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.Scanner;

public class ETLPipeline {
    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";
        int skippedRows = 0;
        int processedRows = 0;

        try (Scanner scanner = new Scanner(new File(inputPath));
             PrintWriter writer = new PrintWriter(new File(outputPath))) {

            // Handle Header
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                writer.println(header + ",PriceRange"); // Adding the new column
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip empty rows
                if (line.trim().isEmpty()) {
                    skippedRows++;
                    continue;
                }
                
                String[] columns = line.split(",");
                
                // Skip rows that don't have exactly 4 fields
                if (columns.length != 4) {
                    skippedRows++;
                    continue;
                }
                
                try {
                    // Extract
                    int id = Integer.parseInt(columns[0].trim());
                    String name = columns[1].trim();
                    double price = Double.parseDouble(columns[2].trim());
                    String category = columns[3].trim();
                    
                    // Skip if any field is empty
                    if (name.isEmpty() || category.isEmpty()) {
                        skippedRows++;
                        continue;
                    }

                    // Transform
                    // 1. Convert name to UPPERCASE
                    name = name.toUpperCase();

                    // 2. Apply 10% discount for Electronics
                    if (category.equalsIgnoreCase("Electronics")) {
                        price *= 0.9;
                    }

                    // 3. Check for Premium Electronics
                    if (price > 500.00 && category.equalsIgnoreCase("Electronics")) {
                        category = "Premium Electronics";
                    }

                    // 4. Determine PriceRange
                    String priceRange;
                    if (price <= 10.00) priceRange = "Low";
                    else if (price <= 100.00) priceRange = "Medium";
                    else if (price <= 500.00) priceRange = "High";
                    else priceRange = "Premium";

                    // Load
                    writer.printf("%d,%s,%.2f,%s,%s%n", id, name, price, category, priceRange);
                    processedRows++;
                    
                } catch (NumberFormatException e) {
                    // Skip rows with unparseable ID or price
                    skippedRows++;
                }
            }

            System.out.println("ETL Process Complete!");
            System.out.println("Processed rows: " + processedRows);
            System.out.println("Skipped rows: " + skippedRows);

        } catch (FileNotFoundException e) {
            System.err.println("Error: Ensure the 'data' folder and 'products.csv' exist.");
        }
    }
}
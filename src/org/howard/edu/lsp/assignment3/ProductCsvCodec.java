package org.howard.edu.lsp.assignment3;

import java.io.PrintWriter;
import java.util.Optional;

public class ProductCsvCodec {
    public Optional<Product> parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return Optional.empty();
        }

        String[] columns = line.split(",");
        if (columns.length != 4) {
            return Optional.empty();
        }

        try {
            int id = Integer.parseInt(columns[0].trim());
            String name = columns[1].trim();
            double price = Double.parseDouble(columns[2].trim());
            String category = columns[3].trim();

            if (name.isEmpty() || category.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(new Product(id, name, price, category, null));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public void writeHeader(PrintWriter writer) {
        writer.println("ProductID,Name,Price,Category,PriceRange");
    }

    public void writeProduct(PrintWriter writer, Product product) {
        writer.printf(
                "%d,%s,%.2f,%s,%s%n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getPriceRange().getLabel());
    }
}

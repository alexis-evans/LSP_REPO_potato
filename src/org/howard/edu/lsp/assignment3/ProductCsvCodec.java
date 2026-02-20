package org.howard.edu.lsp.assignment3;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * Codec for serializing and deserializing Product objects to and from CSV format.
 * Handles both parsing CSV lines into Product objects and writing Product objects as CSV lines.
 * The CSV format uses comma delimiters with fields: ProductID, Name, Price, Category, PriceRange.
 */
public class ProductCsvCodec {
    /**
     * Parses a CSV line into a Product object.
     * The expected format is: ProductID,Name,Price,Category (4 comma-separated fields).
     * Whitespace around fields is trimmed before processing.
     *
     * A line is considered invalid and returns empty Optional if:
     *   - The line is null or empty
     *   - The line does not contain exactly 4 comma-separated fields
     *   - ProductID cannot be parsed as an integer
     *   - Price cannot be parsed as a double
     *   - Name or Category is empty after trimming
     *
     * @param line the CSV line to parse
     * @return an Optional containing the parsed Product, or empty if parsing fails
     */
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

    /**
     * Writes the CSV header row to the output.
     * The header is always: ProductID,Name,Price,Category,PriceRange
     *
     * @param writer the PrintWriter to write the header to
     */
    public void writeHeader(PrintWriter writer) {
        writer.println("ProductID,Name,Price,Category,PriceRange");
    }

    /**
     * Writes a Product object as a CSV line to the output.
     * The format is: ProductID,Name,Price,Category,PriceRange
     * The price is formatted with exactly 2 decimal places.
     *
     * @param writer the PrintWriter to write the product to
     * @param product the Product to write as CSV
     */
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

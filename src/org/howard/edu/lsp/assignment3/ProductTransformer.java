package org.howard.edu.lsp.assignment3;

public class ProductTransformer {
    public Product transform(Product product) {
        Product transformed = product.withName(product.getName().toUpperCase());

        if (transformed.getCategory().equalsIgnoreCase("Electronics")) {
            transformed = transformed.withPrice(transformed.getPrice() * 0.9);
        }

        double roundedPrice = Math.round(transformed.getPrice() * 100.0) / 100.0;
        transformed = transformed.withPrice(roundedPrice);

        if (transformed.getPrice() > 500.00 && transformed.getCategory().equalsIgnoreCase("Electronics")) {
            transformed = transformed.withCategory("Premium Electronics");
        }

        return transformed.withPriceRange(PriceRange.fromPrice(transformed.getPrice()));
    }
}

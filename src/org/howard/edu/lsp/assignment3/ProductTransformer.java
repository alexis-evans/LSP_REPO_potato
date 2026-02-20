package org.howard.edu.lsp.assignment3;

/**
 * Transformer that applies business rules to Product objects.
 * Applies the following transformations in order:
 *  - Converts product name to UPPERCASE
 *  - Applies a 10% discount to products in the "Electronics" category
 *  - Rounds the price to 2 decimal places using round-half-up
 *  - Reclassifies products as "Premium Electronics" if price > $500.00 and category is "Electronics"
 *  - Calculates and assigns the appropriate price range based on the final rounded price
 */
public class ProductTransformer {
    /**
     * Transforms a product by applying all business rules.
     * The product is transformed immutably, returning a new Product instance
     * with all transformations applied.
     *
     * @param product the product to transform
     * @return a new Product with all transformations applied
     */
    public Product transform(Product product) {
        // Rule 1: normalize name casing.
        Product transformed = product.withName(product.getName().toUpperCase());

        // Rule 2: apply 10% discount for Electronics (case-insensitive match).
        if (transformed.getCategory().equalsIgnoreCase("Electronics")) {
            transformed = transformed.withPrice(transformed.getPrice() * 0.9);
        }

        // Round once and reuse that rounded value for all downstream checks.
        double roundedPrice = Math.round(transformed.getPrice() * 100.0) / 100.0;
        transformed = transformed.withPrice(roundedPrice);

        // Rule 3: classify premium electronics using final rounded price.
        if (transformed.getPrice() > 500.00 && transformed.getCategory().equalsIgnoreCase("Electronics")) {
            transformed = transformed.withCategory("Premium Electronics");
        }

        // Rule 4: derive PriceRange from the same final rounded price.
        return transformed.withPriceRange(PriceRange.fromPrice(transformed.getPrice()));
    }
}

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

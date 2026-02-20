package org.howard.edu.lsp.assignment3;

/**
 * Immutable domain model representing a product with its associated attributes.
 * This class follows the immutable object pattern, providing "with" methods
 * to create modified copies rather than mutating the original object.
 */
public class Product {
    // Immutable state; fields are set once in the constructor.
    private final int id;
    private final String name;
    private final double price;
    private final String category;
    private final PriceRange priceRange;

    /**
     * Constructs a Product with the specified attributes.
     *
     * @param id the unique product identifier
     * @param name the product name
     * @param price the product price
     * @param category the product category (e.g., "Electronics", "Furniture")
     * @param priceRange the price range classification, or null if not yet determined
     */
    public Product(int id, String name, double price, String category, PriceRange priceRange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    /**
     * Gets the product ID.
     *
     * @return the product identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the product category.
     *
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the product's price range classification.
     *
     * @return the price range, or null if not yet determined
     */
    public PriceRange getPriceRange() {
        return priceRange;
    }

    /**
     * Creates a new Product with the specified name, keeping all other attributes the same.
     * This method follows the immutable object pattern.
     *
     * @param updatedName the new name for the product
     * @return a new Product instance with the updated name
     */
    public Product withName(String updatedName) {
        // Return a copy to preserve immutability.
        return new Product(id, updatedName, price, category, priceRange);
    }

    /**
     * Creates a new Product with the specified price, keeping all other attributes the same.
     * This method follows the immutable object pattern.
     *
     * @param updatedPrice the new price for the product
     * @return a new Product instance with the updated price
     */
    public Product withPrice(double updatedPrice) {
        // Return a copy to preserve immutability.
        return new Product(id, name, updatedPrice, category, priceRange);
    }

    /**
     * Creates a new Product with the specified category, keeping all other attributes the same.
     * This method follows the immutable object pattern.
     *
     * @param updatedCategory the new category for the product
     * @return a new Product instance with the updated category
     */
    public Product withCategory(String updatedCategory) {
        // Return a copy to preserve immutability.
        return new Product(id, name, price, updatedCategory, priceRange);
    }

    /**
     * Creates a new Product with the specified price range, keeping all other attributes the same.
     * This method follows the immutable object pattern.
     *
     * @param updatedPriceRange the new price range for the product
     * @return a new Product instance with the updated price range
     */
    public Product withPriceRange(PriceRange updatedPriceRange) {
        // Return a copy to preserve immutability.
        return new Product(id, name, price, category, updatedPriceRange);
    }
}

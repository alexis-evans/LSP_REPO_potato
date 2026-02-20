package org.howard.edu.lsp.assignment3;

/**
 * Enumeration representing price range classifications for products.
 * Products are categorized into four ranges based on their final price:
 *   - LOW: price <= $10.00
 *   - MEDIUM: $10.00 < price <= $100.00
 *   - HIGH: $100.00 < price <= $500.00
 *   - PREMIUM: price > $500.00
 */
public enum PriceRange {
    /** Low price range: price <= $10.00 */
    LOW("Low"),

    /** Medium price range: $10.00 < price <= $100.00 */
    MEDIUM("Medium"),

    /** High price range: $100.00 < price <= $500.00 */
    HIGH("High"),

    /** Premium price range: price > $500.00 */
    PREMIUM("Premium");

    private final String label;

    /**
     * Constructs a PriceRange with the specified label.
     *
     * @param label the human-readable label for this price range
     */
    PriceRange(String label) {
        this.label = label;
    }

    /**
     * Gets the human-readable label for this price range.
     *
     * @return the label (e.g., "Low", "Medium", "High", "Premium")
     */
    public String getLabel() {
        return label;
    }

    /**
     * Determines the appropriate price range for a given price value.
     * Uses the following thresholds:
     *   - price <= $10.00 returns LOW
     *   - $10.00 < price <= $100.00 returns MEDIUM
     *   - $100.00 < price <= $500.00 returns HIGH
     *   - price > $500.00 returns PREMIUM
     *
     * @param price the price to classify
     * @return the appropriate PriceRange for the given price
     */
    public static PriceRange fromPrice(double price) {
        if (price <= 10.00) {
            return LOW;
        }
        if (price <= 100.00) {
            return MEDIUM;
        }
        if (price <= 500.00) {
            return HIGH;
        }
        return PREMIUM;
    }
}

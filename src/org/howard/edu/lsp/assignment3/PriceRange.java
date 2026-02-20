package org.howard.edu.lsp.assignment3;

public enum PriceRange {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    PREMIUM("Premium");

    private final String label;

    PriceRange(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

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

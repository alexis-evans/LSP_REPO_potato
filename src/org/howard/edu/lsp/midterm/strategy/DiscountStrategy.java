package org.howard.edu.lsp.midterm.strategy;

/**
 * Represents a pricing strategy for calculating a final price.
 *
 * @author Alexis Evans
 */
public interface DiscountStrategy {
    /**
     * Calculates the final price after applying this discount strategy.
     *
     * @param price the original purchase price
     * @return the final price after discount
     */
    double calculatePrice(double price);
}

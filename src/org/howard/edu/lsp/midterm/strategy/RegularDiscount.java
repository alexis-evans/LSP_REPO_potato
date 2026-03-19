package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for a regular customer.
 *
 * @author Alexis Evans
 */
public class RegularDiscount implements DiscountStrategy {
    /**
     * Returns the original price with no discount applied.
     *
     * @param price the original purchase price
     * @return the unchanged price
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}

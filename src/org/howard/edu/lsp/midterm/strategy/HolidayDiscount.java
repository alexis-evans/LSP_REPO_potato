package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for a holiday customer discount.
 *
 * @author Alexis Evans
 */
public class HolidayDiscount implements DiscountStrategy {
    /**
     * Applies a 15% discount to the original price.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
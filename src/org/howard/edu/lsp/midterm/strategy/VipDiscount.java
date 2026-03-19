package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for a VIP customer.
 *
 * @author Alexis Evans
 */
public class VipDiscount implements DiscountStrategy {
    /**
     * Applies a 20% discount to the original price.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
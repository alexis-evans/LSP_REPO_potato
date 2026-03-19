package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for a member customer.
 *
 * @author Alexis Evans
 */
public class MemberDiscount implements DiscountStrategy {
    /**
     * Applies a 10% discount to the original price.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates final prices using a selected discount strategy.
 *
 * @author Alexis Evans
 */
public class PriceCalculator {
    private DiscountStrategy discountStrategy;

    /**
     * Constructs a PriceCalculator with the given strategy.
     *
     * @param discountStrategy the pricing strategy to use
     */
    public PriceCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Changes the discount strategy used by this calculator.
     *
     * @param discountStrategy the new pricing strategy
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original purchase price
     * @return the final price after applying the strategy
     */
    public double calculatePrice(double price) {
        return discountStrategy.calculatePrice(price);
    }
}

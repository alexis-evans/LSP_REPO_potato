package org.howard.edu.lsp.midterm.strategy;

/**
 * Demonstrates the Strategy Pattern implementation for price calculation.
 *
 * @author Alexis Evans
 */
public class Driver {
    /**
     * Runs the pricing demonstration for different customer types.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        double purchasePrice = 100.0;

        PriceCalculator calculator = new PriceCalculator(new RegularDiscount());
        System.out.println("REGULAR: " + calculator.calculatePrice(purchasePrice));

        calculator.setDiscountStrategy(new MemberDiscount());
        System.out.println("MEMBER: " + calculator.calculatePrice(purchasePrice));

        calculator.setDiscountStrategy(new VipDiscount());
        System.out.println("VIP: " + calculator.calculatePrice(purchasePrice));

        calculator.setDiscountStrategy(new HolidayDiscount());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(purchasePrice));
    }
}

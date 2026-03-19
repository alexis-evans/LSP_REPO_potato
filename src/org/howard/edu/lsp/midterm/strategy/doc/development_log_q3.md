## AI Tool Used: ChatGPT

### Link to full chat for all three questions: https://chatgpt.com/share/69bc87c0-33c4-8003-b2fb-b4903d131245

# How I used it
I used ChatGPT to help me refactor the code of the original PriceCalculator into different independent classes. It also helped me write out the evaluation of the original class. 

# Transcript
Q: complete question 3

A: Thought for 36s

Here is a complete Question 3 solution, including the required markdown files, all Java classes, and the driver output. This design follows the OO ideas from your slides: organize code into classes, keep responsibilities modular, use abstraction and encapsulation, and rely on polymorphism so the same operation can be carried out differently by different classes.

design_evaluation.md

Path: org/howard/edu/lsp/midterm/strategy/doc/design_evaluation.md

# Design Evaluation of `PriceCalculator`

The current `PriceCalculator` design works for the existing customer types, but it is not very maintainable or extensible. The class uses a sequence of hard-coded `if` statements to decide how to calculate the final price for each customer type. This means every time a new pricing rule is introduced, the class must be edited directly. That makes the design harder to extend over time.

The design also concentrates multiple pricing behaviors in a single class instead of distributing them across separate objects. In object-oriented design, this reduces cohesion because one class is responsible for several different discount policies. A better design would encapsulate each pricing rule in its own class.

The implementation also relies on string comparisons such as `"REGULAR"` and `"VIP"`. This increases the chance of errors caused by misspellings or inconsistent input. It also makes the code less robust and less expressive than a design where each pricing behavior is represented as its own strategy object.

This class does not take advantage of polymorphism. The slides explain that polymorphism allows the same abstract operation to be performed differently in different classes. Here, that would mean defining one common discount-calculation operation and letting different strategy classes implement it in different ways. That approach would remove the need for a long chain of conditionals.

Overall, the current design is difficult to grow because adding or changing discount rules requires modifying existing code instead of adding new classes. A Strategy Pattern redesign would improve modularity, make the system easier to test, and better support future changes.
Java files
DiscountStrategy.java

Path: org/howard/edu/lsp/midterm/strategy/DiscountStrategy.java

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
RegularDiscount.java

Path: org/howard/edu/lsp/midterm/strategy/RegularDiscount.java

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
MemberDiscount.java

Path: org/howard/edu/lsp/midterm/strategy/MemberDiscount.java

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
VipDiscount.java

Path: org/howard/edu/lsp/midterm/strategy/VipDiscount.java

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
HolidayDiscount.java

Path: org/howard/edu/lsp/midterm/strategy/HolidayDiscount.java

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
PriceCalculator.java

Path: org/howard/edu/lsp/midterm/strategy/PriceCalculator.java

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
Driver.java

Path: org/howard/edu/lsp/midterm/strategy/Driver.java

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
Expected driver output
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
development_log_q3.md

Path: org/howard/edu/lsp/midterm/strategy/doc/development_log_q3.md

# Development Log for Question 3

## External Resources Used

### 1. ChatGPT
Used to evaluate the original `PriceCalculator` design and refactor it using the Strategy Pattern.

What I asked:
- I asked for a full completion of Question 3 of the midterm.
- I asked for help evaluating why the original `PriceCalculator` design was weak.
- I asked for a Strategy Pattern redesign with all required Java files and the driver output.

How it helped:
- Helped identify the main object-oriented issues in the original class, including hard-coded conditional logic, poor extensibility, and lack of polymorphism.
- Helped organize the design into an interface plus one class per discount strategy.
- Helped draft the required Java code, Javadoc comments, and markdown explanation.

### 2. Uploaded course slides
Used course material on object orientation to support the design choices.

How they helped:
- Reinforced that object-oriented systems should organize behavior into collaborating classes.
- Supported the use of abstraction and polymorphism to allow the same operation to be implemented differently in different classes.
- Helped justify replacing conditional logic with a strategy-based design.
Why this is the right pattern

This is a clean Strategy Pattern design because:

DiscountStrategy defines the shared operation.

Each discount type is its own class.

PriceCalculator delegates pricing behavior to the strategy object.

You can add a new discount later without rewriting the calculator logic.

That matches the OO ideas from your slides about abstraction, modularity, encapsulation, and polymorphism.

If you want, I can also turn Questions 1–3 into one clean submission checklist with every required file path.
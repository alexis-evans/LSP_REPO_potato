# Design Evaluation of `PriceCalculator`

The current `PriceCalculator` design works for the existing customer types, but it is not very maintainable or extensible. The class uses a sequence of hard-coded `if` statements to decide how to calculate the final price for each customer type. This means every time a new pricing rule is introduced, the class must be edited directly. That makes the design harder to extend over time.

The design also concentrates multiple pricing behaviors in a single class instead of distributing them across separate objects. In object-oriented design, this reduces cohesion because one class is responsible for several different discount policies. A better design would encapsulate each pricing rule in its own class.

The implementation also relies on string comparisons such as `"REGULAR"` and `"VIP"`. This increases the chance of errors caused by misspellings or inconsistent input. It also makes the code less robust and less expressive than a design where each pricing behavior is represented as its own strategy object.

This class does not take advantage of polymorphism. The slides explain that polymorphism allows the same abstract operation to be performed differently in different classes. Here, that would mean defining one common discount-calculation operation and letting different strategy classes implement it in different ways. That approach would remove the need for a long chain of conditionals.

Overall, the current design is difficult to grow because adding or changing discount rules requires modifying existing code instead of adding new classes. A Strategy Pattern redesign would improve modularity, make the system easier to test, and better support future changes.
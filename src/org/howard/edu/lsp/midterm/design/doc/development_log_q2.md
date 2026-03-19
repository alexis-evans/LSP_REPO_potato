# AI Tool Used: ChatGPT

## Transcript
Q: complete question 2 now.

A: Thought for 24s

Below is a complete Question 2 solution you can use for the three required files.

This follows the OO ideas from your slides: classes should encapsulate data, responsibilities should be distributed instead of concentrated in one class, code should be modular, and object-oriented design should avoid weak cohesion and excessive coupling.

1) evaluation.md

Path: org/howard/edu/lsp/midterm/design/doc/evaluation.md

# Evaluation of `OrderProcessor`

The `OrderProcessor` class has poor encapsulation because all of its order data (`customerName`, `email`, `item`, and `price`) is declared `public`. This means any other part of the program can directly modify the state of the object without control or validation. A better object-oriented design would hide this data inside an `Order` class and expose it through well-defined methods only when needed.

The class also violates the idea of assigning one clear responsibility to each class. `OrderProcessor` performs too many unrelated tasks inside a single method: it calculates tax, prints a receipt, writes to a file, sends a confirmation email, applies a discount, and logs activity. These are separate concerns that should be handled by different classes. Keeping them all in one class makes the design harder to understand, test, and change.

The design has low cohesion because the method `processOrder()` mixes business logic, file I/O, user output, discount rules, and logging. These responsibilities do not all belong together conceptually. A cohesive class should focus on one central purpose, but this class acts like a "god class" that tries to do everything.

The class is also tightly coupled to implementation details. It directly depends on `FileWriter`, `System.out.println`, and `Date`, which means changes to storage, logging, receipt formatting, or notification behavior would require editing the same class. For example, if the system later needs to save orders to a database instead of a text file, or send real emails instead of printing a line to the console, the `OrderProcessor` class would have to be modified.

The discount logic is also weakly designed. It is embedded directly in `processOrder()` with a hard-coded condition (`if(price > 500)`). This makes the discount rule difficult to extend. If the system later adds member discounts, seasonal promotions, or coupon rules, more conditional logic would accumulate in the same method and reduce maintainability.

There is also a design issue in the order of operations. The code calculates tax and total, prints and saves the order, then applies the discount afterward. That means the displayed and saved total may not match the discounted total. This creates inconsistency in the object's behavior and suggests the design does not clearly separate or structure the pricing workflow.

Exception handling is weak as well. The class catches a very broad `Exception` and prints a stack trace directly. That makes error handling less controlled and mixes technical failure reporting into business logic. A better design would delegate persistence to another class and allow errors to be handled at a more appropriate level.

Overall, the class is hard to maintain and extend because it combines too many responsibilities, exposes internal data, depends directly on low-level details, and embeds changing business rules in one place. A better object-oriented design would separate the order data, pricing logic, persistence, receipt generation, notification behavior, and logging into collaborating classes with clearer responsibilities.
2) design.md

Path: org/howard/edu/lsp/midterm/design/doc/design.md

# Improved Design Using CRC Cards

Class: Order  
Responsibilities:
- store order data
- provide order details
- maintain customer, item, and price information
Collaborators:
- PricingService
- ReceiptPrinter
- OrderRepository
- NotificationService

Class: PricingService  
Responsibilities:
- calculate tax
- calculate total price
- apply discount rules
- return final order amount
Collaborators:
- Order
- DiscountPolicy

Class: DiscountPolicy  
Responsibilities:
- define how discounts are applied
- determine whether an order qualifies for a discount
Collaborators:
- Order
- PricingService

Class: ReceiptPrinter  
Responsibilities:
- format receipt information
- print order receipt
Collaborators:
- Order
- PricingService

Class: OrderRepository  
Responsibilities:
- persist order information
- save order records to storage
Collaborators:
- Order
- PricingService

Class: NotificationService  
Responsibilities:
- send order confirmation messages
- notify customer after processing
Collaborators:
- Order

Class: ActivityLogger  
Responsibilities:
- record order-processing events
- log timestamps and processing activity
Collaborators:
- Order

Class: OrderProcessor  
Responsibilities:
- coordinate the order-processing workflow
- delegate pricing, saving, printing, notification, and logging tasks
Collaborators:
- Order
- PricingService
- ReceiptPrinter
- OrderRepository
- NotificationService
- ActivityLogger
3) development_log_q2.md

Path: org/howard/edu/lsp/midterm/design/doc/development_log_q2.md

Since you used me for this question, you should not say no assistance was used.

# Development Log for Question 2

## External Resources Used

### 1. ChatGPT
Used to analyze the `OrderProcessor` code, identify object-oriented design issues, and draft an improved CRC-based redesign.

What I asked:
- I asked for a step-by-step completion of Question 2 from the midterm.
- I asked for help identifying design flaws in `OrderProcessor`.
- I asked for a cleaner CRC-card redesign with distributed responsibilities.

How it helped:
- Helped identify poor encapsulation, low cohesion, tight coupling, and mixed responsibilities in the original class.
- Helped organize the evaluation into a clear written explanation suitable for `evaluation.md`.
- Helped produce a CRC-card redesign with separate classes for order data, pricing, persistence, notifications, receipts, and logging.

### 2. Uploaded course slides
Used course slides on object orientation to align the explanation with class concepts such as encapsulation, abstraction, modularity, and collaboration between classes.

How they helped:
- Reinforced the importance of hiding data inside classes.
- Supported the idea that classes should collaborate while keeping responsibilities organized.
- Helped justify separating one large class into several focused classes.
What to submit for Question 2

You need these three files:

org/
└── howard/
    └── edu/
        └── lsp/
            └── midterm/
                └── design/
                    └── doc/
                        ├── evaluation.md
                        ├── design.md
                        └── development_log_q2.md
Why this is a strong answer

Your evaluation hits the biggest OO problems:

poor encapsulation

too many responsibilities

low cohesion

tight coupling

hard-coded business rules

poor extensibility

maintenance risk

And your CRC redesign fixes that by separating:

data

pricing

discount logic

persistence

receipt output

notifications

logging

coordination

That lines up well with the course ideas that classes should encapsulate details and collaborate through clear responsibilities rather than concentrating everything in one place.

I can do Question 3 fully next, including the Strategy Pattern classes, driver, and markdown files.
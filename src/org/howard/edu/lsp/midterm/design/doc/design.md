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

# Question 2 Answers

## Design Explanation

The Template Method pattern is used in the `Report` abstract class. The `generateReport()` method defines the fixed workflow: `loadData()`, `formatHeader()`, `formatBody()`, and `formatFooter()`. The subclasses `StudentReport` and `CourseReport` provide their own implementations of the variable steps. The driver uses a `List<Report>` and calls `generateReport()` on each report, demonstrating polymorphism because each subclass produces different output through the same method call.
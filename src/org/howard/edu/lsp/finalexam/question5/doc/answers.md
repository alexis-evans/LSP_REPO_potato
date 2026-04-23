Heuristic 1:

Name:\
Do not create god classes/objects in your system.

Explanation:
This improves maintainability because one class should not control too much of the system’s data or behavior. In lecture, the slides explained that a “god class” concentrates too much responsibility in one place, often with names like `System`, `Manager`, or `Driver`. The home heating example showed that `HeatFlowRegulator` became a god class when it pulled data from `Room`, made decisions, and controlled the `Furnace`; the improved design moved the heat decision into the `Room` class.


Heuristic 2:

Name:
Do not turn an operation into a class.

Explanation:
This improves readability because classes should represent meaningful abstractions, not just actions. In lecture, this was explained as being suspicious of classes whose names are verbs or are derived from verbs, especially if the class only has one meaningful behavior. A behavior should usually be moved into an existing class that owns the related data, instead of creating a separate controller-style class for one operation.

Heuristic 3:

Name:
Beware classes that have many access methods defined in their public interface. Having many access methods implies that related data and behavior are not being kept in one place.

Explanation:
This improves maintainability because too many getters and setters can mean another class is pulling data out and doing work that should belong inside the original class. In lecture, the slides explained that if class `A` calls several get/set methods in class `B`, you should ask what `A` is doing with that information and why class `B` does not do it itself. This supports encapsulation by keeping related data and behavior together instead of spreading logic across multiple classes.

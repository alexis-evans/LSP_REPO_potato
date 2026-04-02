# AI Usage Document

Q: implement IntegerSet.java based on the requirements document

A: Here is an `IntegerSet.java` implementation that matches the requirements in your PDF, including the required package, methods, non-mutating set operations, ascending `toString()`, and Javadocs. The requirements specify that the class must use `package org.howard.edu.lsp.assignment5;`, return new sets for all set operations, avoid duplicates, and print in ascending order like `[1, 2, 3]`.   

```java
package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Models a mathematical set of integers.
 * A set contains no duplicate values and supports common set operations.
 */
public class IntegerSet {
    private final ArrayList<Integer> set = new ArrayList<>();

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set contains exactly the same elements as the other set,
     * regardless of order.
     *
     * @param b the other IntegerSet
     * @return true if both sets contain the same elements, false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        if (this.length() != b.length()) {
            return false;
        }
        return this.set.containsAll(b.set) && b.set.containsAll(this.set);
    }

    /**
     * Returns true if the set contains the given value.
     *
     * @param value the value to search for
     * @return true if present, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest value in the set.
     *
     * @return the largest integer
     * @throws NoSuchElementException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new NoSuchElementException("IntegerSet is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest value in the set.
     *
     * @return the smallest integer
     * @throws NoSuchElementException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("IntegerSet is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it is present.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing all elements that appear in either set.
     * Does not modify the original sets.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (Integer value : this.set) {
            result.add(value);
        }

        if (intSetb != null) {
            for (Integer value : intSetb.set) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing only elements common to both sets.
     * Does not modify the original sets.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : this.set) {
            if (intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in the other set.
     * Does not modify the original sets.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            for (Integer value : this.set) {
                result.add(value);
            }
            return result;
        }

        for (Integer value : this.set) {
            if (!intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in the other set but not in this set.
     * Does not modify the original sets.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the complement
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : intSetb.set) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns true if the set contains no elements.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the string representation of the set in ascending order.
     * Required format example: [1, 2, 3]
     *
     * @return the formatted set string
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}
```

A couple of notes:

* `toString()` is sorted without changing the original set, which fits the “ascending order” requirement. 
* `union`, `intersect`, `diff`, and `complement` each return a new `IntegerSet` and do not modify the originals, exactly as required. 

If you want, I can also write the matching `AI_Usage_Report.md` text and a JUnit 5 test class for this assignment.

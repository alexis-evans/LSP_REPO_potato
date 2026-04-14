Q: 

write integersettest.java that tests all methods and edge cases of the intergerset class. use junit5

A: 

package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    private IntegerSet makeSet(int... values) {
        IntegerSet set = new IntegerSet();
        for (int value : values) {
            set.add(value);
        }
        return set;
    }

    @Test
    void testClearOnNonEmptySet() {
        IntegerSet set = makeSet(1, 2, 3);

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
        assertEquals("[]", set.toString());
    }

    @Test
    void testClearOnEmptySet() {
        IntegerSet set = new IntegerSet();

        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    void testLengthEmptySet() {
        IntegerSet set = new IntegerSet();

        assertEquals(0, set.length());
    }

    @Test
    void testLengthAfterAddingUniqueElements() {
        IntegerSet set = makeSet(1, 2, 3);

        assertEquals(3, set.length());
    }

    @Test
    void testLengthDoesNotIncreaseWithDuplicates() {
        IntegerSet set = new IntegerSet();

        set.add(5);
        set.add(5);
        set.add(5);

        assertEquals(1, set.length());
    }

    @Test
    void testEqualsSameElementsDifferentOrder() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 2, 1);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void testEqualsDifferentSizes() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(1, 2);

        assertFalse(a.equals(b));
    }

    @Test
    void testEqualsDifferentElementsSameSize() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(1, 2, 4);

        assertFalse(a.equals(b));
    }

    @Test
    void testEqualsWithNull() {
        IntegerSet a = makeSet(1, 2, 3);

        assertFalse(a.equals(null));
    }

    @Test
    void testEqualsTwoEmptySets() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        assertTrue(a.equals(b));
    }

    @Test
    void testContainsExistingValue() {
        IntegerSet set = makeSet(10, 20, 30);

        assertTrue(set.contains(20));
    }

    @Test
    void testContainsMissingValue() {
        IntegerSet set = makeSet(10, 20, 30);

        assertFalse(set.contains(99));
    }

    @Test
    void testContainsOnEmptySet() {
        IntegerSet set = new IntegerSet();

        assertFalse(set.contains(1));
    }

    @Test
    void testLargestNormalCase() {
        IntegerSet set = makeSet(4, 10, -2, 7);

        assertEquals(10, set.largest());
    }

    @Test
    void testLargestSingleElement() {
        IntegerSet set = makeSet(42);

        assertEquals(42, set.largest());
    }

    @Test
    void testLargestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();

        assertThrows(NoSuchElementException.class, set::largest);
    }

    @Test
    void testSmallestNormalCase() {
        IntegerSet set = makeSet(4, 10, -2, 7);

        assertEquals(-2, set.smallest());
    }

    @Test
    void testSmallestSingleElement() {
        IntegerSet set = makeSet(42);

        assertEquals(42, set.smallest());
    }

    @Test
    void testSmallestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();

        assertThrows(NoSuchElementException.class, set::smallest);
    }

    @Test
    void testAddNewItem() {
        IntegerSet set = new IntegerSet();

        set.add(8);

        assertTrue(set.contains(8));
        assertEquals(1, set.length());
    }

    @Test
    void testAddDuplicateItemDoesNotChangeSet() {
        IntegerSet set = makeSet(8);

        set.add(8);

        assertEquals(1, set.length());
        assertEquals("[8]", set.toString());
    }

    @Test
    void testAddNegativeAndZeroValues() {
        IntegerSet set = new IntegerSet();

        set.add(0);
        set.add(-5);

        assertTrue(set.contains(0));
        assertTrue(set.contains(-5));
        assertEquals(2, set.length());
    }

    @Test
    void testRemoveExistingItem() {
        IntegerSet set = makeSet(1, 2, 3);

        set.remove(2);

        assertFalse(set.contains(2));
        assertEquals(2, set.length());
        assertEquals("[1, 3]", set.toString());
    }

    @Test
    void testRemoveNonExistingItemDoesNothing() {
        IntegerSet set = makeSet(1, 2, 3);

        set.remove(99);

        assertEquals(3, set.length());
        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    void testRemoveFromEmptySetDoesNothing() {
        IntegerSet set = new IntegerSet();

        set.remove(1);

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    void testUnionNormalCase() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 4, 5);

        IntegerSet result = a.union(b);

        assertEquals("[1, 2, 3, 4, 5]", result.toString());
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[3, 4, 5]", b.toString());
    }

    @Test
    void testUnionWithEmptySet() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = new IntegerSet();

        IntegerSet result = a.union(b);

        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    void testUnionBothEmpty() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        IntegerSet result = a.union(b);

        assertTrue(result.isEmpty());
        assertEquals("[]", result.toString());
    }

    @Test
    void testUnionWithNull() {
        IntegerSet a = makeSet(1, 2, 3);

        IntegerSet result = a.union(null);

        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    void testIntersectNormalCase() {
        IntegerSet a = makeSet(1, 2, 3, 4);
        IntegerSet b = makeSet(3, 4, 5, 6);

        IntegerSet result = a.intersect(b);

        assertEquals("[3, 4]", result.toString());
        assertEquals(2, result.length());
    }

    @Test
    void testIntersectNoCommonElements() {
        IntegerSet a = makeSet(1, 2);
        IntegerSet b = makeSet(3, 4);

        IntegerSet result = a.intersect(b);

        assertTrue(result.isEmpty());
        assertEquals("[]", result.toString());
    }

    @Test
    void testIntersectWithEmptySet() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = new IntegerSet();

        IntegerSet result = a.intersect(b);

        assertTrue(result.isEmpty());
    }

    @Test
    void testIntersectWithNull() {
        IntegerSet a = makeSet(1, 2, 3);

        IntegerSet result = a.intersect(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDiffNormalCase() {
        IntegerSet a = makeSet(1, 2, 3, 4);
        IntegerSet b = makeSet(3, 4, 5);

        IntegerSet result = a.diff(b);

        assertEquals("[1, 2]", result.toString());
    }

    @Test
    void testDiffNoOverlap() {
        IntegerSet a = makeSet(1, 2);
        IntegerSet b = makeSet(3, 4);

        IntegerSet result = a.diff(b);

        assertEquals("[1, 2]", result.toString());
    }

    @Test
    void testDiffAllElementsRemoved() {
        IntegerSet a = makeSet(1, 2);
        IntegerSet b = makeSet(1, 2, 3);

        IntegerSet result = a.diff(b);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDiffWithNullReturnsCopyOfOriginal() {
        IntegerSet a = makeSet(1, 2, 3);

        IntegerSet result = a.diff(null);

        assertEquals("[1, 2, 3]", result.toString());
        assertNotSame(a, result);
    }

    @Test
    void testComplementNormalCase() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 4, 5);

        IntegerSet result = a.complement(b);

        assertEquals("[4, 5]", result.toString());
    }

    @Test
    void testComplementWhenOtherSetIsSubset() {
        IntegerSet a = makeSet(1, 2, 3, 4);
        IntegerSet b = makeSet(2, 3);

        IntegerSet result = a.complement(b);

        assertTrue(result.isEmpty());
    }

    @Test
    void testComplementWithNullReturnsEmptySet() {
        IntegerSet a = makeSet(1, 2, 3);

        IntegerSet result = a.complement(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testComplementWithEmptyOtherSet() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = new IntegerSet();

        IntegerSet result = a.complement(b);

        assertTrue(result.isEmpty());
    }

    @Test
    void testIsEmptyOnNewSet() {
        IntegerSet set = new IntegerSet();

        assertTrue(set.isEmpty());
    }

    @Test
    void testIsEmptyAfterAdd() {
        IntegerSet set = new IntegerSet();
        set.add(1);

        assertFalse(set.isEmpty());
    }

    @Test
    void testIsEmptyAfterClear() {
        IntegerSet set = makeSet(1, 2);
        set.clear();

        assertTrue(set.isEmpty());
    }

    @Test
    void testToStringReturnsSortedOrder() {
        IntegerSet set = makeSet(5, 1, 3, 2);

        assertEquals("[1, 2, 3, 5]", set.toString());
    }

    @Test
    void testToStringEmptySet() {
        IntegerSet set = new IntegerSet();

        assertEquals("[]", set.toString());
    }

    @Test
    void testToStringSingleElement() {
        IntegerSet set = makeSet(9);

        assertEquals("[9]", set.toString());
    }
}

Q: rewrite the test file to group related tests together and include all required edge cases from the homework document

A:
package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;

    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
    }

    // -------------------------------------------------------------------------
    // clear()
    // -------------------------------------------------------------------------

    @Test
    public void testClearNonEmpty() {
        setA.add(1);
        setA.add(2);
        setA.clear();
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    @Test
    public void testClearAlreadyEmpty() {
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    // -------------------------------------------------------------------------
    // length()
    // -------------------------------------------------------------------------

    @Test
    public void testLengthEmpty() {
        assertEquals(0, setA.length());
    }

    @Test
    public void testLengthAfterAdds() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());
    }

    @Test
    public void testLengthNoDuplicates() {
        setA.add(5);
        setA.add(5);
        assertEquals(1, setA.length());
    }

    // -------------------------------------------------------------------------
    // isEmpty()
    // -------------------------------------------------------------------------

    @Test
    public void testIsEmptyNewSet() {
        assertTrue(setA.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAdd() {
        setA.add(1);
        assertFalse(setA.isEmpty());
    }

    @Test
    public void testIsEmptyAfterRemoveAll() {
        setA.add(1);
        setA.remove(1);
        assertTrue(setA.isEmpty());
    }

    // -------------------------------------------------------------------------
    // contains()
    // -------------------------------------------------------------------------

    @Test
    public void testContainsPresent() {
        setA.add(42);
        assertTrue(setA.contains(42));
    }

    @Test
    public void testContainsAbsent() {
        assertFalse(setA.contains(99));
    }

    @Test
    public void testContainsOnEmptySet() {
        assertFalse(setA.contains(0));
    }

    @Test
    public void testContainsNegative() {
        setA.add(-7);
        assertTrue(setA.contains(-7));
        assertFalse(setA.contains(7));
    }

    // -------------------------------------------------------------------------
    // add()
    // -------------------------------------------------------------------------

    @Test
    public void testAddIncreasesLength() {
        setA.add(1);
        assertEquals(1, setA.length());
    }

    @Test
    public void testAddDuplicateIgnored() {
        setA.add(3);
        setA.add(3);
        assertEquals(1, setA.length());
        assertTrue(setA.contains(3));
    }

    @Test
    public void testAddZero() {
        setA.add(0);
        assertTrue(setA.contains(0));
    }

    @Test
    public void testAddNegative() {
        setA.add(-100);
        assertTrue(setA.contains(-100));
        assertEquals(1, setA.length());
    }

    // -------------------------------------------------------------------------
    // remove()
    // -------------------------------------------------------------------------

    @Test
    public void testRemoveExisting() {
        setA.add(5);
        setA.remove(5);
        assertFalse(setA.contains(5));
        assertEquals(0, setA.length());
    }

    @Test
    public void testRemoveNonExisting() {
        setA.add(5);
        setA.remove(99);
        assertEquals(1, setA.length());
        assertTrue(setA.contains(5));
    }

    @Test
    public void testRemoveFromEmptySet() {
        setA.remove(1); // should not throw
        assertTrue(setA.isEmpty());
    }

    // -------------------------------------------------------------------------
    // largest()
    // -------------------------------------------------------------------------

    @Test
    public void testLargestMultipleElements() {
        setA.add(3);
        setA.add(1);
        setA.add(7);
        setA.add(2);
        assertEquals(7, setA.largest());
    }

    @Test
    public void testLargestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.largest());
    }

    @Test
    public void testLargestNegatives() {
        setA.add(-10);
        setA.add(-3);
        setA.add(-7);
        assertEquals(-3, setA.largest());
    }

    @Test
    public void testLargestEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> setA.largest());
    }

    // -------------------------------------------------------------------------
    // smallest()
    // -------------------------------------------------------------------------

    @Test
    public void testSmallestMultipleElements() {
        setA.add(3);
        setA.add(1);
        setA.add(7);
        setA.add(2);
        assertEquals(1, setA.smallest());
    }

    @Test
    public void testSmallestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.smallest());
    }

    @Test
    public void testSmallestNegatives() {
        setA.add(-10);
        setA.add(-3);
        setA.add(-7);
        assertEquals(-10, setA.smallest());
    }

    @Test
    public void testSmallestEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> setA.smallest());
    }

    // -------------------------------------------------------------------------
    // equals()
    // -------------------------------------------------------------------------

    @Test
    public void testEqualsIdentical() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2); setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    public void testEqualsDifferentOrder() {
        setA.add(3); setA.add(1); setA.add(2);
        setB.add(1); setB.add(2); setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    public void testEqualsNotEqual() {
        setA.add(1); setA.add(2);
        setB.add(1); setB.add(3);
        assertFalse(setA.equals(setB));
    }

    @Test
    public void testEqualsDifferentLength() {
        setA.add(1); setA.add(2);
        setB.add(1);
        assertFalse(setA.equals(setB));
    }

    @Test
    public void testEqualsBothEmpty() {
        assertTrue(setA.equals(setB));
    }

    @Test
    public void testEqualsNull() {
        setA.add(1);
        assertFalse(setA.equals(null));
    }

    @Test
    public void testEqualsReflexive() {
        setA.add(5);
        assertTrue(setA.equals(setA));
    }

    // -------------------------------------------------------------------------
    // union()
    // -------------------------------------------------------------------------

    @Test
    public void testUnionDisjoint() {
        setA.add(1); setA.add(2);
        setB.add(3); setB.add(4);
        IntegerSet result = setA.union(setB);
        assertEquals(4, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    public void testUnionOverlap() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.union(setB);
        assertEquals(4, result.length());
    }

    @Test
    public void testUnionWithEmptyB() {
        setA.add(1); setA.add(2);
        IntegerSet result = setA.union(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    public void testUnionWithEmptyA() {
        setB.add(3); setB.add(4);
        IntegerSet result = setA.union(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    public void testUnionBothEmpty() {
        IntegerSet result = setA.union(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUnionDoesNotMutate() {
        setA.add(1); setA.add(2);
        setB.add(3);
        setA.union(setB);
        assertEquals(2, setA.length());
        assertEquals(1, setB.length());
    }

    // -------------------------------------------------------------------------
    // intersect()
    // -------------------------------------------------------------------------

    @Test
    public void testIntersectCommonElements() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testIntersectDisjoint() {
        setA.add(1); setA.add(2);
        setB.add(3); setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectWithEmpty() {
        setA.add(1); setA.add(2);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectBothEmpty() {
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectDoesNotMutate() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3);
        setA.intersect(setB);
        assertEquals(2, setA.length());
        assertEquals(2, setB.length());
    }

    // -------------------------------------------------------------------------
    // diff()
    // -------------------------------------------------------------------------

    @Test
    public void testDiffBasic() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(2); setB.add(3);
        IntegerSet result = setA.diff(setB);
        assertEquals(1, result.length());
        assertTrue(result.contains(1));
    }

    @Test
    public void testDiffDisjoint() {
        setA.add(1); setA.add(2);
        setB.add(3); setB.add(4);
        IntegerSet result = setA.diff(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    public void testDiffASubsetOfB() {
        setA.add(2); setA.add(3);
        setB.add(1); setB.add(2); setB.add(3);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffIdenticalSets() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2); setB.add(3);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffEmptyB() {
        setA.add(1); setA.add(2);
        IntegerSet result = setA.diff(setB);
        assertEquals(2, result.length());
    }

    @Test
    public void testDiffEmptyA() {
        setB.add(1); setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffDoesNotMutate() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3);
        setA.diff(setB);
        assertEquals(2, setA.length());
        assertEquals(2, setB.length());
    }

    // -------------------------------------------------------------------------
    // complement()
    // -------------------------------------------------------------------------

    @Test
    public void testComplementBasic() {
        setA.add(1); setA.add(2);
        setB.add(2); setB.add(3); setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    public void testComplementBSubsetOfA() {
        setA.add(1); setA.add(2); setA.add(3);
        setB.add(1); setB.add(2);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testComplementDisjoint() {
        setA.add(1); setA.add(2);
        setB.add(3); setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    public void testComplementEmptyB() {
        setA.add(1);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testComplementEmptyA() {
        setB.add(1); setB.add(2);
        IntegerSet result = setA.complement(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    public void testComplementDoesNotMutate() {
        setA.add(1);
        setB.add(2); setB.add(3);
        setA.complement(setB);
        assertEquals(1, setA.length());
        assertEquals(2, setB.length());
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", setA.toString());
    }

    @Test
    public void testToStringSingleElement() {
        setA.add(5);
        assertEquals("[5]", setA.toString());
    }

    @Test
    public void testToStringSorted() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());
    }

    @Test
    public void testToStringWithNegatives() {
        setA.add(2);
        setA.add(-1);
        setA.add(0);
        assertEquals("[-1, 0, 2]", setA.toString());
    }
}

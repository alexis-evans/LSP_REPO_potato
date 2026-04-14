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
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

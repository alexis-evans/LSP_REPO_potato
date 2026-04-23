package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for GradeCalculator.
 */
public class GradeCalculatorTest {

    @Test
    public void testAverageReturnsCorrectAverage() {
        GradeCalculator calculator = new GradeCalculator();

        double result = calculator.average(90, 80, 70);

        assertEquals(80.0, result, 0.001);
    }

    @Test
    public void testLetterGradeReturnsCorrectLetter() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(85.0);

        assertEquals("B", result);
    }

    @Test
    public void testIsPassingReturnsTrueForPassingAverage() {
        GradeCalculator calculator = new GradeCalculator();

        boolean result = calculator.isPassing(75.0);

        assertTrue(result);
    }

    @Test
    public void testLetterGradeBoundaryAt90ReturnsA() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(90.0);

        assertEquals("A", result);
    }

    @Test
    public void testIsPassingBoundaryAt60ReturnsTrue() {
        GradeCalculator calculator = new GradeCalculator();

        boolean result = calculator.isPassing(60.0);

        assertTrue(result);
    }

    @Test
    public void testAverageThrowsExceptionForNegativeScore() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(-1, 80, 90);
        });
    }

    @Test
    public void testAverageThrowsExceptionForScoreAbove100() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(90, 101, 80);
        });
    }
}

package org.dc;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticExpressionTest {

    record TestCase(String input, double result) {
    }

    @Test
    void testAll() {
        TestCase[] testCases = {
                new TestCase("5 + 2 * 2 + 6", 15),
                new TestCase("(5 + 2) * 2", 14),
                new TestCase("5 + (2 * 2)", 9),
                new TestCase("5 * (2 * 2)", 20),
                new TestCase("(5 * 2 * 2)", 20),
                new TestCase("((5 * 2))", 10),
                new TestCase("(5 * (2 * 2))", 20),
                new TestCase("(5 * ((2 * 2)))", 20),
                new TestCase("5 - 2", 3),
                new TestCase("3 * 5 - 2", 13),
                new TestCase("3 * (5 - 2)", 9),
                new TestCase("3 * (5 - 2 + 3)", 18),
                new TestCase("3 * (6/2)", 9),
                new TestCase("3 * 6/2", 9),
                new TestCase("4 * 6/3", 8),
                new TestCase("6 / 2 * 3", 1),
                new TestCase("(6 / 2) * 3", 9),
        };

        for (var test: testCases) {
            assertEquals(test.result,  new ArithmeticExpression(test.input).evaluate());
        }
    }
}

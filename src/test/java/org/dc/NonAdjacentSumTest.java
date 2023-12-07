package org.dc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NonAdjacentSumTest {

    record TestCase(
        int[] numbers,
        int expectedSum
    ) {}

    @Test
    void testAll() {
        var testCases = new TestCase[]{
                new TestCase(
                        new int[]{2, 4, 6, 8},
                        12
                ),
                new TestCase(
                        new int[]{5, 1, 1, 5},
                        10
                ),
                new TestCase(
                        new int[]{3, 4, 2},
                        5
                ),
                new TestCase(
                        new int[]{3, 4, 2, 5},
                        9
                ),
                new TestCase(
                        new int[]{3, 4, 2, 5, 1, -6},
                        9
                ),

        };

        for (var test: testCases) {
            assertEquals(
                    test.expectedSum,
                    NonAdjacentSum.compute(test.numbers)
            );
            assertEquals(
                    test.expectedSum,
                    NonAdjacentSum.computeIt(test.numbers)
            );
        }

    }
}

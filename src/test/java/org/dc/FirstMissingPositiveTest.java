package org.dc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstMissingPositiveTest {

     record TestCase(
            int[] numbers,
            int result
    ) {
    }

    @Test
    void testAll() {
        var cases = new TestCase[]{
                new TestCase(new int[]{1, -1, 2, -1, 3, 4, -2, 5}, 6),
                new TestCase(new int[]{2, 3, 7, 6, 8, -1, -10, 15}, 1),
                new TestCase(new int[]{3, 4, -1, 1}, 2),
                new TestCase(new int[]{1, 2, 0}, 3),
                new TestCase(new int[]{700, 1, 0}, 2),
                new TestCase(new int[]{2, 1, 0}, 3),
                new TestCase(new int[]{0, 1, 0}, 2),
                new TestCase(new int[]{0, 0, 0}, 1),
                new TestCase(new int[]{4, 3, 50}, 1),
                new TestCase(new int[]{4, 5, 6}, 1),
                new TestCase(new int[]{4, 5, 1}, 2)
        };

        for (var testCase : cases) {
            assertEquals(
                    testCase.result,
                    FirstMissingPositive.firstMissing(testCase.numbers)
            );
        }
    }
}

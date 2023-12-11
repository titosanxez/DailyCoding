package org.dc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestDistinctSubstringTest {

    record TestCase(
            String input,
            int k,
            int expectedLength
    ) {

    }

    @Test
    void testAll() {
        TestCase[] testCases = {
                new TestCase("abcba", 2, 3),
                new TestCase("aaabcccc", 2, 5),
                new TestCase("aaabcccc", 1, 4),
                new TestCase("aaabcccc", 3, 8),
        };

        for (var test : testCases) {
            assertEquals(
                    test.expectedLength,
                    LongestDistinctSubstring.compute(test.input, test.k)
            );
            assertEquals(
                    test.expectedLength,
                    LongestDistinctSubstring.computeWithMap(test.input, test.k)
            );
        }
    }
}

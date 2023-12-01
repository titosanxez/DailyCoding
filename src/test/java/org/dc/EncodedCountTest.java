package org.dc;

import org.dc.common.EncodedCount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncodedCountTest {

    record TestCase(
            String encoded,
            int expectedCount
    ) {

    }

    @Test
    void testAll() {
        var testCases = new TestCase[]{
                new TestCase("123", 3),
                new TestCase("111", 3),
                new TestCase("291", 1),
                new TestCase("191", 2),
                new TestCase("1234", 3),
                new TestCase("11111", 8),
                new TestCase("11011", 2),
        };

        for (var test : testCases) {
            assertEquals(
                    test.expectedCount,
                    EncodedCount.countValidEncodings(test.encoded)
            );
        }
    }
}

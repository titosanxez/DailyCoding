package org.dc;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class HorizontalSumTest
{
    record TestCase (String treeString, int[] expected) {
    }

    @Test
    void testAll() {
        var testCases = new TestCase[] {
                new TestCase(
                        "1,1,1,null,null,2,null,null,1,1,null,null,3,null,null",
                        new int[]{1, 2, 7}
                ),
                new TestCase(
                        "1,null,null",
                        new int[]{1}
                ),
                new TestCase(
                        "1,null,null",
                        new int[]{1}
                ),
        };

        for (var test : testCases) {
            var serializer = new SerializeTree.StringSerializer();
            var result = HorizontalSum.computeSums(
                    serializer.deserialize(test.treeString)
            );
            assertTrue(
                    Arrays.equals(test.expected, result)
            );
        }
    }
}

package org.dc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniversalTreeTest {

    record TestCase (String treeString, int expected) {

    }

    @Test
    void testAll() {
        var testCases = new TestCase[] {
                new TestCase(
                        "1,1,1,null,null,1,null,null,1,1,null,null,1,null,null",
                        8
                ),
                new TestCase(
                        "1,1,1,null,null,2,null,null,1,1,null,null,1,null,null",
                        7
                ),
                new TestCase(
                        "0,1,null,null,0,1,1,null,null,1,null,null,0,null,null",
                        5
                ),
                new TestCase(
                        "null",
                        0
                ),
                new TestCase(
                        "1,null,null",
                        1
                ),
                new TestCase(
                        "1,1,null,null,null",
                        2
                ),
                new TestCase(
                        "1,1,null,null,2,null,null",
                        2
                ),
                new TestCase(
                        "1,2,null,null,3,null,null",
                        2
                )
        };
        for (var test :testCases) {
            var serializer = new SerializeTree.StringSerializer();
            var root = serializer.deserialize(test.treeString);
            assertEquals(test.expected, UniversalTree.check(root));
        }

    }
}

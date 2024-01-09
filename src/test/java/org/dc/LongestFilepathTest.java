package org.dc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongestFilepathTest {

    record TestCase(
            String input, String result) {
    }

    @Test
    void testAll() {
        TestCase[] tests = {
                new TestCase(
                        "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
                        "dir/subdir2/file.ext"
                ),
                new TestCase(
                        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext\n\t\tfile3.ext\n\tsubdir3",
                        "dir/subdir2/subsubdir2/file2.ext"),
                new TestCase(
                        "dir",
                        "dir"
                ),
                new TestCase("", ""),
        };

        for (var test : tests) {
            var fileNode = LongestFilepath.parse(test.input);
            System.out.println(fileNode);
            System.out.println(fileNode.list());
            assertEquals(test.result, fileNode.longestFilepath());
        }
    }
}

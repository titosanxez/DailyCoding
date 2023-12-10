package org.dc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StaircaseTest {

    record TestCase(
            int size,
            List<List<Integer>> climbs
    ) {
    }

    @Test
    void testAll() {
        var testCases = new TestCase[]{
                new TestCase(
                        4,
                        List.of(
                                List.of(1, 1, 1, 1),
                                List.of(2, 1, 1),
                                List.of(1, 2, 1),
                                List.of(1, 1, 2),
                                List.of(2, 2)
                        )
                ),
                new TestCase(
                        1,
                        List.of(
                                List.of(1)
                        )
                ),
                new TestCase(
                        2,
                        List.of(
                                List.of(1, 1),
                                List.of(2)
                        )
                ),
                new TestCase(
                        3,
                        List.of(
                                List.of(1, 1, 1),
                                List.of(2, 1),
                                List.of(1, 2)
                        )
                ),
                new TestCase(
                        5,
                        List.of(
                                List.of(1, 1, 1, 1,1),
                                List.of(2, 1, 1, 1),
                                List.of(1, 2, 1,1),
                                List.of(1, 1, 2, 1),
                                List.of(2, 2, 1),
                                List.of(1, 1, 1, 2),
                                List.of(2, 1, 2),
                                List.of(1, 2, 2)
                        )
                ),
        };

        for (var test : testCases) {
            assertEquals(
                    test.climbs,
                    Staircase.climb(test.size)
            );
        }
    }
}

package org.dc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class OverlappingIntervalsTest {

    record TestCase(
            List<OverlappingIntervals.Interval> intervals,
            int count
    ) {}

    @Test
    void testAll() {
        TestCase[] testCases = {
                new TestCase(
                        List.of(
                                new OverlappingIntervals.Interval(1, 2),
                                new OverlappingIntervals.Interval(3, 5)
                        ),
                        0
                ),
                new TestCase(
                        List.of(
                                new OverlappingIntervals.Interval(1, 2),
                                new OverlappingIntervals.Interval(0, 2),
                                new OverlappingIntervals.Interval(0, 3)
                        ),
                        3
                ),
                new TestCase(
                        List.of(
                                new OverlappingIntervals.Interval(1, 7),
                                new OverlappingIntervals.Interval(0, 3),
                                new OverlappingIntervals.Interval(-1, 2),
                                new OverlappingIntervals.Interval(5, 7)
                        ),
                        4
                ),
                new TestCase(
                        List.of(),
                        0
                ),
        };

        for (var test : testCases) {
            assertEquals(
                    test.count,
                    OverlappingIntervals.countOverlaps(test.intervals)
            );
        }
    }

}

package org.dc;

import java.util.ArrayList;
import java.util.List;

public class OverlappingIntervals {

    public static int countOverlaps(
            List<Interval> intervals
    ) {
        // first order by increasing upper limit
        var orderedIntervals = new ArrayList<Interval>(intervals.size());
        for (var interval : intervals) {
            insertOrdered(orderedIntervals, interval);
        }

        // Count overlapping intervals by comparing interval Ii::max with Ii+1::min
        int overlapCount = 0;
        for (int i = 0; i < orderedIntervals.size()-1 ; i++) {
            Interval currentInterval = orderedIntervals.get(i);
            for (int j = i + 1; j < orderedIntervals.size() ; j++) {
                if (orderedIntervals.get(j).low <= currentInterval.high) {
                    ++overlapCount;
                }
            }
        }

        return overlapCount;
    }

    private static void insertOrdered(
            List<Interval> ordered,
            Interval interval
    ) {
        int index = 0;
        for (; index < ordered.size(); ++index) {
            if (ordered.get(index).high > interval.high) {
                if (index != 0) {
                    --index;
                }
                break;
            }
        }

        ordered.add(index, interval);
    }

    record Interval(int low, int high) { }
}

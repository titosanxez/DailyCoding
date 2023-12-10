package org.dc;

import java.util.ArrayList;
import java.util.List;

public class Staircase {
    private static final int[] STEP_SIZES = {1, 2};

    public static List<List<Integer>> climb(int stairSize) {
        var climbs = new ArrayList<List<Integer>>();

        for (var stepSize : STEP_SIZES) {
            var steps = new ArrayList<Integer>();
            int nextSize = stairSize - stepSize;
            if (nextSize < 0) {
                continue;
            }
            // valid step
            if (nextSize == 0) {
                steps.add(stepSize);
                climbs.add(steps);
                return climbs;
            }

            var nextClimbs = climb(nextSize);
            for (var nextSteps : nextClimbs) {
                nextSteps.add(stepSize);
            }
            climbs.addAll(nextClimbs);
        }

        return climbs;
    }
}

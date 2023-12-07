package org.dc;

public class NonAdjacentSum {
    
    public static int compute(int[] numbers) {
        // Iterate over the entire array to check each possible combination
        int maxSum = 0;
        for (int i = 0; i < numbers.length; ++i) {
            int nextSum = computeSection(numbers, i);
            maxSum = Math.max(nextSum, maxSum);
        }

        return maxSum;
    }

    private static int computeSection(
            int[] numbers,
            int currentIndex
    ) {

        int  maxSum = numbers[currentIndex];
        for (int distance=2; distance < numbers.length - currentIndex; ++distance) {
            int currentSum = 0;
            for (int i = currentIndex + distance; i < numbers.length; i+=distance) {
                int nextSum = computeSection(numbers, i);
                nextSum = Math.max(nextSum, nextSum + numbers[currentIndex]);
                currentSum = Math.max(currentSum, nextSum);
            }
            maxSum =  Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    public static int computeIt(int[] numbers) {
        int[] history = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        int[] historyIndexes = new int[]{-1, -1};

        int maxSum = 0;
        for (int i = numbers.length - 1; i >=0; --i) {
            int historySum = numbers[i];
            try {
                historySum = Math.addExact(
                    numbers[i],
                    findSuitableHistoryMax(history, historyIndexes, i)
                );
            } catch (ArithmeticException ignored) {
            }

            int nextSum = computeSectionIt(numbers, i);
            int maxSection = Math.max(nextSum, historySum);
            // update history
            updateHistory(history, historyIndexes, maxSection, i);

            maxSum = Math.max(maxSum, maxSection);
        }

        return maxSum;
    }

    private static void updateHistory(
            int[] history,
            int[] historyIndexes,
            int maxValue,
            int index) {
        int minValue = Integer.MAX_VALUE;
        int historyIndex = -1;
        for (int i = 0; i < history.length; i++) {
            if (history[i] < minValue) {
                minValue = history[i];
                historyIndex = i;
            }
        }

        if (historyIndex != -1) {
            history[historyIndex] = maxValue;
            historyIndexes[historyIndex] = index;
        }
    }

    private static int findSuitableHistoryMax(
            int[] history,
            int [] historyIndexes,
            int fromIndex) {

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < historyIndexes.length ; i++) {
            if (historyIndexes[i] > 0
                    && historyIndexes[i] - fromIndex > 1
                    && history[i] > maxValue) {
                maxValue = history[i];
            }
        }

        return maxValue;
    }

    private static int computeSectionIt(int[] numbers, int startIndex) {
        int maxSum = numbers[startIndex];
        for (int i = startIndex; i < numbers.length; i++) {
            int currentSum = numbers[i];

            for (int distance = 2; distance < numbers.length - i; distance++) {
                int nextSum = 0;
                for (int j = i + distance; j < numbers.length; j+=distance) {
                    nextSum += numbers[j];
                }
                nextSum += numbers[i];
                currentSum = Math.max(currentSum, nextSum);
            }
        }

        return maxSum;
    }
}

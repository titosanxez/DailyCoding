package org.dc;

public class FirstMissingPositive {

    public static int firstMissing(int[] elements) {
        // erase negatives
        int lastAvailableIndex = elements.length - 1;
        int positiveCount = 0;
        for (int i = 0; i <= lastAvailableIndex; i++) {
            if (elements[i] <= 0) {
                lastAvailableIndex = nextAvailableIndex(elements, i, lastAvailableIndex);
                if (lastAvailableIndex != i) {
                    swap(elements, i, lastAvailableIndex);
                    --lastAvailableIndex;
                    ++positiveCount;
                }
            } else {
                ++positiveCount;
            }
        }

        // Mark existing
        // element as negative value
        boolean edgeFound = false;
        for (int i = 0; i < positiveCount; i++) {
            var valueAsIndex = Math.abs(elements[i]);
            if (valueAsIndex == positiveCount) {
                edgeFound = true;
            }
            if (valueAsIndex < elements.length) {
                elements[valueAsIndex] = (elements[valueAsIndex] > 0)
                        ? elements[valueAsIndex] * -1
                        : -1;
            }
        }

        for (int i = 1; i < elements.length; ++i) {
            if (elements[i] > 0) {
                return i;
            }
        }

        if (edgeFound) {
            return positiveCount + 1;
        }

        return positiveCount == 0 ? 1: positiveCount;
    }

    static int nextAvailableIndex(int[] elements, int currentIndex, int lastAvailable) {
        for (int i = lastAvailable; i > currentIndex ; --i) {
            if (elements[i] > 0) {
                return i;
            }
        }

        return currentIndex;
    }

    static void swap(int[] elements, int left, int right) {
        var temp = elements[left];
        elements[left] = elements[right];
        elements[right] = temp;
    }
}

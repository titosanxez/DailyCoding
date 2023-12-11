package org.dc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestDistinctSubstring {

    public static int compute(String input, int k) {
        var currentSet = new HashSet<Character>();
        int startIndex = 0;
        int endIndex = 0;
        int maxLength = 0;

        while (endIndex < input.length()) {
            char nextChar = input.charAt(endIndex);

            if (currentSet.contains(nextChar)) {
                ++endIndex;
                continue;
            }

            if (currentSet.size() < k) {
                ++endIndex;
                currentSet.add(nextChar);
                continue;
            }

            int currentLength = endIndex - startIndex;
            maxLength = Math.max(
                    maxLength,
                    currentLength
            );
            endIndex -= currentLength - 1;
            startIndex = endIndex;
            currentSet.clear();
        }

        // last iteration
        if (!currentSet.isEmpty()) {
            maxLength = Math.max(
                    maxLength,
                    endIndex - startIndex
            );
        }

        return maxLength;
    }

    public static int computeWithMap(String input, int k) {
        var appearedChars = new HashMap<Character, Integer>();
        int cursor = 0;
        int startIndex = 0;
        int maxLength = 0;

        while (cursor < input.length()) {
            char nextChar = input.charAt(cursor);

            if (appearedChars.containsKey(nextChar)) {
                appearedChars.put(
                        nextChar,
                        appearedChars.get(nextChar) + 1
                );
            } else {
                appearedChars.put(nextChar, 1);

                // The map contains all the characters that have appeared so far,
                // and we can skip re-processing a total of characters that is given
                // by:
                //      to_skip = a + b + ... < k
                // that is, as many characters such that each different character, including
                // its repetition count, is less than the number of accepted different characters.
                while (appearedChars.size() > k) {
                    char inputChar = input.charAt(startIndex);
                    int repetition = appearedChars.get(inputChar);
                    if (repetition == 1) {
                        appearedChars.remove(inputChar);
                    } else {
                        appearedChars.put(
                                inputChar,
                                appearedChars.get(inputChar) - 1
                        );
                    }
                    ++startIndex;
                }
            }

            maxLength = Math.max(maxLength, cursor - startIndex + 1);
            ++cursor;
        }

        return maxLength;

    }
}

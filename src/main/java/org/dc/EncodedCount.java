package org.dc;

public class EncodedCount {
    private static final int MAX_ENCODED_VALUE = 26;
    private static final int MIN_ENCODED_VALUE = 1;

    public static int countValidEncodings(String encoded) {
        return countValidEncodings(encoded, 0);
    }

    private static int countValidEncodings(String encoded, int startIndex) {
        if (startIndex == encoded.length()) {
            return 1;
        }

        int count = 0;
        for (int chunkSize = 1; chunkSize <= 2; ++chunkSize) {
            if (startIndex + chunkSize > encoded.length()) {
                return count;
            }

            // read one or two chars max
            var value = Integer.parseInt(
                    encoded.substring(startIndex, startIndex + chunkSize)
            );
            // When we find an encoded value out of range, it implies the
            // existing combination is invalid, so we move on to the next
            if (value < MIN_ENCODED_VALUE
                    || value > MAX_ENCODED_VALUE
                    || value < 10 && chunkSize == 2
            ) {
                continue;
            }
            // If the encoded value is valid, we can continue looking for the remaining
            // of the chain
            count += countValidEncodings(encoded, startIndex + chunkSize);
        }

        return count;
    }

}

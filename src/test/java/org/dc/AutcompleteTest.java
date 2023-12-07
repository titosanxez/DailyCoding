package org.dc;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AutcompleteTest {

    record TestCase(
            String[] wordSet,
            String input,
            List<String> expectedResult) {
    }

    @Test
    void testAll() {
        var testCases = new TestCase[]{
                new TestCase(
                        new String[]{"dog", "deer", "deal"},
                        "de",
                        List.of("deal", "deer")
                ),
                new TestCase(
                        new String[]{"dog", "deer", "deal"},
                        "dee",
                        List.of("deer")
                ),
                new TestCase(
                        new String[]{"dog", "deer", "deal"},
                        "",
                        List.of("deal", "deer", "dog")
                ),
                new TestCase(
                        new String[]{"dog", "deer", "deal"},
                        "z",
                        List.of()
                ),
                new TestCase(
                        new String[]{"dog", "deer", "deal"},
                        "deals",
                        List.of()
                ),
                new TestCase(
                        new String[]{"arc", "carbon", "deal"},
                        "a",
                        List.of("arc")
                )
        };

        for (var test : testCases) {
            var autocomplete = new Autocomplete(test.wordSet);
            assertEquals(
                    test.expectedResult,
                    autocomplete.trigger(test.input)
            );
        }
    }
}

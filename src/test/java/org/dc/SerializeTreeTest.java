package org.dc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class SerializeTreeTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,4,null,null,5,null,null,3,6,null,null,7,null,null",
            "1,2,4,null,null,null,3,null,7,null,null",
            "1,2,4,null,null,null,null",
            "1,null,2,null,3,null,null",
            "1,null,null",
            "null"
    })
    void testAll(String input) {
        var serializer = new SerializeTree.StringSerializer();
        var node = serializer.deserialize(input);
        var treeString = serializer.serialize(node);
        assertEquals(input, treeString);
    }
}

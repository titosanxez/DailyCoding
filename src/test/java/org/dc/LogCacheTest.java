package org.dc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LogCacheTest {

    @Test
    void testFlow() {
        var logCache = new LogCache(3);
        var entries = new ArrayList<Long>();

        for (int i = 0; i < 3 ; i++) {
            entries.add((long) i * 10);
            logCache.record(entries.get(i));
        }
        assertEquals(3, logCache.size());
        assertEquals(entries.get(2), logCache.getAt(0));
        assertEquals(entries.get(1), logCache.getAt(1));
        assertEquals(entries.get(0), logCache.getAt(2));
        // add items beyond max size
        entries.add(30L);
        logCache.record(entries.get(3));
        assertEquals(3, logCache.size());
        assertEquals(entries.get(3), logCache.getAt(0));
        assertEquals(entries.get(2), logCache.getAt(1));
        assertEquals(entries.get(1), logCache.getAt(2));
        // repeat
        logCache.record(40L);
        assertEquals(3, logCache.size());
        assertEquals(40L, logCache.getAt(0));
        assertEquals(entries.get(3), logCache.getAt(1));
        assertEquals(entries.get(2), logCache.getAt(2));
        logCache.record(50L);
        assertEquals(3, logCache.size());
        assertEquals(50L, logCache.getAt(0));
        assertEquals(40L, logCache.getAt(1));
        assertEquals(entries.get(3), logCache.getAt(2));
    }

    @Test
    void testRemoval() {
        var logCache = new LogCache(3);
        var entries = new ArrayList<Long>();

        for (int i = 0; i < 3 ; i++) {
            entries.add((long) i * 10);
            logCache.record(entries.get(i));
        }
        assertEquals(3, logCache.size());
        // remove middle element, past the start index
        logCache.remove(1);
        assertEquals(2, logCache.size());
        assertEquals(entries.get(2), logCache.getAt(0));
        assertEquals(entries.get(0), logCache.getAt(1));
        // remove first element on a size < max
        logCache.remove(0);
        assertEquals(1, logCache.size());
        assertEquals(entries.get(0), logCache.getAt(0));
        // add enough elements to fill up the buffer and overflow by two positions
        logCache.record(30L);
        logCache.record(40L);
        logCache.record(50L);
        logCache.record(60L);
        assertEquals(3, logCache.size());
        assertEquals(60L, logCache.getAt(0));
        // remove older position
        logCache.remove(2);
        assertEquals(2, logCache.size());
        assertEquals(60L, logCache.getAt(0));
        assertEquals(50L, logCache.getAt(1));
    }
}

package org.dc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XORLinkedListTest {

    @Test
    void testAll() {
        var list = new XORLinkedList();
        assertNull(list.last());
        assertNull(list.getAt(0));
        // Add first node
        var node = list.createNode();
        list.add(node);
        assertEquals(node, list.last());
        assertEquals(node, list.getAt(0));
        // Add second node
        var second = list.createNode();
        list.add(second);
        assertEquals(second, list.last());
        assertEquals(node, list.getAt(0));
        assertEquals(second, list.getAt(1));
        // Add third node
        var third = list.createNode();
        list.add(third);
        assertEquals(third, list.last());
        assertEquals(node, list.getAt(0));
        assertEquals(second, list.getAt(1));
        assertEquals(third, list.getAt(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.getAt(5));
    }

    @Test
    void testIterator() {
        var list = new XORLinkedList();
        var it = list.iterator();
        assertFalse(it.hasNext());
        // one node
        list.add(list.createNode());
        it = list.iterator();
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
        // two nodes
        list.add(list.createNode());
        var count = 0;
        it = list.iterator();
        while (it.hasNext()) {
            assertNotNull(it.next());
            ++count;
        }
        assertEquals(2, count);
    }

    @Test
    void testAddNodeAfter() {
        var list = new XORLinkedList();
        // two nodes
        var firstNode = list.createNode();
        list.add(firstNode);
        var secondNode = list.createNode();
        list.add(secondNode, firstNode);
        assertEquals(firstNode, list.getAt(0));
        assertEquals(secondNode, list.getAt(1));
        // Add a third node in between
        var thirdNode = list.createNode();
        list.add(thirdNode, firstNode);
        assertEquals(thirdNode, list.getAt(1));
        assertEquals(secondNode, list.getAt(2));
    }

}

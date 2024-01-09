package org.dc;

public class LogCache {
    private final Long[] entries;
    private int startIndex;
    private int size;

    public LogCache(int maxEntries) {
        this.entries = new Long[maxEntries];
        this.startIndex = this.entries.length;
    }

    public void record(Long orderId) {
        this.startIndex =
                (this.startIndex - 1 + this.entries.length) % this.entries.length;
        if (this.size < this.entries.length) {
            ++this.size;
        }
        this.entries[this.startIndex] = orderId;
    }

    public Long getAt(int index) {
        int actualIndex = this.computeIndex(index);
        return this.entries[actualIndex];
    }

    private int computeIndex(int index) {
        return (this.startIndex + index) % this.entries.length;
    }

    public int size() {
        return this.size;
    }

    public void remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        int actualIndex = this.computeIndex(index);
        for (int i = actualIndex; i > 0; --i) {
            this.entries[i] = this.entries[i-1];
        }
        if (this.startIndex <= actualIndex) {
            this.startIndex = (this.startIndex + 1) % this.entries.length;
        }
        this.size -= 1;
    }

}

package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
    private final Queue<Integer> buffer;
    private final int maxSize;

    public SharedBuffer(int maxSize) {
        this.buffer = new LinkedList<>();
        this.maxSize = maxSize;
    }

    // Synchronized method to add an element to the buffer
    public synchronized void add(int value) throws InterruptedException {
        while (buffer.size() == maxSize) {
            wait(); // Wait if buffer is full
        }
        buffer.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // Notify waiting threads
    }

    // Synchronized method to remove an element from the buffer
    public synchronized int remove() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Wait if buffer is empty
        }
        int value = buffer.poll();
        System.out.println("Consumed: " + value);
        notifyAll(); // Notify waiting threads
        return value;
    }
}

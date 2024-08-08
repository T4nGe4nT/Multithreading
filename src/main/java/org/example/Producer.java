package org.example;

import java.util.Random;

public class Producer implements Runnable {
    private final SharedBuffer buffer;
    private final Random random = new Random();
    private final int maxIterations;

    public Producer(SharedBuffer buffer, int maxIterations) {
        this.buffer = buffer;
        this.maxIterations = maxIterations;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < maxIterations; i++) {
                int number = random.nextInt(100); // Generate random number
                buffer.add(number); // Add to buffer
                Thread.sleep(1000); // Sleep to simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


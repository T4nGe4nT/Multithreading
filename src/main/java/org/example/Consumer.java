package org.example;

public class Consumer implements Runnable {
    private final SharedBuffer buffer;
    private int sum = 0;
    private final int maxIterations;

    public Consumer(SharedBuffer buffer, int maxIterations) {
        this.buffer = buffer;
        this.maxIterations = maxIterations;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < maxIterations; i++) {
                int number = buffer.remove(); // Remove from buffer
                sum += number; // Calculate sum
                System.out.println("Sum: " + sum);
                Thread.sleep(1500); // Sleep to simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


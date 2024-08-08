package org.example;

public class Main {
    public static void main(String[] args) {
        final int BUFFER_SIZE = 10;
        final int MAX_ITERATIONS = 20; // Number of iterations to run

        SharedBuffer sharedBuffer = new SharedBuffer(BUFFER_SIZE);

        Producer producer = new Producer(sharedBuffer, MAX_ITERATIONS);
        Consumer consumer = new Consumer(sharedBuffer, MAX_ITERATIONS);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Program has completed execution.");
    }
}
// If im grasping this right the producer makes a number and passes it to the buffer. the consumer grabs it.
// The consumer then takes and adds that number to what ever value it has.
// The buffer acts a que handling the values the producer passes in until the consumer can take them.
// This would make sense if I had a method retrieving or calculating data at a different rate than another method would process.
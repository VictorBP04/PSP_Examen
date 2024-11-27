package ejercicios1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contador concurrente utilizando AtomicInteger.
 */
public class ConcurrentCounter {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        Runnable incrementTask = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " incrementa a " + counter.incrementAndGet());
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(incrementTask);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Contador final: " + counter.get());
    }
}

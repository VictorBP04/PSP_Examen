package ejercicios1;

import java.util.LinkedList;

/**
 * Problema Productor-Consumidor con un búfer limitado.
 */
public class ProducerConsumer {
    static class Buffer {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int capacity;

        public Buffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void produce(int value) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // Esperar a que haya espacio disponible
            }
            queue.add(value);
            System.out.println("Producido: " + value);
            notifyAll(); // Notificar a los consumidores
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Esperar a que haya elementos disponibles
            }
            int value = queue.removeFirst();
            System.out.println("Consumido: " + value);
            notifyAll(); // Notificar a los productores
            return value;
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        // Productor
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumidor
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.consume();
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}

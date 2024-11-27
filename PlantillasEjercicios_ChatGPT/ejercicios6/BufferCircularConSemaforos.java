package ejercicios6;

// Implementa un búfer circular en el que los productores agregan elementos y los consumidores los eliminan. Utiliza semáforos para sincronizar el acceso al búfer.

import java.util.concurrent.Semaphore;

class BufferCircular {
    private final int[] buffer;
    private int count = 0, head = 0, tail = 0;
    private final Semaphore semaforoProductores;
    private final Semaphore semaforoConsumidores;

    public BufferCircular(int size) {
        buffer = new int[size];
        semaforoProductores = new Semaphore(size);  // Controla los productores
        semaforoConsumidores = new Semaphore(0);    // Controla los consumidores
    }

    public void producir(int item) throws InterruptedException {
        semaforoProductores.acquire();  // Espera si el buffer está lleno
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        semaforoConsumidores.release();  // Notifica que hay un nuevo ítem disponible
    }

    public int consumir() throws InterruptedException {
        semaforoConsumidores.acquire();  // Espera si el buffer está vacío
        int item = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        semaforoProductores.release();  // Notifica que hay espacio en el buffer
        return item;
    }
}

public class BufferCircularConSemaforos {
    public static void main(String[] args) {
        BufferCircular buffer = new BufferCircular(5);  // Buffer circular con tamaño 5
        Runnable productor = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.producir(i);
                    System.out.println(Thread.currentThread().getName() + " produjo: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumidor = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int item = buffer.consumir();
                    System.out.println(Thread.currentThread().getName() + " consumió: " + item);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y arrancar varios hilos productores y consumidores
        new Thread(productor, "Productor 1").start();
        new Thread(productor, "Productor 2").start();
        new Thread(consumidor, "Consumidor 1").start();
        new Thread(consumidor, "Consumidor 2").start();
    }
}

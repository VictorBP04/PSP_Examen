package ejercicios1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ejemplo de uso de un pool de hilos para ejecutar tareas concurrentes.
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " ejecutando tarea.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }

        executor.shutdown();
    }
}

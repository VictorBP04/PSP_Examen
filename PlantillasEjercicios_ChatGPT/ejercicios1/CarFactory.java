package ejercicios1;

import java.util.concurrent.CountDownLatch;

/**
 * Simulación de fábrica de autos donde ensambladores trabajan en paralelo.
 */
public class CarFactory {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable assembleTask = () -> {
            String task = Thread.currentThread().getName();
            System.out.println(task + " está trabajando en su parte del auto.");
            try {
                Thread.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(task + " completó su trabajo.");
            latch.countDown();
        };

        new Thread(assembleTask, "Chasis").start();
        new Thread(assembleTask, "Motor").start();
        new Thread(assembleTask, "Pintura").start();

        try {
            latch.await(); // Esperar a que todas las partes estén listas
            System.out.println("El auto ha sido ensamblado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

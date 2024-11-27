package ejercicios1;

/**
 * Ejemplo de uso de prioridades en hilos.
 */
public class PriorityThreads {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " ejecutando");
            }
        };

        Thread lowPriority = new Thread(task, "Hilo Baja Prioridad");
        Thread highPriority = new Thread(task, "Hilo Alta Prioridad");

        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.setPriority(Thread.MAX_PRIORITY);

        lowPriority.start();
        highPriority.start();
    }
}

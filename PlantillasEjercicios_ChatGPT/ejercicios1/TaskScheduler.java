package ejercicios1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Ejemplo de un programador de tareas que ejecuta acciones en intervalos regulares.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Tarea ejecutada a las " + System.currentTimeMillis());

        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        // Finalizar después de 10 segundos
        scheduler.schedule(() -> {
            System.out.println("Finalizando el programador.");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);
    }
}

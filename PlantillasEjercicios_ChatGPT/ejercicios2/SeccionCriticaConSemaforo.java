package ejercicios2;

import java.util.concurrent.Semaphore;

class SeccionCritica {
    private Semaphore semaforo = new Semaphore(1); // Semáforo binario, solo un hilo puede acceder a la vez

    // Método para acceder a la sección crítica
    public void acceso() {
        try {
            semaforo.acquire(); // Intentar adquirir el semáforo
            System.out.println("Accediendo a la sección crítica");
            // Simulación de trabajo en la sección crítica
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release(); // Liberar el semáforo para que otro hilo pueda acceder
        }
    }
}

public class SeccionCriticaConSemaforo {
    public static void main(String[] args) {
        SeccionCritica seccion = new SeccionCritica();

        // Crear dos hilos que intentan acceder a la sección crítica
        Thread hilo1 = new Thread(seccion::acceso);
        Thread hilo2 = new Thread(seccion::acceso);

        // Iniciar ambos hilos
        hilo1.start();
        hilo2.start();
    }
}

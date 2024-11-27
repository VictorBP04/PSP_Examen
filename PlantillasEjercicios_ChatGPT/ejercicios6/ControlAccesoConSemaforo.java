package ejercicios6;

//Implementa un sistema de control de acceso donde solo un hilo puede acceder a un recurso en un momento dado. Utiliza un semáforo binario para garantizar que los hilos no accedan al recurso de manera concurrente.


import java.util.concurrent.Semaphore;

class Recurso {
    private final Semaphore semaforo = new Semaphore(1);  // Semáforo binario

    public void accederRecurso(String hilo) throws InterruptedException {
        semaforo.acquire();  // Adquiere el semáforo (bloquea el acceso)
        System.out.println(hilo + " está accediendo al recurso.");
        Thread.sleep(1000);  // Simula el uso del recurso
        semaforo.release();  // Libera el semáforo (permite el acceso a otros)
    }
}

public class ControlAccesoConSemaforo {
    public static void main(String[] args) {
        Recurso recurso = new Recurso();
        Runnable tarea = () -> {
            try {
                recurso.accederRecurso(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y arrancar varios hilos para acceder al recurso
        for (int i = 0; i < 5; i++) {
            new Thread(tarea, "Hilo " + i).start();
        }
    }
}


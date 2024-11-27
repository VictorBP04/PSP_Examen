package ejercicios5;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class ControlAereo {
    // Semáforo que controla el acceso a las pistas (2 pistas disponibles).
    static Semaphore pistas = new Semaphore(2);
    // Cola de prioridad para gestionar la secuencia de aviones (prioridad: aterrizajes primero).
    static PriorityBlockingQueue<String> cola = new PriorityBlockingQueue<>();

    static class Avion implements Runnable {
        private String operacion; // "aterrizar" o "despegar"

        public Avion(String operacion) {
            this.operacion = operacion;
        }

        @Override
        public void run() {
            try {
                // Añade el avión a la cola y espera su turno.
                cola.put(Thread.currentThread().getName() + " solicita " + operacion);
                pistas.acquire(); // Espera hasta que haya una pista disponible.

                System.out.println(Thread.currentThread().getName() + " comienza a " + operacion + ".");
                Thread.sleep(3000); // Simula el tiempo que toma la operación.

                System.out.println(Thread.currentThread().getName() + " finaliza " + operacion + ".");
                pistas.release(); // Libera la pista para el próximo avión.

                cola.remove(Thread.currentThread().getName() + " solicita " + operacion); // Sale de la cola.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Se crean 6 aviones, alternando entre aterrizajes y despegues.
        for (int i = 1; i <= 6; i++) {
            String operacion = (i % 2 == 0) ? "despegar" : "aterrizar";
            new Thread(new Avion(operacion), "Avión " + i).start();
        }
    }
}


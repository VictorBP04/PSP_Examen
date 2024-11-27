package ejercicios5;

import java.util.concurrent.Semaphore;

public class ClusterComputo {
    // El clúster tiene 3 nodos disponibles.
    static Semaphore nodos = new Semaphore(3);

    static class Tarea implements Runnable {
        @Override
        public void run() {
            try {
                nodos.acquire(); // Reserva un nodo.
                System.out.println(Thread.currentThread().getName() + " ejecuta su tarea en un nodo.");

                Thread.sleep(2000); // Simula la duración de la tarea.

                System.out.println(Thread.currentThread().getName() + " libera el nodo.");
                nodos.release(); // Libera el nodo.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            new Thread(new Tarea(), "Tarea " + i).start();
        }
    }
}


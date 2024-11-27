package ejercicios5;

import java.util.concurrent.Semaphore;

public class PasoNivel {
    static Semaphore barrera = new Semaphore(1); // La barrera solo puede estar en una posición (abierta/cerrada).

    static class Tren implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Tren acercándose. Cerrando barrera.");
                barrera.acquire(); // Cierra la barrera.

                Thread.sleep(5000); // Simula el paso del tren.

                System.out.println("Tren ha pasado. Abriendo barrera.");
                barrera.release(); // Abre la barrera.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Coche implements Runnable {
        private int id;

        public Coche(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Coche " + id + " espera a que la barrera esté abierta.");
                barrera.acquire(); // Espera a que la barrera esté abierta.
                System.out.println("Coche " + id + " cruza el paso.");

                Thread.sleep(1000); // Simula el tiempo de cruce.
                barrera.release(); // Libera el paso.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Tren()).start();
        for (int i = 1; i <= 5; i++) {
            new Thread(new Coche(i)).start();
        }
    }
}


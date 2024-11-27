package ejercicios5;

import java.util.concurrent.Semaphore;

public class Impresora {
    // Solo una impresora disponible.
    static Semaphore impresora = new Semaphore(1);

    static class Trabajo implements Runnable {
        @Override
        public void run() {
            try {
                impresora.acquire(); // Reserva la impresora.
                System.out.println(Thread.currentThread().getName() + " empieza a imprimir.");

                Thread.sleep(3000); // Simula el tiempo de impresión.

                System.out.println(Thread.currentThread().getName() + " finaliza impresión.");
                impresora.release(); // Libera la impresora.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(new Trabajo(), "Trabajo " + i).start();
        }
    }
}


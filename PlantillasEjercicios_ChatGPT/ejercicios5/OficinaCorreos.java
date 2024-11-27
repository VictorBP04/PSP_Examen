package ejercicios5;

import java.util.concurrent.Semaphore;

public class OficinaCorreos {
    static Semaphore ventanillas = new Semaphore(2); // 2 ventanillas disponibles

    static class Cliente implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " llega a la oficina.");
                ventanillas.acquire();
                System.out.println(Thread.currentThread().getName() + " es atendido.");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " termina.");
                ventanillas.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(new Cliente(), "Cliente " + i).start();
        }
    }
}

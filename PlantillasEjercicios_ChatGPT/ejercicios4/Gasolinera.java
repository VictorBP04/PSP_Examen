package ejercicios4;

import java.util.concurrent.Semaphore;

public class Gasolinera {
    static Semaphore bombas = new Semaphore(3); // 3 bombas de gasolina

    static class Coche implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " llega a la gasolinera.");
                bombas.acquire(); // Espera a que haya una bomba libre
                System.out.println(Thread.currentThread().getName() + " empieza a repostar.");
                Thread.sleep((int) (Math.random() * 5000)); // Simula el tiempo de repostaje
                System.out.println(Thread.currentThread().getName() + " termina de repostar.");
                bombas.release(); // Libera la bomba
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            new Thread(new Coche(), "Coche " + i).start();
        }
    }
}

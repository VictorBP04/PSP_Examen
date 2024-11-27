package ejercicios4;

import java.util.concurrent.Semaphore;

public class CruceFerroviario {
    static Semaphore cruce = new Semaphore(1); // Solo un tren o coche puede usar el cruce

    static class Tren implements Runnable {
        @Override
        public void run() {
            try {
                cruce.acquire();
                System.out.println(Thread.currentThread().getName() + " cruza el cruce.");
                Thread.sleep(3000);
                cruce.release();
                System.out.println(Thread.currentThread().getName() + " dejó el cruce.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Coche implements Runnable {
        @Override
        public void run() {
            try {
                cruce.acquire();
                System.out.println(Thread.currentThread().getName() + " cruza el cruce.");
                Thread.sleep(1000);
                cruce.release();
                System.out.println(Thread.currentThread().getName() + " dejó el cruce.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Tren(), "Tren 1").start();
        for (int i = 1; i <= 3; i++) {
            new Thread(new Coche(), "Coche " + i).start();
        }
    }
}

package ejercicios4;

import java.util.concurrent.Semaphore;

public class Banker {
    static int recursosDisponibles = 10;
    static Semaphore mutex = new Semaphore(1);

    static class Proceso implements Runnable {
        private int recursosSolicitados;

        public Proceso(int recursos) {
            this.recursosSolicitados = recursos;
        }

        @Override
        public void run() {
            try {
                mutex.acquire();
                if (recursosSolicitados <= recursosDisponibles) {
                    recursosDisponibles -= recursosSolicitados;
                    System.out.println(Thread.currentThread().getName() + " asignado con " + recursosSolicitados + " recursos.");
                    Thread.sleep(2000); // Simula el uso de recursos
                    recursosDisponibles += recursosSolicitados;
                    System.out.println(Thread.currentThread().getName() + " liberó " + recursosSolicitados + " recursos.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " no pudo obtener recursos.");
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Proceso((int) (Math.random() * 4) + 1), "Proceso " + i).start();
        }
    }
}

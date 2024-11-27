package ejercicios4;

import java.util.concurrent.Semaphore;

public class Parking {
    static Semaphore plazasDisponibles = new Semaphore(5); // 5 plazas en el parking
    static Semaphore accesoPuerta = new Semaphore(1); // Solo un coche puede pasar por la puerta a la vez

    static class Coche implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " llega al parking.");
                accesoPuerta.acquire(); // Espera para entrar por la puerta

                if (plazasDisponibles.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " entra al parking.");
                    Thread.sleep(2000); // Simula el tiempo de estacionamiento
                    plazasDisponibles.release(); // Libera la plaza al salir
                    System.out.println(Thread.currentThread().getName() + " sale del parking.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " no encuentra plazas y se va.");
                }

                accesoPuerta.release(); // Libera la puerta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Coche(), "Coche " + i).start();
            try {
                Thread.sleep(500); // Simula llegadas escalonadas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

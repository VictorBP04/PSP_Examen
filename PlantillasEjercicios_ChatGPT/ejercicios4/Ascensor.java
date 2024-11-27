package ejercicios4;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Ascensor {
    static PriorityBlockingQueue<Integer> solicitudes = new PriorityBlockingQueue<>();
    static Semaphore ascensorDisponible = new Semaphore(1); // Solo un hilo puede mover el ascensor

    static class Usuario implements Runnable {
        private int pisoSolicitado;

        public Usuario(int piso) {
            this.pisoSolicitado = piso;
        }

        @Override
        public void run() {
            try {
                solicitudes.put(pisoSolicitado);
                System.out.println(Thread.currentThread().getName() + " solicitó el piso " + pisoSolicitado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ControlAscensor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    ascensorDisponible.acquire(); // Bloquea el ascensor
                    if (!solicitudes.isEmpty()) {
                        int piso = solicitudes.poll(); // Toma la siguiente solicitud
                        System.out.println("Ascensor va al piso " + piso);
                        Thread.sleep(2000); // Simula el tiempo de viaje
                    }
                    ascensorDisponible.release(); // Libera el ascensor
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread control = new Thread(new ControlAscensor());
        control.start();

        for (int i = 1; i <= 5; i++) {
            new Thread(new Usuario((int) (Math.random() * 10) + 1), "Usuario " + i).start();
            try {
                Thread.sleep(1000); // Simula llegadas aleatorias de usuarios
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

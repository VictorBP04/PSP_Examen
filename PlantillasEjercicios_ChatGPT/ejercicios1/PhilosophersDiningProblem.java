package ejercicios1;

import java.util.concurrent.Semaphore;

/**
 * Problema de los Filósofos Comensales: Simulación de filósofos cenando con tenedores compartidos.
 */
public class PhilosophersDiningProblem {
    static class Philosopher extends Thread {
        private int id;
        private Semaphore leftFork;
        private Semaphore rightFork;

        public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Filósofo " + id + " está pensando.");
                    Thread.sleep((int) (Math.random() * 1000));
                    System.out.println("Filósofo " + id + " quiere comer.");

                    // Intentar tomar los tenedores (semáforos)
                    leftFork.acquire();
                    rightFork.acquire();

                    System.out.println("Filósofo " + id + " está comiendo.");
                    Thread.sleep((int) (Math.random() * 1000));

                    // Dejar los tenedores
                    leftFork.release();
                    rightFork.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5;
        Semaphore[] forks = new Semaphore[NUM_FILOSOFOS];

        // Inicializar semáforos para los tenedores
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            forks[i] = new Semaphore(1);
        }

        // Crear e iniciar los hilos de los filósofos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Philosopher(i, forks[i], forks[(i + 1) % NUM_FILOSOFOS]).start();
        }
    }
}

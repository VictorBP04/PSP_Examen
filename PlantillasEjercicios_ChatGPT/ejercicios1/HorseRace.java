package ejercicios1;

import java.util.concurrent.CyclicBarrier;

/**
 * Simulación de una carrera de caballos usando CyclicBarrier para sincronización.
 */
public class HorseRace {
    static class Horse extends Thread {
        private final int horseId;
        private final CyclicBarrier barrier;

        public Horse(int horseId, CyclicBarrier barrier) {
            this.horseId = horseId;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Caballo " + horseId + " está listo.");
                barrier.await(); // Esperar a que todos los caballos estén listos

                System.out.println("Caballo " + horseId + " está corriendo...");
                Thread.sleep((int) (Math.random() * 3000));
                System.out.println("Caballo " + horseId + " terminó la carrera.");
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        final int NUM_CABALLOS = 5;
        CyclicBarrier barrier = new CyclicBarrier(NUM_CABALLOS, () -> {
            System.out.println("¡Todos los caballos empiezan a correr!");
        });

        for (int i = 1; i <= NUM_CABALLOS; i++) {
            new Horse(i, barrier).start();
        }
    }
}

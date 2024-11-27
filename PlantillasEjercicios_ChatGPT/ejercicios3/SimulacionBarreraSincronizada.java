package ejercicios3;

import java.util.concurrent.CountDownLatch;

// Clase que simula una barrera de sincronización mejorada
class BarreraSincronizada {
    private CountDownLatch latch;

    public BarreraSincronizada(int numeroDeHilos) {
        latch = new CountDownLatch(numeroDeHilos);
    }

    // Método que hace que un hilo espere hasta que todos lleguen a la barrera
    public void esperar() throws InterruptedException {
        latch.countDown(); // Decrementa el contador
        latch.await(); // Espera a que el contador llegue a cero
    }

    // Reinicia la barrera para un nuevo conjunto de hilos
    public void reiniciar(int numeroDeHilos) {
        latch = new CountDownLatch(numeroDeHilos); // Reinicia la barrera
    }
}

// Hilo que realiza un trabajo y luego espera en la barrera
class HiloTrabajo extends Thread {
    private final BarreraSincronizada barrera;

    public HiloTrabajo(BarreraSincronizada barrera) {
        this.barrera = barrera;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " está trabajando.");
            Thread.sleep((long) (Math.random() * 1000)); // Simula trabajo
            System.out.println(Thread.currentThread().getName() + " ha terminado su trabajo y espera en la barrera.");
            barrera.esperar(); // Espera en la barrera
            System.out.println(Thread.currentThread().getName() + " ha cruzado la barrera.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class SimulacionBarreraSincronizada {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 5;
        BarreraSincronizada barrera = new BarreraSincronizada(NUM_HILOS);

        HiloTrabajo[] hilos = new HiloTrabajo[NUM_HILOS];

        // Crea e inicia los hilos
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new HiloTrabajo(barrera);
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (HiloTrabajo hilo : hilos) {
            hilo.join();
        }

        // Reiniciar la barrera para un nuevo grupo de hilos
        barrera.reiniciar(NUM_HILOS);
        System.out.println("Barrera reiniciada para un nuevo grupo de hilos.");

        // Crea e inicia los hilos nuevamente
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new HiloTrabajo(barrera);
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (HiloTrabajo hilo : hilos) {
            hilo.join();
        }

        System.out.println("Todos los hilos han cruzado la barrera en la segunda ronda.");
    }
}

package ejercicios3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Barrera {
    private final int totalHilos;
    private int contador = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();

    public Barrera(int totalHilos) {
        this.totalHilos = totalHilos;
    }

    public void esperar() throws InterruptedException {
        lock.lock();
        try {
            contador++;
            if (contador == totalHilos) {
                condicion.signalAll(); // Despierta a todos los hilos
            } else {
                while (contador < totalHilos) {
                    condicion.await(); // Espera a que todos lleguen
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

class HiloConBarreras extends Thread {
    private final Barrera barrera;

    public HiloConBarreras(Barrera barrera) {
        this.barrera = barrera;
    }

    public void run() {
        try {
            System.out.println(getName() + " ha llegado a la barrera.");
            barrera.esperar();
            System.out.println(getName() + " ha cruzado la barrera.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SincronizacionConBarreras {
    public static void main(String[] args) {
        final int NUM_HILOS = 5;
        Barrera barrera = new Barrera(NUM_HILOS);
        HiloConBarreras[] hilos = new HiloConBarreras[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new HiloConBarreras(barrera);
            hilos[i].start();
        }
    }
}

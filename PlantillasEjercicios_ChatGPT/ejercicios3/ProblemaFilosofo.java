package ejercicios3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class filosofo extends Thread {
    private final int id;
    private final Lock tenedorIzquierdo;
    private final Lock tenedorDerecho;

    public filosofo(int id, Lock tenedorIzquierdo, Lock tenedorDerecho) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void comer() throws InterruptedException {
        // Intentar adquirir tenedores sin riesgo de deadlock
        while (true) {
            if (tenedorIzquierdo.tryLock()) {
                if (tenedorDerecho.tryLock()) {
                    try {
                        System.out.println("Filósofo " + id + " está comiendo.");
                        Thread.sleep((long) (Math.random() * 1000));
                    } finally {
                        tenedorDerecho.unlock();
                    }
                    break; // Sale del bucle si comió
                } else {
                    tenedorIzquierdo.unlock(); // Libera el tenedor izquierdo si no pudo tomar el derecho
                }
            }
        }
    }
}

public class ProblemaFilosofo {
    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5;
        Lock[] tenedores = new Lock[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new ReentrantLock();
        }

        filosofo[] filosofos = new filosofo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new filosofo(i, tenedores[i], tenedores[(i + 1) % NUM_FILOSOFOS]);
            filosofos[i].start();
        }
    }
}

package ejercicios3;

import java.util.concurrent.Semaphore;

class Tenedor {
    private final Semaphore semaforo = new Semaphore(1);

    public void tomar() throws InterruptedException {
        semaforo.acquire(); // Toma el tenedor
    }

    public void soltar() {
        semaforo.release(); // Suelta el tenedor
    }
}

class Filosofo extends Thread {
    private final Tenedor izquierda;
    private final Tenedor derecha;

    public Filosofo(Tenedor izquierda, Tenedor derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println(getName() + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void comer() throws InterruptedException {
        izquierda.tomar();
        derecha.tomar();
        System.out.println(getName() + " está comiendo.");
        Thread.sleep((long) (Math.random() * 1000));
        izquierda.soltar();
        derecha.soltar();
    }
}

public class ProblemaDeLosFilosofos {
    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5;
        Tenedor[] tenedores = new Tenedor[NUM_FILOSOFOS];
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];

        // Crear los tenedores
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Tenedor();
        }

        // Crear los filósofos y asignarles tenedores
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(tenedores[i], tenedores[(i + 1) % NUM_FILOSOFOS]);
            filosofos[i].start();
        }
    }
}

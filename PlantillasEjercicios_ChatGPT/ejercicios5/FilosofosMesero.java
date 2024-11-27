package ejercicios5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilosofosMesero {

    private static final int NUM_FILOSOFOS = 5;
    private final Estado[] estados = new Estado[NUM_FILOSOFOS];
    private final Lock lock = new ReentrantLock();
    private final Condition[] condiciones = new Condition[NUM_FILOSOFOS];

    // Estados posibles de un filósofo
    private enum Estado { PENSANDO, HAMBRIENTO, COMIENDO }

    public FilosofosMesero() {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            estados[i] = Estado.PENSANDO;
            condiciones[i] = lock.newCondition();
        }
    }

    // Método para intentar tomar los tenedores
    public void tomarTenedores(int id) {
        lock.lock();
        try {
            estados[id] = Estado.HAMBRIENTO;
            verificarTenedores(id); // Intenta comer si los tenedores están disponibles.
            if (estados[id] != Estado.COMIENDO) {
                condiciones[id].await(); // Si no puede comer, espera.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // Método para liberar los tenedores
    public void soltarTenedores(int id) {
        lock.lock();
        try {
            estados[id] = Estado.PENSANDO;
            System.out.println("Filósofo " + id + " dejó de comer y está pensando.");
            verificarTenedores((id + 4) % NUM_FILOSOFOS); // Verifica el filósofo a la izquierda.
            verificarTenedores((id + 1) % NUM_FILOSOFOS); // Verifica el filósofo a la derecha.
        } finally {
            lock.unlock();
        }
    }

    // Método que verifica si un filósofo puede comer
    private void verificarTenedores(int id) {
        if (estados[id] == Estado.HAMBRIENTO &&
                estados[(id + 4) % NUM_FILOSOFOS] != Estado.COMIENDO &&
                estados[(id + 1) % NUM_FILOSOFOS] != Estado.COMIENDO) {

            estados[id] = Estado.COMIENDO;
            System.out.println("Filósofo " + id + " está comiendo.");
            condiciones[id].signal(); // Despierta al filósofo que ahora puede comer.
        }
    }

    // Clase para representar un filósofo
    static class Filosofo implements Runnable {
        private final int id;
        private final FilosofosMesero monitor;

        public Filosofo(int id, FilosofosMesero monitor) {
            this.id = id;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Filósofo " + id + " está pensando.");
                    Thread.sleep((long) (Math.random() * 3000)); // Simula tiempo pensando.
                    monitor.tomarTenedores(id);
                    Thread.sleep((long) (Math.random() * 3000)); // Simula tiempo comiendo.
                    monitor.soltarTenedores(id);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FilosofosMesero monitor = new FilosofosMesero();
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            new Thread(new Filosofo(i, monitor), "Filósofo " + i).start();
        }
    }
}


package ejercicios2;

import java.util.concurrent.Semaphore;

class Filosofo extends Thread {
    private final int id;
    private final Semaphore tenedores;

    public Filosofo(int id, Semaphore tenedores) {
        this.id = id;
        this.tenedores = tenedores;
    }

    // Método que simula el comportamiento del filósofo
    public void run() {
        try {
            while (true) {
                pensar();            // El filósofo piensa
                tenedores.acquire(2); // Toma dos tenedores
                comer();             // El filósofo come
                tenedores.release(2); // Suelta los dos tenedores
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simulación de la acción de pensar
    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    // Simulación de la acción de comer
    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep((long) (Math.random() * 1000));
    }
}

public class FilosofosYTenedores {
    public static void main(String[] args) {
        Semaphore tenedores = new Semaphore(4); // Hay 5 filósofos, pero solo 4 tenedores

        // Iniciar los 5 filósofos, cada uno intentando acceder a los tenedores
        for (int i = 0; i < 5; i++) {
            new Filosofo(i, tenedores).start();
        }
    }
}

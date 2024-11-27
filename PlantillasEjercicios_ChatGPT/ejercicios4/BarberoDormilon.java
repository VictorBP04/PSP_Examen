package ejercicios4;

import java.util.concurrent.Semaphore;

public class BarberoDormilon {
    static Semaphore sillasDisponibles = new Semaphore(3); // 3 sillas en la sala de espera
    static Semaphore barbero = new Semaphore(0); // Barbero comienza dormido
    static Semaphore sillaBarbero = new Semaphore(1); // Solo un cliente puede estar en la silla del barbero

    static class Cliente implements Runnable {
        @Override
        public void run() {
            try {
                if (sillasDisponibles.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " se sienta en la sala de espera.");
                    barbero.release(); // Despierta al barbero
                    sillaBarbero.acquire(); // Espera a ser atendido
                    System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " se va porque no hay sillas.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Barbero implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    barbero.acquire(); // Espera a que un cliente lo despierte
                    System.out.println("Barbero atiende a un cliente.");
                    Thread.sleep(2000); // Tiempo de corte
                    sillaBarbero.release(); // Termina el corte
                    sillasDisponibles.release(); // Libera una silla de espera
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread barberoThread = new Thread(new Barbero());
        barberoThread.start();

        for (int i = 1; i <= 10; i++) {
            new Thread(new Cliente(), "Cliente " + i).start();
            try {
                Thread.sleep(500); // Llegada escalonada de clientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

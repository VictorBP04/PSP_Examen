package ejercicios4;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class Aeropuerto {
    static Semaphore pista = new Semaphore(2); // 2 pistas disponibles
    static PriorityBlockingQueue<String> colaAterrizaje = new PriorityBlockingQueue<>();
    static PriorityBlockingQueue<String> colaDespegue = new PriorityBlockingQueue<>();

    static class Avion implements Runnable {
        private String tipoOperacion;

        public Avion(String tipoOperacion) {
            this.tipoOperacion = tipoOperacion;
        }

        @Override
        public void run() {
            try {
                if (tipoOperacion.equals("aterrizar")) {
                    colaAterrizaje.put(Thread.currentThread().getName() + " solicita aterrizaje.");
                } else {
                    colaDespegue.put(Thread.currentThread().getName() + " solicita despegue.");
                }
                pista.acquire(); // Espera a que una pista esté disponible
                System.out.println(Thread.currentThread().getName() + " usa la pista para " + tipoOperacion + ".");
                Thread.sleep(3000); // Simula el tiempo de uso de la pista
                pista.release(); // Libera la pista
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            String operacion = (i % 2 == 0) ? "despegar" : "aterrizar";
            new Thread(new Avion(operacion), "Avión " + i).start();
        }
    }
}

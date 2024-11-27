package ejercicios5;

import java.util.concurrent.Semaphore;

public class EstacionCarga {
    static Semaphore puntosCarga = new Semaphore(4); // 4 puntos de carga disponibles.

    static class Vehiculo implements Runnable {
        private int id;

        public Vehiculo(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Vehículo " + id + " espera para cargar.");
                puntosCarga.acquire(); // Reserva un punto de carga.

                System.out.println("Vehículo " + id + " está cargando.");
                Thread.sleep((long) (Math.random() * 5000)); // Simula el tiempo de carga.

                System.out.println("Vehículo " + id + " terminó de cargar y se va.");
                puntosCarga.release(); // Libera el punto de carga.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Vehiculo(i), "Vehículo " + i).start();
        }
    }
}


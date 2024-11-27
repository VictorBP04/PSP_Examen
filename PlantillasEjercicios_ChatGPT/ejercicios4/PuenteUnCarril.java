package ejercicios4;

import java.util.concurrent.Semaphore;

public class PuenteUnCarril {
    static Semaphore puente = new Semaphore(1); // Solo un vehículo puede cruzar el puente
    static Semaphore mutex = new Semaphore(1); // Controla la alternancia de direcciones
    static int vehiculosEsperandoNorte = 0, vehiculosEsperandoSur = 0;

    static class Vehiculo implements Runnable {
        private String direccion;

        public Vehiculo(String direccion) {
            this.direccion = direccion;
        }

        @Override
        public void run() {
            try {
                if (direccion.equals("Norte")) {
                    mutex.acquire();
                    vehiculosEsperandoNorte++;
                    mutex.release();
                } else {
                    mutex.acquire();
                    vehiculosEsperandoSur++;
                    mutex.release();
                }

                puente.acquire(); // Espera para cruzar el puente
                System.out.println(Thread.currentThread().getName() + " cruza desde " + direccion);
                Thread.sleep(2000); // Simula el tiempo de cruce
                puente.release(); // Libera el puente

                mutex.acquire();
                if (direccion.equals("Norte")) vehiculosEsperandoNorte--;
                else vehiculosEsperandoSur--;
                mutex.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            String direccion = (i % 2 == 0) ? "Sur" : "Norte";
            new Thread(new Vehiculo(direccion), "Vehículo " + i).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

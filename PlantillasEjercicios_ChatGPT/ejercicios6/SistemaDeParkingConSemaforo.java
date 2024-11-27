package ejercicios6;

//Simula un sistema de estacionamiento con un número limitado de plazas. Los coches intentan entrar al parking, pero si está lleno, deben esperar.

import java.util.concurrent.Semaphore;

class Parking {
    private final Semaphore plazasDisponibles;

    public Parking(int numeroPlazas) {
        plazasDisponibles = new Semaphore(numeroPlazas);  // Inicializa el semáforo con el número de plazas
    }

    public void entrarAlParking(String coche) throws InterruptedException {
        if (plazasDisponibles.tryAcquire()) {  // Si hay plaza disponible
            System.out.println(coche + " ha entrado al parking.");
            Thread.sleep(2000);  // Simula el tiempo estacionado
            plazasDisponibles.release();  // Libera la plaza
            System.out.println(coche + " ha salido del parking.");
        } else {
            System.out.println(coche + " no pudo entrar, parking lleno.");
        }
    }
}

public class SistemaDeParkingConSemaforo {
    public static void main(String[] args) {
        Parking parking = new Parking(3);  // Parking con 3 plazas disponibles
        Runnable coche = () -> {
            try {
                parking.entrarAlParking(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y arrancar varios coches
        for (int i = 0; i < 10; i++) {
            new Thread(coche, "Coche " + i).start();
        }
    }
}

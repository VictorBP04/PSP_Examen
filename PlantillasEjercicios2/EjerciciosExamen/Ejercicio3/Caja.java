package EjerciciosExamen.Ejercicio3;

import java.util.concurrent.Semaphore;

public class Caja {
    private int totalVentas;
    private Semaphore semaphore;

    public Caja() {
        this.totalVentas = 0;
        this.semaphore = new Semaphore(1); // Solo una persona a la vez en la caja
    }

    public void realizarCompra(int cantidad) {
        try {
            semaphore.acquire(); // Esperar si la caja está ocupada
            totalVentas += cantidad; // Realizar la compra
            Thread.sleep(100); // Simular tiempo de atención
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Liberar la caja
        }
    }

    public int getTotalVentas() {
        return totalVentas;
    }
}

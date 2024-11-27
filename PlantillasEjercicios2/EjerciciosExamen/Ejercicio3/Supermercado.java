package EjerciciosExamen.Ejercicio3;

public class Supermercado {
    public static void main(String[] args) {
        final int NUM_CLIENTES = 10;
        Caja[] cajas = {new Caja(), new Caja(), new Caja()}; // Tres cajas
        Cliente[] clientes = new Cliente[NUM_CLIENTES];

        // Crear y empezar los hilos de los clientes
        for (int i = 0; i < NUM_CLIENTES; i++) {
            int cajaSeleccionada = (int) (Math.random() * 3); // Elegir caja aleatoria
            clientes[i] = new Cliente(cajas[cajaSeleccionada]);
            clientes[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Imprimir los totales de cada caja
        int totalGeneral = 0;
        for (int i = 0; i < cajas.length; i++) {
            int totalCaja = cajas[i].getTotalVentas();
            System.out.println("Total en Caja " + (i + 1) + ": " + totalCaja + "€");
            totalGeneral += totalCaja;
        }
        System.out.println("Total de ventas del día: " + totalGeneral + "€");
    }
}

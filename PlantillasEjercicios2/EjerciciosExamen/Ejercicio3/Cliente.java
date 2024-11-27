package EjerciciosExamen.Ejercicio3;

import java.util.Random;

public class Cliente extends Thread {
    private static int idCounter = 0;
    private int id;
    private Caja caja;

    public Cliente(Caja caja) {
        this.caja = caja;
        this.id = ++idCounter; // Asignar un ID único a cada cliente
    }

    @Override
    public void run() {
        Random random = new Random();
        int compra = random.nextInt(100) + 1; // Compra aleatoria entre 1 y 100€

        System.out.println("Voy a comprar " + compra + "€ - Cliente " + id);
        caja.realizarCompra(compra);
        System.out.println("He terminado de comprar - Cliente " + id);
    }
}

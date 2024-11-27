package ejercicios2;

class Cliente extends Thread {
    private final Cajera cajera;

    public Cliente(Cajera cajera) {
        this.cajera = cajera;
    }

    // Método que se ejecuta cuando el cliente comienza
    public void run() {
        System.out.println(Thread.currentThread().getName() + " va a comprar.");
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simula el tiempo de compra
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " va a pagar.");
        cajera.atender(this); // Llamar correctamente al método atender
    }
}

class Cajera extends Thread {
    // Método que simula la atención de un cliente
    public void atender(Cliente cliente) {
        System.out.println("Cajera atendiendo a " + cliente.getName());
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simula el tiempo de cobro
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cajera ha cobrado a " + cliente.getName());
    }

    @Override
    public void run() {
        // La Cajera puede hacer otras cosas en su hilo, si es necesario
        System.out.println("Cajera lista para atender.");
    }
}

public class CajeraYClientesConcurrentes {
    public static void main(String[] args) {
        Cajera cajera = new Cajera(); // Crear la instancia de Cajera con mayúscula
        cajera.start(); // Iniciar el hilo de la cajera

        // Crear y lanzar 20 hilos de clientes
        for (int i = 0; i < 20; i++) {
            new Cliente(cajera).start(); // Crear y empezar un nuevo hilo de Cliente
        }
    }
}

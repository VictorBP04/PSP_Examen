package ejercicios2;

class Barberia {
    private boolean hayCliente = false;

    // Método sincronizado para cortar el pelo al cliente
    public synchronized void cortarPelo() throws InterruptedException {
        while (!hayCliente) {
            wait(); // El barbero espera si no hay clientes
        }
        System.out.println("Cortando el pelo al cliente...");
        hayCliente = false;
        notifyAll(); // Notificar que ha terminado
    }

    // Método sincronizado para que un cliente llegue
    public synchronized void llegarCliente() {
        System.out.println("Cliente llegó.");
        hayCliente = true;
        notifyAll(); // Notificar al barbero que hay un cliente
    }
}

public class ProblemaBarberoDormilon {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();

        // Hilo que representa al barbero
        Thread barbero = new Thread(() -> {
            try {
                while (true) {
                    barberia.cortarPelo(); // El barbero corta el pelo
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo que representa al cliente que llega
        Thread cliente = new Thread(() -> {
            barberia.llegarCliente(); // El cliente llega a la barbería
        });

        barbero.start(); // Iniciar el hilo del barbero
        cliente.start(); // Iniciar el hilo del cliente
    }
}

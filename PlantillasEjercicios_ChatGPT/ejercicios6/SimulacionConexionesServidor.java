package ejercicios6;

//En un sistema de servidores y clientes, varios clientes intentan acceder a un servidor para realizar una solicitud. Los servidores tienen un número limitado de conexiones simultáneas. Implementa un sistema que gestione las solicitudes de los clientes usando monitores, asegurando que no se exceda el número máximo de conexiones simultáneas.
//Hay varios servidores con capacidad limitada.
//Los clientes deben esperar si no hay servidores disponibles.
//Los servidores deben atender las solicitudes de los clientes de forma concurrente, pero respetando el límite de conexiones.

class Servidor {
    private final int maxConexiones;
    private int conexionesActivas = 0;

    public Servidor(int maxConexiones) {
        this.maxConexiones = maxConexiones;
    }

    public synchronized void atenderSolicitud(String cliente) throws InterruptedException {
        while (conexionesActivas >= maxConexiones) {
            System.out.println("Servidor ocupado, esperando: " + cliente);
            wait();  // Espera si no hay espacio para nuevas conexiones
        }
        conexionesActivas++;
        System.out.println("Conectando cliente: " + cliente);
        Thread.sleep(1000);  // Simula la atención del cliente
        conexionesActivas--;
        notifyAll();  // Notifica que un servidor está disponible
    }
}

public class SimulacionConexionesServidor {
    public static void main(String[] args) {
        Servidor servidor = new Servidor(3);  // Máximo 3 conexiones
        Runnable cliente = () -> {
            try {
                servidor.atenderSolicitud(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y arrancar varios hilos (clientes)
        for (int i = 0; i < 10; i++) {
            new Thread(cliente, "Cliente " + i).start();
        }
    }
}

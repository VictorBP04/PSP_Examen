package ejercicios2;

import java.util.concurrent.Semaphore;

class Buffer {
    private int contenido;
    private Semaphore vacio = new Semaphore(1);  // Semáforo para verificar si el buffer está vacío
    private Semaphore lleno = new Semaphore(0);   // Semáforo para verificar si el buffer está lleno

    // Método de producción (agregar un valor al buffer)
    public void producir(int valor) throws InterruptedException {
        vacio.acquire();  // Espera hasta que el buffer esté vacío para producir
        contenido = valor;
        System.out.println("Producido: " + contenido);
        lleno.release();  // Notifica al consumidor que hay un nuevo valor disponible
    }

    // Método de consumo (leer y eliminar un valor del buffer)
    public int consumir() throws InterruptedException {
        lleno.acquire();  // Espera hasta que el buffer esté lleno para consumir
        int valor = contenido;
        System.out.println("Consumido: " + valor);
        vacio.release();  // Notifica al productor que el buffer está disponible
        return valor;
    }
}

public class ProductorConsumidorConSemaforos {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Hilo productor que produce 5 valores
        Thread productor = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.producir(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo consumidor que consume 5 valores
        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.consumir();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        productor.start(); // Iniciar hilo productor
        consumidor.start(); // Iniciar hilo consumidor
    }
}

package ejercicios2;

import java.util.concurrent.Semaphore;

class Contador {
    private int cuenta = 0;
    private Semaphore semaforo = new Semaphore(1); // Semáforo binario para controlar el acceso a la cuenta

    // Método para incrementar la cuenta de manera sincronizada
    public void incrementar() throws InterruptedException {
        semaforo.acquire();  // Adquirir el semáforo antes de modificar la cuenta
        cuenta++;  // Incrementar la cuenta
        semaforo.release();  // Liberar el semáforo
    }

    // Método para obtener el valor de la cuenta
    public int obtenerCuenta() {
        return cuenta;
    }
}

public class ContadorConSemaforo {
    public static void main(String[] args) {
        Contador contador = new Contador();

        // Hilo 1 que incrementa la cuenta 1000 veces
        Thread hilo1 = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    contador.incrementar();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo 2 que también incrementa la cuenta 1000 veces
        Thread hilo2 = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    contador.incrementar();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        hilo1.start(); // Iniciar el primer hilo
        hilo2.start(); // Iniciar el segundo hilo

        try {
            hilo1.join(); // Esperar a que el hilo 1 termine
            hilo2.join(); // Esperar a que el hilo 2 termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cuenta final: " + contador.obtenerCuenta()); // Mostrar el valor final de la cuenta
    }
}

package ejercicios2;

class Secuencia implements Runnable {
    private static int contador = 1;  // Contador estático compartido por todos los hilos

    public void run() {
        while (contador <= 20) {
            // Se utiliza la sincronización de la clase Secuencia
            synchronized (Secuencia.class) {
                if (contador <= 20) {
                    System.out.println(contador++);  // Imprime el contador y lo incrementa
                }
            }
            try {
                Thread.sleep(100);  // Controla la velocidad de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SecuenciaNumerosConcurrente {
    public static void main(String[] args) {
        // Crear dos hilos para imprimir la secuencia de números
        Thread hilo1 = new Thread(new Secuencia());
        Thread hilo2 = new Thread(new Secuencia());

        hilo1.start();  // Inicia el primer hilo
        hilo2.start();  // Inicia el segundo hilo

        try {
            hilo1.join();  // Espera a que termine el primer hilo
            hilo2.join();  // Espera a que termine el segundo hilo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

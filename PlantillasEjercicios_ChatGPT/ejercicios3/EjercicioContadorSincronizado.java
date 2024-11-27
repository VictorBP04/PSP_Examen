package ejercicios3;

class ContadorSincronizado {
    private int cuenta = 0;

    // Método sincronizado para incrementar la cuenta
    public synchronized void incrementar() {
        cuenta++;
    }

    // Método sincronizado para obtener el valor de la cuenta
    public synchronized int obtenerCuenta() {
        return cuenta;
    }
}

class HiloContadorSincronizado extends Thread {
    private final ContadorSincronizado contador;

    public HiloContadorSincronizado(ContadorSincronizado contador) {
        this.contador = contador;
    }

    public void run() {
        // Cada hilo incrementa la cuenta 1000 veces
        for (int i = 0; i < 1000; i++) {
            contador.incrementar();
        }
    }
}

public class EjercicioContadorSincronizado {
    public static void main(String[] args) throws InterruptedException {
        ContadorSincronizado contador = new ContadorSincronizado();  // Se crea el objeto contador
        HiloContadorSincronizado[] hilos = new HiloContadorSincronizado[10];  // Creamos 10 hilos

        // Iniciamos los hilos
        for (int i = 0; i < 10; i++) {
            hilos[i] = new HiloContadorSincronizado(contador);
            hilos[i].start();
        }

        // Esperamos a que todos los hilos terminen su ejecución
        for (HiloContadorSincronizado hilo : hilos) {
            hilo.join();
        }

        // Mostramos el valor final del contador
        System.out.println("Valor final del contador: " + contador.obtenerCuenta());
    }
}

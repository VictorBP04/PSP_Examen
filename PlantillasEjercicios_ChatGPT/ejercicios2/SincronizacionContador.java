package ejercicios2;

class MonitorContador {
    private int contador = 0;

    // Método sincronizado para incrementar el contador
    public synchronized void incrementar() {
        contador++;
    }

    // Método sincronizado para obtener el valor actual del contador
    public synchronized int obtenerContador() {
        return contador;
    }
}

public class SincronizacionContador {
    public static void main(String[] args) {
        MonitorContador monitor = new MonitorContador();

        // Hilo 1 que incrementa el contador 1000 veces
        Thread hilo1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                monitor.incrementar();
            }
        });

        // Hilo 2 que también incrementa el contador 1000 veces
        Thread hilo2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                monitor.incrementar();
            }
        });

        // Iniciar ambos hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que ambos hilos terminen antes de mostrar el resultado
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar el contador final
        System.out.println("Contador final: " + monitor.obtenerContador());
    }
}

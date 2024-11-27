package ejercicios6;

//Implementa un sistema de logging en el que varios hilos intentan escribir mensajes en un archivo de log. Sin embargo, solo un hilo puede escribir en el archivo a la vez. Utiliza monitores para sincronizar el acceso a la operación de escritura.

//Los hilos deben escribir mensajes de forma concurrente en el archivo de log.
//Si un hilo está escribiendo, los demás deben esperar a que termine.

class Log {
    private final Object monitor = new Object();

    public void escribirLog(String mensaje) {
        synchronized (monitor) {
            System.out.println("Escribiendo en el log: " + mensaje);
            try {
                Thread.sleep(500);  // Simula la escritura en el archivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SistemaLog {
    public static void main(String[] args) {
        Log log = new Log();
        Runnable tarea = () -> {
            for (int i = 0; i < 5; i++) {
                log.escribirLog(Thread.currentThread().getName() + " - Mensaje " + i);
            }
        };

        // Crear y arrancar varios hilos para escribir en el log
        for (int i = 0; i < 3; i++) {
            new Thread(tarea, "Hilo " + i).start();
        }
    }
}

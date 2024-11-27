package ejercicios5;

import java.util.concurrent.Semaphore;

public class CopiaSeguridad {
    // Cada archivo tiene un "candado" que permite o bloquea el acceso durante la copia.
    static Semaphore[] archivos = { new Semaphore(1), new Semaphore(1), new Semaphore(1) };

    static class Proceso implements Runnable {
        private int idArchivo; // Archivo que este proceso debe copiar.

        public Proceso(int idArchivo) {
            this.idArchivo = idArchivo;
        }

        @Override
        public void run() {
            try {
                archivos[idArchivo].acquire(); // Bloquea el acceso al archivo.
                System.out.println(Thread.currentThread().getName() + " comienza copia del archivo " + idArchivo);

                Thread.sleep(2000); // Simula la duración de la copia.

                System.out.println(Thread.currentThread().getName() + " finaliza copia del archivo " + idArchivo);
                archivos[idArchivo].release(); // Libera el archivo para otros procesos.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Se asignan diferentes procesos a los archivos 0, 1 y 2.
        for (int i = 1; i <= 6; i++) {
            int archivo = i % 3; // Reparto circular entre los 3 archivos.
            new Thread(new Proceso(archivo), "Proceso " + i).start();
        }
    }
}

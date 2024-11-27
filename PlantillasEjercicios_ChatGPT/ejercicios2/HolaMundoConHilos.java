package ejercicios2;

class HolaMundo implements Runnable {
    private final int id;

    public HolaMundo(int id) {
        this.id = id;
    }

    // Método que se ejecuta cuando el hilo comienza
    public void run() {
        try {
            Thread.sleep(id * 100); // Espera proporcional al identificador
            System.out.println("Hola Mundo desde el hilo " + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class HolaMundoConHilos {
    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];  // Creamos un arreglo de 5 hilos

        // Creamos y lanzamos 5 hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new HolaMundo(i));
            hilos[i].start();
        }

        // Esperamos a que todos los hilos terminen antes de finalizar el programa
        for (Thread hilo : hilos) {
            try {
                hilo.join();  // Asegura que el hilo principal espere a que cada hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimir mensaje final cuando todos los hilos hayan terminado
        System.out.println("Fin de ejecución de todos los hilos.");
    }
}

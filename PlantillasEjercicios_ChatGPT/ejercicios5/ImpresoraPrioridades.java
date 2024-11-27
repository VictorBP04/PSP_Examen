package ejercicios5;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

class Trabajo implements Comparable<Trabajo> {
    private int prioridad;
    private String nombre;

    public Trabajo(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Trabajo o) {
        return Integer.compare(o.getPrioridad(), this.prioridad); // Prioridad más alta primero.
    }
}

public class ImpresoraPrioridades {
    static Semaphore impresora = new Semaphore(1);
    static PriorityBlockingQueue<Trabajo> cola = new PriorityBlockingQueue<>();

    static class ProcesoImpresion implements Runnable {
        @Override
        public void run() {
            try {
                impresora.acquire(); // Reserva la impresora.
                Trabajo trabajo = cola.take(); // Toma el siguiente trabajo de la cola.

                System.out.println("Imprimiendo " + trabajo.getNombre() + " con prioridad " + trabajo.getPrioridad());
                Thread.sleep(3000); // Simula la impresión.

                System.out.println(trabajo.getNombre() + " finalizado.");
                impresora.release(); // Libera la impresora.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cola.add(new Trabajo(1, "Documento A"));
        cola.add(new Trabajo(3, "Documento B"));
        cola.add(new Trabajo(2, "Documento C"));

        for (int i = 0; i < 3; i++) {
            new Thread(new ProcesoImpresion()).start();
        }
    }
}


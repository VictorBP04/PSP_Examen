package ejercicios3;

import java.util.LinkedList;
import java.util.Queue;

class BufferCircular {
    private final Queue<Integer> buffer;
    private final int capacidad;
    private int contador = 0;

    public BufferCircular(int capacidad) {
        this.buffer = new LinkedList<>();
        this.capacidad = capacidad;
    }

    public synchronized void producir(int valor) throws InterruptedException {
        while (contador == capacidad) {
            wait(); // Espera si el buffer está lleno
        }
        buffer.add(valor);
        contador++;
        notifyAll(); // Notifica a los consumidores
    }

    public synchronized int consumir() throws InterruptedException {
        while (contador == 0) {
            wait(); // Espera si el buffer está vacío
        }
        int valor = buffer.remove();
        contador--;
        notifyAll(); // Notifica a los productores
        return valor;
    }
}

class HiloProductor extends Thread {
    private final BufferCircular buffer;

    public HiloProductor(BufferCircular buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.producir(i);
                System.out.println("Producido: " + i);
                Thread.sleep(100); // Simula tiempo de producción
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class HiloConsumidor extends Thread {
    private final BufferCircular buffer;

    public HiloConsumidor(BufferCircular buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int valor = buffer.consumir();
                System.out.println("Consumido: " + valor);
                Thread.sleep(150); // Simula tiempo de consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class SimulacionProductorConsumidor {
    public static void main(String[] args) {
        BufferCircular buffer = new BufferCircular(5); // Capacidad del buffer
        HiloProductor productor = new HiloProductor(buffer);
        HiloConsumidor consumidor = new HiloConsumidor(buffer);

        productor.start();
        consumidor.start();
    }
}

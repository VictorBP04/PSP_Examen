package ejercicios3;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacidad;
    private final Object lock = new Object();

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
    }

    public void producir(int valor) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacidad) {
                lock.wait(); // Espera a que haya espacio
            }
            queue.add(valor);
            System.out.println("Producido: " + valor);
            lock.notifyAll(); // Notifica a los consumidores
        }
    }

    public int consumir() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait(); // Espera a que haya elementos
            }
            int valor = queue.poll();
            System.out.println("Consumido: " + valor);
            lock.notifyAll(); // Notifica a los productores
            return valor;
        }
    }
}

class Productor extends Thread {
    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.producir(i);
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.consumir();
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProductorConsumidorBuffer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Productor productor = new Productor(buffer); // Usar 'Productor' con mayúscula
        Consumidor consumidor = new Consumidor(buffer); // Usar 'Consumidor' con mayúscula

        productor.start();
        consumidor.start();
    }
}

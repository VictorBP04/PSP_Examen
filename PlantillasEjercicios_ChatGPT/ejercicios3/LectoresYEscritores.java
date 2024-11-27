package ejercicios3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class RecursoCompartido {
    private int lectores = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition puedeEscribir = lock.newCondition();

    public void leer() throws InterruptedException {
        lock.lock();
        try {
            lectores++;
            System.out.println("Leyendo... Lectores activos: " + lectores);
        } finally {
            lock.unlock();
        }
    }

    public void dejarLeer() {
        lock.lock();
        try {
            lectores--;
            System.out.println("Dejando de leer... Lectores activos: " + lectores);
            if (lectores == 0) {
                puedeEscribir.signal(); // Notifica a los escritores
            }
        } finally {
            lock.unlock();
        }
    }

    public void escribir() throws InterruptedException {
        lock.lock();
        try {
            while (lectores > 0) {
                puedeEscribir.await(); // Espera a que no haya lectores
            }
            System.out.println("Escribiendo...");
            Thread.sleep(1000); // Simula tiempo de escritura
        } finally {
            lock.unlock();
        }
    }
}

class Lector extends Thread {
    private final RecursoCompartido recurso;

    public Lector(RecursoCompartido recurso) {
        this.recurso = recurso;
    }

    public void run() {
        try {
            recurso.leer();
            Thread.sleep(500); // Simula tiempo de lectura
            recurso.dejarLeer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Escritor extends Thread {
    private final RecursoCompartido recurso;

    public Escritor(RecursoCompartido recurso) {
        this.recurso = recurso;
    }

    public void run() {
        try {
            recurso.escribir();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LectoresYEscritores {
    public static void main(String[] args) {
        RecursoCompartido recurso = new RecursoCompartido();

        // Crear hilos de lectores
        for (int i = 0; i < 5; i++) {
            new Lector(recurso).start();
        }

        // Crear hilos de escritores
        for (int i = 0; i < 2; i++) {
            new Escritor(recurso).start();
        }
    }
}

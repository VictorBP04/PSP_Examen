package ejercicios1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Problema de Lectores y Escritores: Simula acceso concurrente con prioridad configurable.
 */
public class ReadersWriters {
    static class SharedResource {
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read(int readerId) {
            lock.readLock().lock();
            try {
                System.out.println("Lector " + readerId + " está leyendo.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Lector " + readerId + " terminó de leer.");
                lock.readLock().unlock();
            }
        }

        public void write(int writerId) {
            lock.writeLock().lock();
            try {
                System.out.println("Escritor " + writerId + " está escribiendo.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Escritor " + writerId + " terminó de escribir.");
                lock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Crear lectores
        for (int i = 1; i <= 3; i++) {
            int readerId = i;
            new Thread(() -> resource.read(readerId)).start();
        }

        // Crear escritores
        for (int i = 1; i <= 2; i++) {
            int writerId = i;
            new Thread(() -> resource.write(writerId)).start();
        }
    }
}

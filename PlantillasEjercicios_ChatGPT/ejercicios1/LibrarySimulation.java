package ejercicios1;

import java.util.concurrent.Semaphore;

/**
 * Simulación de biblioteca con acceso concurrente para tomar y devolver libros.
 */
public class LibrarySimulation {
    static class Library {
        private final Semaphore books;

        public Library(int totalBooks) {
            this.books = new Semaphore(totalBooks);
        }

        public void borrowBook(String reader) throws InterruptedException {
            books.acquire();
            System.out.println(reader + " tomó un libro.");
            Thread.sleep(2000);
            returnBook(reader);
        }

        public void returnBook(String reader) {
            books.release();
            System.out.println(reader + " devolvió un libro.");
        }
    }

    public static void main(String[] args) {
        Library library = new Library(3);

        Runnable readerTask = () -> {
            String reader = Thread.currentThread().getName();
            try {
                library.borrowBook(reader);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(readerTask, "Lector " + i).start();
        }
    }
}

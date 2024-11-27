package ejercicios6;

//Simula una biblioteca con varios libros y usuarios que intentan tomar prestados los libros. Solo un usuario puede tomar un libro prestado a la vez. Los usuarios deben esperar si el libro está siendo utilizado.

class Biblioteca {
    private final boolean[] libros;
    private final Object monitor = new Object();  // Monitor para sincronizar

    public Biblioteca(int numLibros) {
        libros = new boolean[numLibros];
    }

    public void pedirPrestado(int libroId, String usuario) throws InterruptedException {
        synchronized (monitor) {
            while (libros[libroId]) {  // Si el libro está prestado, espera
                System.out.println(usuario + " espera el libro " + libroId);
                monitor.wait();
            }
            libros[libroId] = true;
            System.out.println(usuario + " ha pedido prestado el libro " + libroId);
        }
    }

    public void devolver(int libroId, String usuario) {
        synchronized (monitor) {
            libros[libroId] = false;
            System.out.println(usuario + " ha devuelto el libro " + libroId);
            monitor.notifyAll();  // Notifica a los demás usuarios
        }
    }
}

public class SistemaBibliotecaConMonitores {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(3);  // 3 libros disponibles
        Runnable usuario = () -> {
            try {
                biblioteca.pedirPrestado(1, Thread.currentThread().getName());
                Thread.sleep(2000);  // Simula la lectura del libro
                biblioteca.devolver(1, Thread.currentThread().getName());
                Thread.sleep(2000);  // Simula la lectura del libro
                biblioteca.devolver(1, Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y arrancar varios usuarios
        new Thread(usuario, "Usuario 1").start();
        new Thread(usuario, "Usuario 2").start();
        new Thread(usuario, "Usuario 3").start();
    }
}


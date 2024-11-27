package ejercicios6;

//Implementa un sistema en el que varios hilos deben realizar tareas en paralelo y luego esperar hasta que todos los hilos terminen antes de continuar con la siguiente fase.

class Barrera {
    private final int totalHilos;
    private int contador = 0;

    public Barrera(int totalHilos) {
        this.totalHilos = totalHilos;
    }

    public synchronized void esperar() throws InterruptedException {
        contador++;
        if (contador == totalHilos) {
            notifyAll();
        } else {
            wait();
        }
    }
}

class Tarea implements Runnable {
    private final Barrera barrera;

    public Tarea(Barrera barrera) {
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " está trabajando.");
            Thread.sleep(1000);
            barrera.esperar();
            System.out.println(Thread.currentThread().getName() + " ha terminado.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SincronizacionTareasConBarrera {
    public static void main(String[] args) {
        Barrera barrera = new Barrera(3);  // Esperar hasta que 3 hilos terminen
        Runnable tarea = new Tarea(barrera);

        // Crear y arrancar varios hilos
        new Thread(tarea, "Hilo 1").start();
        new Thread(tarea, "Hilo 2").start();
        new Thread(tarea, "Hilo 3").start();
    }
}

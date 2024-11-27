package EjerciciosExamen.Ejercicio2;

import java.util.concurrent.CountDownLatch;

public class Alumno extends Thread {
    private String nombre;
    private CountDownLatch latch;

    public Alumno(String nombre, CountDownLatch latch) {
        this.nombre = nombre;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Espera a que el profesor salude
            latch.await();
            // Saludo del alumno
            System.out.println("Morgen Ich bin " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
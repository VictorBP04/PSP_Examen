package EjerciciosExamen.Ejercicio2;

import java.util.concurrent.CountDownLatch;

public class Clase {
    public static void main(String[] args) {
        int numAlumnos = 10;
        CountDownLatch latch = new CountDownLatch(1);

        // Crear y empezar los hilos de los alumnos
        Alumno[] alumnos = new Alumno[numAlumnos];
        String[] nombres = {"Klaus", "Wilfred", "Anna", "Maria", "Hans", "Peter", "Julia", "Sofia", "Lukas", "Max"};

        for (int i = 0; i < numAlumnos; i++) {
            alumnos[i] = new Alumno(nombres[i], latch);
            alumnos[i].start();
        }

        // Crear y empezar el hilo del profesor
        Profesor profesor = new Profesor(latch);
        profesor.start();

        // Esperar a que todos los hilos terminen
        try {
            profesor.join();
            for (Alumno alumno : alumnos) {
                alumno.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

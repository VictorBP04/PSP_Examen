package EjerciciosExamen.Ejercicio2;

import java.util.concurrent.CountDownLatch;

public class Profesor extends Thread {
    private CountDownLatch latch;

    public Profesor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        // Saludo del profesor
        System.out.println("Guten Morgen die Klasse!");
        // Libera a los alumnos para que saluden
        latch.countDown();
        // Pregunta final
        System.out.println("Wie alt bist du?");
    }
}
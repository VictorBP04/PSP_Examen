package ejercicios5;

import java.util.concurrent.Semaphore;

public class ControlEvento {
    // Capacidad máxima del evento es de 4 personas a la vez.
    static Semaphore capacidad = new Semaphore(4);

    static class Persona implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " espera para entrar.");
                capacidad.acquire(); // Espera hasta que haya espacio.

                System.out.println(Thread.currentThread().getName() + " entra al evento.");
                Thread.sleep(3000); // Simula el tiempo que la persona pasa dentro.

                System.out.println(Thread.currentThread().getName() + " sale del evento.");
                capacidad.release(); // Libera el lugar para otra persona.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Persona(), "Persona " + i).start();
        }
    }
}


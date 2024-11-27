package ejercicios1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Ejemplo de un hilo que calcula pagos periódicos.
 */
public class PeriodicPayment {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask calculatePayment = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Calculando salarios a las " + System.currentTimeMillis());
            }
        };

        // Programar el cálculo cada 2 segundos
        timer.scheduleAtFixedRate(calculatePayment, 0, 2000);

        // Cancelar después de 10 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Deteniendo el cálculo de salarios.");
                timer.cancel();
            }
        }, 10000);
    }
}

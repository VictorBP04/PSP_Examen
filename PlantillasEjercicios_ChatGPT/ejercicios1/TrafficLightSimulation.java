package ejercicios1;

/**
 * Simulación de semáforo utilizando hilos para cambiar las luces de tráfico.
 */
public class TrafficLightSimulation {
    static class TrafficLight extends Thread {
        private final String[] states = {"ROJO", "VERDE", "AMARILLO"};
        private int currentState = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Luz actual: " + states[currentState]);
                    switch (currentState) {
                        case 0 -> Thread.sleep(3000); // ROJO
                        case 1 -> Thread.sleep(5000); // VERDE
                        case 2 -> Thread.sleep(2000); // AMARILLO
                    }
                    currentState = (currentState + 1) % states.length;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        light.start();
    }
}

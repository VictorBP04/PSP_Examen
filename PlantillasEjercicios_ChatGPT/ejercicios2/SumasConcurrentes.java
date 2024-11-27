package ejercicios2;

class SumaPares implements Runnable {
    private int suma = 0;

    // Método run que realiza la suma de números pares entre 1 y 100
    public void run() {
        for (int i = 2; i <= 100; i += 2) {
            suma += i;
        }
        System.out.println("Suma de pares: " + suma); // Muestra el resultado de la suma de pares
    }
}

class SumaImpares implements Runnable {
    private int suma = 0;

    // Método run que realiza la suma de números impares entre 1 y 99
    public void run() {
        for (int i = 1; i < 100; i += 2) {
            suma += i;
        }
        System.out.println("Suma de impares: " + suma); // Muestra el resultado de la suma de impares
    }
}

class SumaTermina2o3 implements Runnable {
    private int suma = 0;

    // Método run que realiza la suma de números que terminan en 2 o 3 entre 0 y 100
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 10 == 2 || i % 10 == 3) { // Comprueba si el número termina en 2 o 3
                suma += i;
            }
        }
        System.out.println("Suma de números que terminan en 2 o 3: " + suma); // Muestra el resultado
    }
}

public class SumasConcurrentes {
    public static void main(String[] args) {
        // Creamos los hilos para realizar las sumas
        Thread hiloPares = new Thread(new SumaPares());
        Thread hiloImpares = new Thread(new SumaImpares());
        Thread hiloTermina2o3 = new Thread(new SumaTermina2o3());

        // Iniciamos los hilos
        hiloPares.start();
        hiloImpares.start();
        hiloTermina2o3.start();

        // Esperamos a que todos los hilos terminen antes de continuar
        try {
            hiloPares.join();
            hiloImpares.join();
            hiloTermina2o3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mensaje final
        System.out.println("Fin de la suma.");
    }
}

package ejercicios4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorWeb {
    static class Cliente implements Runnable {
        private int idCliente;

        public Cliente(int id) {
            this.idCliente = id;
        }

        @Override
        public void run() {
            System.out.println("Cliente " + idCliente + " conectado.");
            try {
                Thread.sleep((int) (Math.random() * 3000)); // Simula el tiempo de procesamiento
                System.out.println("Cliente " + idCliente + " finaliza.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3); // 3 conexiones simultáneas
        for (int i = 1; i <= 10; i++) {
            pool.execute(new Cliente(i));
        }
        pool.shutdown();
    }
}

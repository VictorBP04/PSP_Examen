package ejercicios4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeneradorNumeros {
    static List<Integer> listaNumeros = Collections.synchronizedList(new ArrayList<>());

    static class Generador implements Runnable {
        @Override
        public void run() {
            int numero = (int) (Math.random() * 100);
            listaNumeros.add(numero);
            System.out.println(Thread.currentThread().getName() + " generó el número: " + numero);
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5); // 5 hilos

        for (int i = 0; i < 10; i++) {
            pool.execute(new Generador());
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        Collections.sort(listaNumeros);
        System.out.println("Lista ordenada: " + listaNumeros);
    }
}

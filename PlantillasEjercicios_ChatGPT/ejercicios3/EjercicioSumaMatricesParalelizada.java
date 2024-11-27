package ejercicios3;

class SumaMatricesParalelizada extends Thread {
    private final int[][] matrizA;
    private final int[][] matrizB;
    private final int[][] resultado;
    private final int inicio;
    private final int fin;

    public SumaMatricesParalelizada(int[][] matrizA, int[][] matrizB, int[][] resultado, int inicio, int fin) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultado = resultado;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        for (int i = inicio; i < fin; i++) {
            for (int j = 0; j < matrizA[i].length; j++) {
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
    }
}

public class EjercicioSumaMatricesParalelizada {
    public static void main(String[] args) throws InterruptedException {
        int filas = 4;
        int columnas = 4;
        int[][] matrizA = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] matrizB = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };
        int[][] resultado = new int[filas][columnas];

        int hilos = 2; // Número de hilos
        SumaMatricesParalelizada[] tareas = new SumaMatricesParalelizada[hilos];
        int filasPorHilo = filas / hilos;

        for (int i = 0; i < hilos; i++) {
            int inicio = i * filasPorHilo;
            int fin = (i + 1) * filasPorHilo;
            if (i == hilos - 1) {
                fin = filas; // Asegura que el último hilo procese todas las filas restantes
            }
            tareas[i] = new SumaMatricesParalelizada(matrizA, matrizB, resultado, inicio, fin);
            tareas[i].start();
        }

        for (SumaMatricesParalelizada tarea : tareas) {
            tarea.join(); // Espera a que todos los hilos terminen
        }

        System.out.println("Resultado de la suma de matrices:");
        for (int[] fila : resultado) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}

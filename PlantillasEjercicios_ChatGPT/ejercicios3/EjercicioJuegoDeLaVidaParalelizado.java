package ejercicios3;

class JuegoDeLaVidaParalelizado extends Thread {
    private final int[][] actual;
    private final int[][] siguiente;
    private final int inicioFila;
    private final int finFila;

    public JuegoDeLaVidaParalelizado(int[][] actual, int[][] siguiente, int inicioFila, int finFila) {
        this.actual = actual;
        this.siguiente = siguiente;
        this.inicioFila = inicioFila;
        this.finFila = finFila;
    }

    public void run() {
        for (int i = inicioFila; i < finFila; i++) {
            for (int j = 0; j < actual[i].length; j++) {
                int vecinosVivos = contarVecinos(i, j);
                if (actual[i][j] == 1) {
                    // Regla 1 o 3: Sobrevive
                    siguiente[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                } else {
                    // Regla 4: Nace
                    siguiente[i][j] = (vecinosVivos == 3) ? 1 : 0;
                }
            }
        }
    }

    private int contarVecinos(int fila, int columna) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Ignorar la celda misma
                int filaVecina = fila + i;
                int columnaVecina = columna + j;
                if (filaVecina >= 0 && filaVecina < actual.length && columnaVecina >= 0 && columnaVecina < actual[0].length) {
                    contador += actual[filaVecina][columnaVecina];
                }
            }
        }
        return contador;
    }
}

public class EjercicioJuegoDeLaVidaParalelizado {
    public static void main(String[] args) throws InterruptedException {
        int filas = 6;
        int columnas = 6;
        int[][] actual = {
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0}
        };
        int[][] siguiente = new int[filas][columnas];

        int hilos = 2; // Número de hilos
        JuegoDeLaVidaParalelizado[] tareas = new JuegoDeLaVidaParalelizado[hilos];
        int filasPorHilo = filas / hilos;

        for (int i = 0; i < hilos; i++) {
            int inicioFila = i * filasPorHilo;
            int finFila = (i + 1) * filasPorHilo;
            if (i == hilos - 1) {
                finFila = filas; // Asegura que el último hilo procese todas las filas restantes
            }
            tareas[i] = new JuegoDeLaVidaParalelizado(actual, siguiente, inicioFila, finFila);
            tareas[i].start();
        }

        for (JuegoDeLaVidaParalelizado tarea : tareas) {
            tarea.join(); // Espera a que todos los hilos terminen
        }

        // Actualiza el estado actual con el siguiente
        System.arraycopy(siguiente, 0, actual, 0, actual.length);

        System.out.println("Estado siguiente del Juego de la Vida:");
        for (int[] fila : actual) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}

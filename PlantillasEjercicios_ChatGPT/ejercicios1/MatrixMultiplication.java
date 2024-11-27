package ejercicios1;

/**
 * Multiplicación de matrices utilizando hilos para calcular bloques en paralelo.
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] C = new int[2][2];

        Thread t1 = new Thread(() -> C[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0]);
        Thread t2 = new Thread(() -> C[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1]);
        Thread t3 = new Thread(() -> C[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0]);
        Thread t4 = new Thread(() -> C[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1]);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Resultado de la multiplicación:");
        for (int[] row : C) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

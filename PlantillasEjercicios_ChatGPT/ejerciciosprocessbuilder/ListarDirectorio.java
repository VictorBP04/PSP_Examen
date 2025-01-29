package ejerciciosprocessbuilder;

import java.io.IOException;

public class ListarDirectorio {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
        pb.inheritIO(); // Redirige la salida al mismo terminal
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

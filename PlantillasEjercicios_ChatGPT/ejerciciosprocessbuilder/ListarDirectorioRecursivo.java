package ejerciciosprocessbuilder;

import java.io.IOException;

public class ListarDirectorioRecursivo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir", "/s");
        pb.inheritIO(); // Redirige la salida al terminal
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

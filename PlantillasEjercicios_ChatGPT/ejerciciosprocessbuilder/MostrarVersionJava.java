package ejerciciosprocessbuilder;

import java.io.IOException;

public class MostrarVersionJava {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "-version");
        pb.inheritIO(); // Redirige la salida a la consola
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

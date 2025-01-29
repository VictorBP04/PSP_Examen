package ejerciciosprocessbuilder;

import java.io.IOException;

public class CopiarArchivo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "copy", "origen.txt", "destino.txt");
        pb.inheritIO();
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

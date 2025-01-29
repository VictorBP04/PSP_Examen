package ejerciciosprocessbuilder;

import java.io.IOException;

public class AbrirPaginaWeb {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "https://www.google.com");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

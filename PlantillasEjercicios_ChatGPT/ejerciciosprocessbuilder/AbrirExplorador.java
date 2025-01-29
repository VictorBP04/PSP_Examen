package ejerciciosprocessbuilder;

import java.io.IOException;

public class AbrirExplorador {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("explorer.exe", ".");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


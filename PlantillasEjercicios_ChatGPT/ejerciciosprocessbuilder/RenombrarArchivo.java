package ejerciciosprocessbuilder;

import java.io.IOException;

public class RenombrarArchivo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "rename", "archivo.txt", "nuevo_nombre.txt");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

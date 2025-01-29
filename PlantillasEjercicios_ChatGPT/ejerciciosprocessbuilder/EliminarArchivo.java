package ejerciciosprocessbuilder;

import java.io.IOException;

public class EliminarArchivo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "del", "archivo.txt");
        try {
            pb.start();
            System.out.println("Archivo eliminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

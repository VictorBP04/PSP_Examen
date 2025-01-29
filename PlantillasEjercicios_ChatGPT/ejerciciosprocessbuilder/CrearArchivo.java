package ejerciciosprocessbuilder;

import java.io.IOException;

public class CrearArchivo {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo.", ">", "archivo.txt");
        try {
            pb.start();
            System.out.println("Archivo guardado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

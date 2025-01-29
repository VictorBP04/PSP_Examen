package ejerciciosprocessbuilder;

import java.io.IOException;

public class CrearDirectorio {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "mkdir", "nuevaCarpeta");
        try {
            pb.start();
            System.out.println("Directorio 'nuevaCarpeta' creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


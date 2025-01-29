package ejerciciosprocessbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComandoFallidoProcessBuilder {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "comando_inexistente");
        try {
            Process proceso = pb.start();
            BufferedReader error = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String linea;

            System.out.println("Salida de error:");
            while ((linea = error.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

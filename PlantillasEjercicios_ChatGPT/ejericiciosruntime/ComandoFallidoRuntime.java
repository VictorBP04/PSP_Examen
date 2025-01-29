package ejericiciosruntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComandoFallidoRuntime {
    public static void main(String[] args) {
        try {
            Process proceso = Runtime.getRuntime().exec("cmd /c comando_inexistente");
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

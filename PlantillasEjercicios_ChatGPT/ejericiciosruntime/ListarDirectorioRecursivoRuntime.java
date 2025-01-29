package ejericiciosruntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListarDirectorioRecursivoRuntime {
    public static void main(String[] args) {
        try {
            Process proceso = Runtime.getRuntime().exec("cmd /c dir /s");
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

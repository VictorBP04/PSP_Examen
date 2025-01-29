package ejericiciosruntime;

import java.io.IOException;

public class CrearDirectorioRuntime {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("cmd /c mkdir nuevaCarpeta");
            System.out.println("Directorio 'nuevaCarpeta' creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

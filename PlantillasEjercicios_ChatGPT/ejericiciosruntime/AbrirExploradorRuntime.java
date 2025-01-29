package ejericiciosruntime;

import java.io.IOException;

public class AbrirExploradorRuntime {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("explorer.exe .");
            System.out.println("Explorador de archivos abierto en el directorio actual.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

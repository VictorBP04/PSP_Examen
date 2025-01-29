package ejerciciosprocessbuilder;

import java.io.IOException;

public class AbrirNotepad {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

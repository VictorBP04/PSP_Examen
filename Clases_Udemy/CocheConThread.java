

//FORMAS DE CREAR HILOS

public class CocheConThread extends Thread {

    //1ERA FORMA

    private String simbol;

    public CocheConThread(String simbol) {
        this.simbol = simbol;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(simbol);
        }
    }
}

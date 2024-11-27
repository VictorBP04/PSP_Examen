

//FORMAS DE CREAR HILOS

public class CocheConRunnable implements Runnable {

    //1ERA FORMA

    private String simbol;

    public CocheConRunnable(String simbol) {
        this.simbol = simbol;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(simbol);
        }
    }
}

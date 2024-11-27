

//FORMAS DE CREAR HILOS

public class Main1 {
    public static void main(String[] args) {

        //1ERA FORMA

        CocheConThread coche = new CocheConThread("+");
        CocheConThread coche2 = new CocheConThread("-");
        CocheConThread coche3 = new CocheConThread("/");
        coche.start();
        coche2.start();
        coche3.start();

        //2A FORMA

        CocheConRunnable cocheR = new CocheConRunnable("+");
        CocheConRunnable cocheR2 = new CocheConRunnable("-");
        CocheConRunnable cocheR3 = new CocheConRunnable("/");
        new Thread(cocheR).start();
        new Thread(cocheR2).start();
        new Thread(cocheR3).start();

        //3A FORMA (LÓGICAS PEQUEÑAS)

        new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.print("+");
                }
            }
        }.start();

        //FORMA CON LAMBDAS PARA QUE EL CÓDIGO SEA MÁS LIMPIO Y CORTO

        new Thread(() -> {
            while (true){
                System.out.print("+");
            }
        }).start();

        //FORMA CON RUNNABLE() DENTRO DEL THREAD (CREANDO UN OBJETO THREAD Y DENTRO UN RUNNABLE(TAMBIÉN SIMPLIFICABLE CON LAMBDAS))

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.print("+");
                }
            }
        }).start();

        while (true){
            System.out.print("  ");
        }

    }
}

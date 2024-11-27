package ejercicios2;

class cliente extends Thread {
    private final ejercicios2.cajera cajera;

    public cliente(ejercicios2.cajera cajera) {
        this.cajera = cajera;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " va a comprar.");
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simula el tiempo de compra
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " va a pagar.");
        cajera.atender(this);
    }
}

class cajera extends Thread {
    public synchronized void atender(cliente cliente) {
        System.out.println("Cajera atendiendo a " + cliente.getName());
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simula el tiempo de cobro
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cajera ha cobrado a " + cliente.getName());
    }
}

public class CajeraAtendiendo {
    public static void main(String[] args) {
        cajera cajera = new cajera();
        cajera.start();

        for (int i = 0; i < 20; i++) {
            new cliente(cajera).start();
        }
    }
}

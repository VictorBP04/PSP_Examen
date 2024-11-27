

//CUANDO USAR EXNTEDS THREAD: CUANDO LA CLASE EXTIENDE DE OTRA Y NO PUEDE EXTENDER TAMBIÉN DE THREAD, POR EJEMPLO TENDRÍA QUE SER:
//PUBLIC CLASS COCHE EXTENDS "VEHÍCULO" IMPLEMENTS RUNNABLE

import java.util.Random;

public class Coche extends Thread {

    private Random random = new Random();
    private final Carrera carrera;
    private String nombre;

    public Coche(String nombre, Carrera carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            try {
                sleep(random.nextInt(1)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carrera.setGanador(this);
    }

    public String getCoche() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Coche nombre = " + nombre;
    }

}



import java.util.ArrayList;

public class Carrera {

    private ArrayList<Coche> participantes;
    private ArrayList<Coche> finalizacion;
    private Coche ganador;

    public Carrera() {
        this.participantes = new ArrayList<>();
        this.finalizacion = new ArrayList<>();
    }

    public void addParticipante(String nombre) {

        participantes.add(new Coche(nombre, this));
    }

    public void iniciarCarrera() {
        for (Coche coche : participantes) {
            coche.start();
        }
    }

    //UNA VEZ QUE UN HILO HA ACCEDIDO, NINGÚN OTRO PODRÁ
    public synchronized void setGanador(Coche coche) {
            if (this.ganador == null) {
                this.ganador = coche;
            }
            finalizacion.add(coche);
    }

    public Coche getGanador() {
        for (Coche coche : participantes) {
            try {
                coche.join();
            } catch (InterruptedException e) {
                System.err.println("Error en la finalización del hilo");;
            }
        }
        return ganador;
    }

    public ArrayList<Coche> getFinalizacion() {
        for (Coche coche : participantes) {
            try {
                if (coche.isAlive())
                coche.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar la finalización del hilo");;
            }
        }
        return finalizacion;
    }
}

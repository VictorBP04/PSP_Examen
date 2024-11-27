

//5 COCHES DIFERENTES, CADA COCHE DEBE RECORRER 50 METROS (en este caso
// 9 para no tardar tanto), SOLO UN GANADOR.

public class Main {

    public static void main(String[] args) {

        Carrera carrera = new Carrera();
        for (int i = 1; i < 6; i++){
            carrera.addParticipante(i + "");
        }

        carrera.iniciarCarrera();

        System.out.println(carrera.getFinalizacion());
        System.out.println(carrera.getGanador());
        System.out.println(carrera.getFinalizacion());

    }

}

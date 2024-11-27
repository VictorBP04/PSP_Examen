package EjerciciosExamen.Ejercicio1;

import java.util.ArrayList;

public class ITV {

	
	ArrayList<Integer> lista = new ArrayList<Integer>();
	int tiempoTotal=0;
	int cochesAtendidos=0;
	
	public void aniadirCoche(int numeroCoche) {
		lista.add(numeroCoche);
	}
	
	public void terminarCoche(int tiempoRevision) {
		
		if (lista.size()>0) {
			lista.remove(0);
			this.tiempoTotal+=tiempoRevision;
			cochesAtendidos++;
			
		}
	}
	public int cuantosAtendidos() {
		return this.cochesAtendidos;
	}
	public void mostrarTiempo() {
		System.out.println("El tiempo de ITV es "+this.tiempoTotal);
	}
}

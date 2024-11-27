package EjerciciosExamen.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread{
	
	int id;
	ITV itv;
	
	Semaphore entrada;
	Semaphore asignarPuesto;
	Semaphore finCoches;
	
	public Vehiculo(int id,ITV itv,Semaphore entrada,Semaphore asignarPuesto,Semaphore finCoches) {
		this.id = id;
		this.itv=itv;
		this.entrada=entrada;
		this.asignarPuesto=asignarPuesto;
		this.finCoches=finCoches;
	}

	public void run() {
		
		try {
		
		
		
		entrada.acquire();
		System.out.println("Soy coche" +id+" quiero a�adir");
		this.itv.aniadirCoche(this.id);
		entrada.release();
		
		asignarPuesto.acquire();
		System.out.println("Soy coche" +id+" estoy a�adido");	
		finCoches.acquire();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

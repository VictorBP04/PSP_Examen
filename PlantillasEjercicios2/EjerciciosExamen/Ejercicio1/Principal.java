package EjerciciosExamen.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		final int MAXPUESTOS=3;
		final int MAXCOCHES=7;
		
		Semaphore entrada = new Semaphore(1);
		Semaphore asignarPuesto = new Semaphore(MAXPUESTOS);
		Semaphore finCoches = new Semaphore(0);
		Semaphore finPuestos = new Semaphore(0);
		
	
		
		
		ITV itv= new ITV();
		
		Puesto p1=new Puesto(1,itv,finPuestos,finCoches,asignarPuesto,MAXCOCHES);
		Puesto p2=new Puesto(2, itv,finPuestos,finCoches,asignarPuesto,MAXCOCHES);
		Puesto p3=new Puesto(3, itv,finPuestos,finCoches,asignarPuesto,MAXCOCHES);
		
		Vehiculo c1= new Vehiculo(1,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c2= new Vehiculo(2,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c3= new Vehiculo(3,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c4= new Vehiculo(4,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c5= new Vehiculo(5,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c6= new Vehiculo(6,itv,entrada,asignarPuesto,finCoches);
		Vehiculo c7= new Vehiculo(7,itv,entrada,asignarPuesto,finCoches);
		
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();
		
	    p1.join();
	    p2.join();
	    p3.join();
	    
	    c1.join();
	    c2.join();
	    c3.join();
	    c4.join();
	    c5.join();
	    c6.join();
	    c7.join();
	    itv.mostrarTiempo();
	}

}

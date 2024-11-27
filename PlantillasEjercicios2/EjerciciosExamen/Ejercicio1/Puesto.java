package EjerciciosExamen.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Puesto extends Thread
{

	
	int id;
	ITV itv;
	Semaphore finPuestos;
	Semaphore finCoches;
	Semaphore asignarPuesto;

	int tiempo=0;
	final int MAXCOCHES;
	public Puesto(int id, ITV itv, Semaphore finPuestos,Semaphore finCoches,	Semaphore asignarPuesto,int MAXCOCHES) {
		this.id = id;
		this.itv = itv;
		this.finPuestos = finPuestos;
		this.finCoches = finCoches;
		this.asignarPuesto=asignarPuesto;
		this.MAXCOCHES=MAXCOCHES;
	}
	
	
	public void run () {
		System.out.println("Soy puesto "+id+" comienzo");
		while (itv.cuantosAtendidos()!=MAXCOCHES) {
			int n=(int) (Math.random()*2)+1;
			try {
				System.out.println("Me duermo "+n);
				this.sleep(n*1000);
				itv.terminarCoche(n);
				asignarPuesto.release();
				if (this.itv.lista.size()==0)
					for (int i=0;i<MAXCOCHES;i++) {
						this.finCoches.release();
					}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("Soy puesto "+id+" termino");
		
	}
	
	
}

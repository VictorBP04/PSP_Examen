package EjerciciosNormales.Ejercicio11;

import java.util.concurrent.Semaphore;
/***********************************
Implementar un programa en java que simule 
el comportamiento siguiente:
Se dispone de un museo que es visitado por 10 turistas.
El museo solo dispone de un aforo máximo de 5 turistas simultáneos.
Los accesos al museo se podrán realizar por puertas doble.
Existe una sala especial del museo que solo puede ser visitada de 1 en 1.
Los visitantes deberán esperar a que estén todos en la puerta para empezar a 
acceder.
+Una vez que los turistas visitantes han terminado de visitar el museo deberán 
subirse al autobús y cuando el último turista termine de visitar, el autobús arrancará.
+Se pide calcular el tiempo total que se tarda en simular todo el proceso.


 */


public class PrincipalMuseo {

	public static void main(String[] args) {

		long t1=System.currentTimeMillis();
		Semaphore aforo = new Semaphore(5);
		Semaphore entrada = new Semaphore(2);
		Semaphore sala = new Semaphore(1);
		Semaphore espera = new Semaphore(0);
		Semaphore mutex = new Semaphore(1);
		Semaphore autobus = new Semaphore(0);
		
		Bean bean = new Bean();
		
		
		
		HiloTurista h1 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h2 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h3 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h4 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h5 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h6 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h7 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h8 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h9 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		HiloTurista h10 = new HiloTurista(aforo,entrada,sala,espera,mutex,autobus,bean);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();
		h8.start();
		h9.start();
		h10.start();

		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
			h5.join();
			h6.join();
			h7.join();
			h8.join();
			h9.join();
			h10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
	}

}

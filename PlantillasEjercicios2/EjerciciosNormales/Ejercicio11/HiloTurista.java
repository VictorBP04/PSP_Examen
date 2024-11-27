package EjerciciosNormales.Ejercicio11;

import java.util.concurrent.Semaphore;

public class HiloTurista extends Thread{
	
	Semaphore aforo;
	Semaphore entrada;
	Semaphore sala;
	Semaphore espera;
	Semaphore mutex;
	Semaphore autobus;
	Bean bean;
	
	


	
	public HiloTurista(Semaphore aforo, Semaphore entrada, Semaphore sala, Semaphore espera, Semaphore mutex,
			Semaphore autobus, Bean bean) {
		this.aforo = aforo;
		this.entrada = entrada;
		this.sala = sala;
		this.espera = espera;
		this.mutex = mutex;
		this.autobus = autobus;
		this.bean = bean;
	}





	public void run() {
	try {
		mutex.acquire();
			if (bean.contador==9) {
				mutex.release();
				System.out.println("Voy a desbloquear a todos ");
				for (int i=0;i<10;i++)
				  espera.release();
			}
			else {
				
				this.bean.contador++;
				System.out.println(Thread.currentThread().getId()+" contador "+bean.contador);
				mutex.release();
				espera.acquire();
				
			}
			
			aforo.acquire();
			System.out.println("Entro al museo "+Thread.currentThread().getId());
			entrada.acquire();
			System.out.println("Espero en la cola "+Thread.currentThread().getId());
			//Thread.sleep(1000);
			entrada.release();
			System.out.println("Visito al museo "+Thread.currentThread().getId());
			//Thread.sleep(2000);
			sala.acquire();
			System.out.println("Entro a la sala especial "+Thread.currentThread().getId());
			//Thread.sleep(1000);
			sala.release();
	

			entrada.acquire();
			System.out.println("Saliendo del museo "+Thread.currentThread().getId());
			entrada.release();
			aforo.release();
			System.out.println("He salido del museo "+Thread.currentThread().getId());
			mutex.acquire();
			if (bean.esperadoBus<9) {
				System.out.println("Estamos en el bus "+(bean.esperadoBus+1));
				bean.esperadoBus++;
				mutex.release();
				autobus.acquire();
			}
			else {
			mutex.release();
			System.out.println("Estamos todos en el bus.Nos vamos");
			for (int i=0;i<10;i++)
			  autobus.release();
			}
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	
	
}

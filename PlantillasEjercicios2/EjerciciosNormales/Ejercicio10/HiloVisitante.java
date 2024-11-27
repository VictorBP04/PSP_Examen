package EjerciciosNormales.Ejercicio10;

public class HiloVisitante extends Thread {
	Bean b;
	Bean2 b2;



	public HiloVisitante(Bean b, Bean2 b2) {
		super();
		this.b = b;
		this.b2 = b2;
	}



	public void run() {
		System.out.println("Llego al museo." + Thread.currentThread().getId());

		synchronized (b) {

			if (b.numVisitantes < b.AFORO) {
				b.numVisitantes++;
				System.out.println(b.numVisitantes);
			}
			else {
				System.out.println("ME BLOQUEO." + Thread.currentThread().getId());
				try {
					b.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
			System.out.println("Visito el museo." + Thread.currentThread().getId());
			synchronized (b) {
				b.numVisitantes--;
				if (b.numVisitantes < b.AFORO) {
					System.out.println(+Thread.currentThread().getId() + " Desbloqueo un hilo");
					b.notify();
				}
			}
			System.out.println("Salgo del museo." + Thread.currentThread().getId());
			synchronized (b2) {
				b2.visitas++;
				if (b2.visitas==10)
					b2.notify();
			}
			System.out.println("Espero al bus." + Thread.currentThread().getId());	
			synchronized (b) {		
					try {
						if (b2.visitas<10)
						b.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				
			}
		
	}

}
